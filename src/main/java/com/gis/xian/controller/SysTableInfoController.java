package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.entity.SysTableInfo;
import com.gis.xian.service.SysTableInfoService;
import com.gis.xian.utils.safety.GeometryUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表信息控制器
 */
@RestController
@RequestMapping("/table")
public class SysTableInfoController extends BaseController {

    @Autowired
    private SysTableInfoService sysTableInfoService;

    /**
     * 获取所有数据库表信息
     * @return 数据库表信息列表
     */
    @GetMapping("/list")
    public ApiResponse<List<SysTableInfo>> getAllTables() {
        List<SysTableInfo> tables = sysTableInfoService.getAllTables();
        return ApiResponse.ok(tables);
    }

    /**
     * 数据管理模块 - 获取所有数据库表信息
     * 为前端数据管理模块提供专用接口
     * @return 数据库表信息列表
     */
    @GetMapping("/data-management/tables")
    public ApiResponse<List<SysTableInfo>> getDataManagementTables() {
        List<SysTableInfo> tables = sysTableInfoService.getAllTables();
        
        // 打印第一条数据的时间字段，用于调试
        if (tables != null && !tables.isEmpty()) {
            SysTableInfo firstTable = tables.get(0);
            System.out.println("\n========================================");
            System.out.println(" 表列表查询结果");
            System.out.println("表名: " + firstTable.getTableName());
            System.out.println("创建时间: " + firstTable.getCreateTime());
            System.out.println("更新时间: " + firstTable.getUpdateTime());
            System.out.println("========================================\n");
        }
        
        return ApiResponse.ok(tables);
    }

    /**
     * 查看表的具体数据内容
     * @param tableName 表名
     * @param page 页码（可选，默认1）
     * @param pageSize 每页条数（可选，默认10）
     * @return 表数据记录
     */
    @GetMapping("/data/{tableName}")
    public ApiResponse<Map<String, Object>> getTableData(
            @PathVariable String tableName,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        
        // 计算 OFFSET
        int offset = (page - 1) * pageSize;
        
        // 获取表数据（分页）
        List<Map<String, Object>> data = sysTableInfoService.getTableData(tableName, pageSize, offset);
        
        // 获取总记录数
        int total = sysTableInfoService.getTableTotalCount(tableName);
        
        // 获取表字段信息
        List<Map<String, Object>> columns = sysTableInfoService.getTableColumns(tableName);
        
        // 处理 geometry 字段：将 point 转换为 lon 和 lat
        processGeometryFields(data, columns);
        
        // 组装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("tableName", tableName);
        result.put("columns", columns);
        result.put("data", data);
        result.put("total", total);
        
        return ApiResponse.ok(result);
    }

