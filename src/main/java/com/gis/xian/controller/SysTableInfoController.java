package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.entity.SysTableInfo;
import com.gis.xian.service.SysTableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表信息控制器
 */
@RestController
@RequestMapping("/api/table")
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
        return ApiResponse.ok(tables);
    }

    /**
     * 查看表的具体数据内容
     * @param tableName 表名
     * @param limit 限制返回的记录数（可选，不传则不限制）
     * @return 表数据记录
     */
    @GetMapping("/data/{tableName}")
    public ApiResponse<Map<String, Object>> getTableData(
            @PathVariable String tableName,
            @RequestParam(required = false) Integer limit) {
        
        // 获取表数据
        List<Map<String, Object>> data = sysTableInfoService.getTableData(tableName, limit);
        
        // 获取表字段信息
        List<Map<String, Object>> columns = sysTableInfoService.getTableColumns(tableName);
        
        // 组装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("tableName", tableName);
        result.put("columns", columns);
        result.put("data", data);
        result.put("total", data.size());
        
        return ApiResponse.ok(result);
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
     * @param whereConditions WHERE条件（JSON格式，如 {"id": 1}）
     * @param updateData 更新数据（JSON格式，如 {"name": "新值"}）
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
            
            if (whereConditions == null || whereConditions.isEmpty()) {
                return ApiResponse.error("WHERE条件不能为空");
            }
            if (updateData == null || updateData.isEmpty()) {
                return ApiResponse.error("更新数据不能为空");
            }
            
            sysTableInfoService.updateTableData(tableName, whereConditions, updateData);
            return ApiResponse.ok(null);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}

