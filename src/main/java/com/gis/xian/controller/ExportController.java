package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.Map;

/**
 * 数据导出控制器
 */
@RestController
@RequestMapping("/api/export")
@Slf4j
public class ExportController extends BaseController {

    @Resource
    private ExportService exportService;

    /**
     * 游标分页测试接口
     */
    @GetMapping("/test-cursor/{tableName}")
    public ApiResponse<Map<String, Object>> testCursorPagination(@PathVariable String tableName) {
        log.info("收到游标分页测试请求，表名: {}", tableName);
        
        try {
            Map<String, Object> result = exportService.testCursorPagination(tableName);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("游标分页测试失败: {}", e.getMessage(), e);
            return ApiResponse.error(500, "测试失败: " + e.getMessage(), null);
        }
    }

    /**
     * 流式导出表数据到CSV文件
     */
    @GetMapping("/stream/{tableName}")
    public ApiResponse<Map<String, Object>> streamExport(
            @PathVariable String tableName,
            @RequestParam(required = false, defaultValue = "./exports") String outputPath) {
        
        log.info("收到流式导出请求，表名: {}, 输出路径: {}", tableName, outputPath);
        
        try {
            // 确保输出目录存在
            java.io.File dir = new java.io.File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 生成文件名
            String fileName = tableName + "_" + System.currentTimeMillis() + ".csv";
            String fullPath = outputPath + "/" + fileName;
            
            // 执行流式导出
            Map<String, Object> result = exportService.streamExportToCsv(tableName, fullPath);
            
            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("流式导出失败: {}", e.getMessage(), e);
            return ApiResponse.error(500, "导出失败: " + e.getMessage(), null);
        }
    }
}
