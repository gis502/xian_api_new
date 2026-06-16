package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.entity.ExportTask;
import com.gis.xian.service.ExportService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/export")
@Slf4j
public class ExportController extends BaseController {

    @Resource
    private ExportService exportService;

    private static final String EXPORT_DIR = "./exports";

    @GetMapping("/count/{tableName}")
    public ApiResponse<Integer> getRowCount(@PathVariable String tableName) {
        int count = exportService.getRowCount(tableName);
        return ApiResponse.ok(count);
    }

    @GetMapping("/{tableName}")
    public void downloadCsv(@PathVariable String tableName, HttpServletResponse response) {
        log.info("同步导出请求，表名: {}", tableName);
        try {
            String fileName = URLEncoder.encode(tableName + ".csv", StandardCharsets.UTF_8);
            response.setContentType("text/csv; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            exportService.streamExportToStream(tableName, response.getOutputStream());
        } catch (Exception e) {
            log.error("同步导出失败，表名: {}, 错误: {}", tableName, e.getMessage(), e);
            if (!response.isCommitted()) {
                try {
                    response.reset();
                    response.setContentType("application/json; charset=UTF-8");
                    response.setStatus(500);
                    response.getOutputStream().write(
                        ("{\"code\":500,\"message\":\"" + e.getMessage() + "\"}")
                            .getBytes(StandardCharsets.UTF_8));
                } catch (Exception ignored) {}
            }
        }
    }

    @PostMapping("/submit")
    public ApiResponse<Map<String, Object>> submitTask(@RequestBody Map<String, Object> body) {
        String tableName = (String) body.get("tableName");
        int rowCount = exportService.getRowCount(tableName);
        log.info("提交异步导出任务，表名: {}, 行数: {}", tableName, rowCount);

        ExportTask task = exportService.submitTask(tableName, rowCount);
        String filePath = EXPORT_DIR + "/" + task.getId() + "_" + tableName + ".csv";
        exportService.runAsyncExport(task.getId(), tableName, filePath);

        return ApiResponse.ok(Map.of(
            "taskId", task.getId(),
            "totalRows", rowCount
        ));
    }

    @GetMapping("/progress/{taskId}")
    public ApiResponse<Map<String, Object>> getProgress(@PathVariable Long taskId) {
        ExportTask task = exportService.getTask(taskId);
        if (task == null) {
            return ApiResponse.error(404, "任务不存在", null);
        }
        return ApiResponse.ok(Map.of(
            "taskId", task.getId(),
            "status", task.getStatus(),
            "totalRows", task.getTotalRows(),
            "processedRows", task.getProcessedRows(),
            "errorMessage", task.getErrorMessage(),
            "filePath", task.getFilePath()
        ));
    }

    @GetMapping("/download/{taskId}")
    public void downloadFile(@PathVariable Long taskId, HttpServletResponse response) {
        ExportTask task = exportService.getTask(taskId);
        if (task == null || !"COMPLETED".equals(task.getStatus())) {
            try {
                response.setContentType("application/json; charset=UTF-8");
                response.setStatus(404);
                response.getWriter().write("{\"code\":404,\"message\":\"文件不存在或任务未完成\"}");
                response.getWriter().flush();
            } catch (Exception ignored) {}
            return;
        }

        File file = new File(task.getFilePath());
        if (!file.exists()) {
            try {
                response.setContentType("application/json; charset=UTF-8");
                response.setStatus(404);
                response.getWriter().write("{\"code\":404,\"message\":\"文件已被清理\"}");
                response.getWriter().flush();
            } catch (Exception ignored) {}
            return;
        }

        try {
            String fileName = URLEncoder.encode(task.getTableName() + ".csv", StandardCharsets.UTF_8);
            response.setContentType("text/csv; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            response.setContentLengthLong(file.length());

            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.error("文件下载失败，taskId: {}, 错误: {}", taskId, e.getMessage(), e);
        }
    }
}
