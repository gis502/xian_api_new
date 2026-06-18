package com.gis.xian.controller;

import com.gis.xian.service.ExportService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/export")
@Slf4j
public class ExportController extends BaseController {

    @Resource
    private ExportService exportService;

    @GetMapping("/{tableName}")
    public void downloadCsv(@PathVariable String tableName, HttpServletResponse response) {
        log.info("导出请求，表名: {}", tableName);
        try {
            String fileName = URLEncoder.encode(tableName + ".csv", StandardCharsets.UTF_8);
            response.setContentType("text/csv; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            exportService.streamExportToStream(tableName, response.getOutputStream());
        } catch (Exception e) {
            log.error("导出失败，表名: {}, 错误: {}", tableName, e.getMessage(), e);
            if (!response.isCommitted()) {
                try {
                    response.reset();
                    response.setContentType("application/json; charset=UTF-8");
                    response.setStatus(500);
                    response.getOutputStream().write(
                        ("导出失败: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
                } catch (Exception ignored) {}
            }
        }
    }
}
