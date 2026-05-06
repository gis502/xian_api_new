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
     * @param limit 限制返回的记录数（可选，默认100）
     * @return 表数据记录
     */
    @GetMapping("/data/{tableName}")
    public ApiResponse<Map<String, Object>> getTableData(
            @PathVariable String tableName,
            @RequestParam(defaultValue = "100") Integer limit) {
        
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
}