    /**
     * 处理 geometry 字段：将 WKB 数据转换为 lon 和 lat
     * @param data 表数据
     * @param columns 字段信息
     */
    private void processGeometryFields(List<Map<String, Object>> data, List<Map<String, Object>> columns) {
        if (data == null || data.isEmpty() || columns == null) {
            return;
        }
        
        // 遍历所有字段，查找可能包含 WKB 数据的字段
        for (Map<String, Object> col : columns) {
            String columnName = (String) col.get("column_name");
            
            // 遍历每一行数据
            for (Map<String, Object> row : data) {
                Object value = row.get(columnName);
                if (value != null) {
                    String valueStr = value.toString();
                    
                    // 检查是否为十六进制字符串（WKB 特征）
                    if (isWKBFormat(valueStr)) {
                        try {
                            // 将 WKB 转换为 lon 和 lat
                            String[] lonLat = GeometryUtil.wkbToLonLat(valueStr);
                            if (lonLat != null && lonLat.length == 2) {
                                // 创建一个新的 Map 来存储转换后的数据
                                Map<String, Object> newRow = new java.util.HashMap<>(row);
                                // 移除原始字段
                                newRow.remove(columnName);
                                // 添加 lon 和 lat 字段
                                newRow.put("lon", lonLat[0]);
                                newRow.put("lat", lonLat[1]);
                                
                                // 用新 Map 替换旧 Map
                                int index = data.indexOf(row);
                                if (index >= 0) {
                                    data.set(index, newRow);
                                }
                                
                                System.out.println("成功转换字段: " + columnName + " -> lon: " + lonLat[0] + ", lat: " + lonLat[1]);
                            }
                        } catch (Exception e) {
                            // 转换失败，保留原始数据
                            System.err.println("转换字段 " + columnName + " 失败: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * 检查字符串是否为 WKB 格式（十六进制字符串）
     * WKB 特征：长度为偶数，只包含十六进制字符，长度通常为 42（Point 类型）
     * @param value 字符串值
     * @return 是否为 WKB 格式
     */
    private boolean isWKBFormat(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        
        // 移除 0x 前缀（如果有）
        String hexValue = value;
        if (hexValue.startsWith("0x") || hexValue.startsWith("0X")) {
            hexValue = hexValue.substring(2);
        }
        
        // 检查长度是否为偶数（十六进制字符串的特征）
        if (hexValue.length() % 2 != 0) {
            return false;
        }
        
        // 检查是否只包含十六进制字符
        if (!hexValue.matches("^[0-9A-Fa-f]+$")) {
            return false;
        }
        
        // Point 类型的 WKB 通常是 42 个字符（21 字节）
        // 但也可以是其他长度
        return hexValue.length() >= 42;
    }

    /**
     * 修改表信息（表名、表描述）
     * @param oldTableName 原表名
     * @param newTableName 新表名（可选）
     * @param newComment 新表描述（可选）
     * @return 操作结果
     */
    @PutMapping("/update-table-info")
    public ApiResponse<Void> updateTableInfo(
            @RequestParam String oldTableName,
            @RequestParam(required = false) String newTableName,
            @RequestParam(required = false) String newComment) {
        try {
            sysTableInfoService.updateTableInfo(oldTableName, newTableName, newComment);
            return ApiResponse.ok(null);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 修改表中的具体数据记录
     * @param tableName 表名
     * @param request 请求体，包含 whereConditions 和 updateData
     * @return 操作结果
     */
    @PutMapping("/update-data/{tableName}")
    public ApiResponse<Void> updateTableData(
            @PathVariable String tableName,
            @RequestBody Map<String, Object> request) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> whereConditions = (Map<String, Object>) request.get("whereConditions");
            @SuppressWarnings("unchecked")
            Map<String, Object> updateData = (Map<String, Object>) request.get("updateData");
            
            // 处理 geometry 字段更新
            updateData = processGeometryUpdate(tableName, whereConditions, updateData);
            
            sysTableInfoService.updateTableData(tableName, whereConditions, updateData);
            return ApiResponse.ok(null);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 新增表数据记录
     * @param tableName 表名
     * @param insertData 新增数据
     * @return 操作结果
     */
    @PostMapping("/insert-data/{tableName}")
    public ApiResponse<Void> insertTableData(
            @PathVariable String tableName,
            @RequestBody Map<String, Object> insertData) {
        try {
            // 移除主键字段（如果传了id，由数据库自动生成）
            insertData.remove("id");
            
            // 处理字段类型转换
            processInsertData(tableName, insertData);
            
            sysTableInfoService.insertTableData(tableName, insertData);
            return ApiResponse.ok(null);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 删除表数据记录（物理删除）
     * @param tableName 表名
     * @param request 请求体，包含 ids 数组
     * @return 操作结果
     */
    @DeleteMapping("/delete-data/{tableName}")
    public ApiResponse<Void> deleteTableData(
            @PathVariable String tableName,
            @RequestBody Map<String, Object> request) {
        try {
            @SuppressWarnings("unchecked")
            List<Object> ids = (List<Object>) request.get("ids");
            
            if (ids == null || ids.isEmpty()) {
                return ApiResponse.error("请选择要删除的记录");
            }
            
            System.out.println("\n========================================");
            System.out.println("🗑️ 开始删除数据");
            System.out.println("表名: " + tableName);
            System.out.println("删除IDs: " + ids);
            System.out.println("========================================\n");
            
            sysTableInfoService.deleteTableData(tableName, ids);
            
            System.out.println("✅ 删除成功！\n");
            return ApiResponse.ok(null);
        } catch (Exception e) {
            System.err.println("❌ 删除失败：");
            e.printStackTrace();
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 处理新增数据的字段类型转换
     * @param tableName 表名
     * @param insertData 新增数据
     */
    private void processInsertData(String tableName, Map<String, Object> insertData) {
        System.out.println("📋 获取表字段信息...");
        List<Map<String, Object>> columns = sysTableInfoService.getTableColumns(tableName);
        System.out.println("字段数量: " + columns.size());
        
        for (Map<String, Object> col : columns) {
            String columnName = (String) col.get("column_name");
            String dataType = (String) col.get("data_type");
            
            // 只打印我们关心的字段
            if ("create_time".equals(columnName) || "update_time".equals(columnName)) {
                System.out.println("\n🕒 检查字段: " + columnName);
                System.out.println("  数据库类型: " + dataType);
                System.out.println("  是否包含在insertData中: " + insertData.containsKey(columnName));
                
                if (insertData.containsKey(columnName)) {
                    Object value = insertData.get(columnName);
                    System.out.println("  原始值: " + value);
                    System.out.println("  原始类型: " + (value != null ? value.getClass().getName() : "null"));
                    
                    if (value == null) {
                        System.out.println("  ⏭️ 值为null，跳过");
                        continue;
                    }
                    
                    // 处理时间字段
                    if (dataType != null && (dataType.toLowerCase().contains("timestamp") || 
                        dataType.toLowerCase().contains("date"))) {
                        System.out.println("  🔧 检测到时间字段，开始转换...");
                        try {
                            String timeStr = value.toString().trim();
                            System.out.println("    原始时间字符串: " + timeStr);
                            
                            // 替换 T 为空格（兼容 ISO 8601 格式）
                            timeStr = timeStr.replace("T", " ");
                            // 移除时区信息（如果有）
                            if (timeStr.contains("+")) {
                                timeStr = timeStr.substring(0, timeStr.indexOf("+"));
                            }
                            if (timeStr.contains("Z")) {
                                timeStr = timeStr.replace("Z", "");
                            }
                            System.out.println("    处理后时间字符串: " + timeStr);
                            
                            // 解析时间字符串
                            java.time.LocalDateTime localDateTime = java.time.LocalDateTime.parse(
                                timeStr, 
                                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            );
                            System.out.println("    解析为LocalDateTime: " + localDateTime);
                            
                            // 转换为 Timestamp
                            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(localDateTime);
                            insertData.put(columnName, timestamp);
                            
                            System.out.println("    ✅ 转换成功！");
                            System.out.println("    新值: " + timestamp);
                            System.out.println("    新类型: " + timestamp.getClass().getName());
                        } catch (Exception e) {
                            System.err.println("    ❌ 时间字段转换失败！");
                            System.err.println("    错误: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            // 其他字段的处理（保持原样）
            if (dataType == null || !insertData.containsKey(columnName)) {
                continue;
            }
            
            Object value = insertData.get(columnName);
            if (value == null) {
                continue;
            }
            
            // 处理整数字段
            if (dataType.toLowerCase().contains("int")) {
                try {
                    insertData.put(columnName, Integer.parseInt(value.toString()));
                } catch (Exception e) {
                    System.err.println("整数字段转换失败: " + columnName + ", 值: " + value);
                }
            }
            
            // 处理浮点数字段
            else if (dataType.toLowerCase().contains("float") || 
                     dataType.toLowerCase().contains("double") ||
                     dataType.toLowerCase().contains("numeric")) {
                try {
                    insertData.put(columnName, Double.parseDouble(value.toString()));
                } catch (Exception e) {
                    System.err.println("浮点数字段转换失败: " + columnName + ", 值: " + value);
                }
            }
        }
    }

    /**
     * 处理 geometry 字段更新：将 lon 和 lat 转换为 WKB
     * @param tableName 表名
     * @param whereConditions WHERE条件
     * @param updateData 更新数据
     * @return 处理后的更新数据
     */
    private Map<String, Object> processGeometryUpdate(String tableName, Map<String, Object> whereConditions, Map<String, Object> updateData) {
        // 获取表字段信息
        List<Map<String, Object>> columns = sysTableInfoService.getTableColumns(tableName);
        
        // 检查是否存在 lon 和 lat 字段
        boolean hasLon = false;
        boolean hasLat = false;
        for (Map<String, Object> col : columns) {
            String columnName = (String) col.get("column_name");
            if ("lon".equals(columnName)) hasLon = true;
            if ("lat".equals(columnName)) hasLat = true;
        }
        
        // 如果同时有 lon 和 lat，转换为 geom 字段
        if (hasLon && hasLat && updateData.containsKey("lon") && updateData.containsKey("lat")) {
            try {
                Object lonValue = updateData.get("lon");
                Object latValue = updateData.get("lat");
                
                if (lonValue != null && latValue != null) {
                    // 转换为 WKB（传入字符串参数）
                    String wkb = GeometryUtil.lonLatToWkb(lonValue.toString(), latValue.toString());
                    
                    // 移除 lon 和 lat，添加 geom
                    updateData.remove("lon");
                    updateData.remove("lat");
                    updateData.put("geom", wkb);
                }
            } catch (Exception e) {
                System.err.println("转换 lon/lat 到 WKB 失败: " + e.getMessage());
            }
        }
        
        return updateData;
    }
}

