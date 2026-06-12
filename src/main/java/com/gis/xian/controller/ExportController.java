package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 数据导出控制器
 */
@RestController
@RequestMapping("/api/export")
@Slf4j
public class ExportController {

    @Resource
    private ExportService exportService;

    /**
     * 分页导出表数据
     */
    @GetMapping("/table/{tableName}")
    public ApiResponse<Map<String, Object>> exportTableData(
            @PathVariable String tableName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1000") int pageSize) {

        try {
            List<Map<String, Object>> data = exportService.exportTableData(tableName, page, pageSize);
            int total = exportService.getTableTotalCount(tableName);

            Map<String, Object> result = new java.util.HashMap<>();
            result.put("data", data);
            result.put("total", total);
            result.put("page", page);
            result.put("pageSize", pageSize);

            return ApiResponse.ok(result);
        } catch (Exception e) {
            log.error("导出表数据失败: {}", e.getMessage(), e);
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    /**
     * 流式导出（适用于超大数据量）
     */
    @PostMapping("/stream/{tableName}")
    public ApiResponse<Void> streamExport(
            @PathVariable String tableName,
            @RequestParam(defaultValue = "1000") int batchSize) {

        try {
            exportService.streamExport(tableName, batchSize);
            return ApiResponse.ok();
        } catch (Exception e) {
            log.error("流式导出失败: {}", e.getMessage(), e);
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 测试游标分页性能
     * @param tableName 表名
     * @param batchSize 批次大小（默认50000）
     * @param maxBatches 最大批次数（默认10）
     */
    @GetMapping("/test-cursor/{tableName}")
    public ApiResponse<String> testCursorPagination(
            @PathVariable String tableName,
            @RequestParam(defaultValue = "50000") int batchSize,
            @RequestParam(defaultValue = "10") int maxBatches) {

        try {
            log.info("开始测试游标分页: 表={}, 批次大小={}, 最大批次数={}", 
                     tableName, batchSize, maxBatches);
            
            exportService.testCursorPagination(tableName, batchSize, maxBatches);
            
            return ApiResponse.ok("游标分页测试完成，请查看后端日志");
        } catch (IllegalArgumentException e) {
            log.warn("游标分页测试失败: {}", e.getMessage());
            return new ApiResponse<>(500, e.getMessage(), null);
        } catch (Exception e) {
            log.error("游标分页测试异常: {}", e.getMessage(), e);
            return new ApiResponse<>(500, "测试失败: " + e.getMessage(), null);
        }
    }

    /**
     * 验证表的主键类型
     */
    @GetMapping("/validate-pk/{tableName}")
    public ApiResponse<Boolean> validatePrimaryKey(@PathVariable String tableName) {
        try {
            boolean valid = exportService.validateIntegerPrimaryKey(tableName);
            return ApiResponse.ok(valid);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        } catch (Exception e) {
            log.error("验证主键失败: {}", e.getMessage(), e);
            return new ApiResponse<>(500, "验证失败: " + e.getMessage(), null);
        }
    }
}
