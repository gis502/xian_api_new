package com.gis.xian.service;

import com.gis.xian.entity.ExportTask;
import com.gis.xian.mapper.export.ExportMapper;
import com.gis.xian.utils.safety.CsvWriterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExportService {

    @Resource
    private ExportMapper exportMapper;

    private static final int DEFAULT_BATCH_SIZE = 10000;
    private static final byte[] UTF8_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
    private static final int ASYNC_THRESHOLD = 10000;

    public boolean shouldUseAsync(int rowCount) {
        return rowCount >= ASYNC_THRESHOLD;
    }

    public int getRowCount(String tableName) {
        return exportMapper.getTableTotalCount(tableName);
    }

    public String detectPrimaryKeyColumn(String tableName) {
        try {
            Map<String, Object> pkInfo = exportMapper.getPrimaryKeyInfo(tableName);
            if (pkInfo != null && pkInfo.get("column_name") != null) {
                return pkInfo.get("column_name").toString();
            }
        } catch (Exception e) {
            log.warn("获取主键信息失败，回退到 id: {}", e.getMessage());
        }
        return "id";
    }

    public List<Map<String, Object>> queryBatch(String tableName, String pkColumn, Long lastId, int batchSize) {
        return exportMapper.cursorQueryByPkColumn(tableName, pkColumn, lastId, batchSize);
    }

    public void streamExportToStream(String tableName, OutputStream outputStream) {
        log.info("同步导出，表名: {}", tableName);
        long t0 = System.currentTimeMillis();
        log.info("异步导出 - 检测主键...");
        String pkColumn = detectPrimaryKeyColumn(tableName);
        log.info("异步导出 - 主键列: {}", pkColumn);

        try {
            log.info("异步导出 - 开始计数...");
            outputStream.write(UTF8_BOM);
            CSVPrinter csvPrinter = CsvWriterUtil.createCsvPrinter(outputStream);

            Long lastId = 0L;
            int totalRows = 0;
            List<String> headers = null;

            int batchIndex = 0;
                while (true) {
                long batchT0 = System.currentTimeMillis();
                List<Map<String, Object>> batch = queryBatch(tableName, pkColumn, lastId, DEFAULT_BATCH_SIZE);
                long queryTime = System.currentTimeMillis() - batchT0;

                if (batch.isEmpty()) break;

                if (headers == null) {
                    headers = CsvWriterUtil.extractHeaders(batch);
                    CsvWriterUtil.writeHeaders(csvPrinter, headers);
                }
                CsvWriterUtil.writeRows(csvPrinter, batch, headers);

                int sz = batch.size();
                totalRows += sz;
                lastId = getLastIdFromBatch(batch, pkColumn);
                csvPrinter.flush();

                batchIndex++;
                long batchTime = System.currentTimeMillis() - batchT0;
                log.info("第 {} 批完成，查询 {} ms，本批总 {} ms，累计 {} 行",
                        batchIndex, queryTime, batchTime, totalRows);

                if (sz < DEFAULT_BATCH_SIZE) break;
            }

            CsvWriterUtil.closeCsvPrinter(csvPrinter);
            log.info("同步导出完成！{} 行, {} ms", totalRows, System.currentTimeMillis() - t0);

        } catch (IOException e) {
            throw new RuntimeException("CSV写入失败: " + e.getMessage(), e);
        }
    }

    @Async("exportTaskExecutor")
    public void runAsyncExport(Long taskId, String tableName, String filePath) {
        log.info("异步导出开始，taskId: {}, 表名: {}", taskId, tableName);
        long t0 = System.currentTimeMillis();
        log.info("异步导出 - 检测主键...");
        String pkColumn = detectPrimaryKeyColumn(tableName);
        log.info("异步导出 - 主键列: {}", pkColumn);
        int processedRows = 0;

        try {
            log.info("异步导出 - 开始计数...");
            int totalRows = getRowCount(tableName);
            exportMapper.updateTaskProgress(taskId, 0);
            log.info("异步导出 - 计数完成: {} 行，开始分批导出", totalRows);

            File outputFile = new File(filePath);
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) parentDir.mkdirs();

            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(outputFile), 65536)) {
                fos.write(UTF8_BOM);
                CSVPrinter csvPrinter = CsvWriterUtil.createCsvPrinter(fos);

                Long lastId = 0L;
                List<String> headers = null;

                int batchIndex = 0;
                while (true) {
                    long batchT0 = System.currentTimeMillis();
                    List<Map<String, Object>> batch = queryBatch(tableName, pkColumn, lastId, DEFAULT_BATCH_SIZE);
                    long queryTime = System.currentTimeMillis() - batchT0;

                    if (batch.isEmpty()) break;

                    if (headers == null) {
                        headers = CsvWriterUtil.extractHeaders(batch);
                        CsvWriterUtil.writeHeaders(csvPrinter, headers);
                    }
                    CsvWriterUtil.writeRows(csvPrinter, batch, headers);

                    int sz = batch.size();
                    processedRows += sz;
                    lastId = getLastIdFromBatch(batch, pkColumn);

                    csvPrinter.flush();
                    long batchTime = System.currentTimeMillis() - batchT0;

                    exportMapper.updateTaskProgress(taskId, processedRows);

                    batchIndex++;
                    log.info("第 {} 批完成，查询 {} ms，本批总 {} ms，累计 {} 行",
                            batchIndex, queryTime, batchTime, processedRows);

                    if (sz < DEFAULT_BATCH_SIZE) break;
                }

                CsvWriterUtil.closeCsvPrinter(csvPrinter);
            }

            exportMapper.updateTaskCompleted(taskId, filePath, processedRows);
            log.info("异步导出完成！taskId: {}, {} 行, {} ms", taskId, processedRows, System.currentTimeMillis() - t0);

        } catch (Exception e) {
            log.error("异步导出失败，taskId: {}, 错误: {}", taskId, e.getMessage(), e);
            try { exportMapper.updateTaskFailed(taskId, e.getMessage()); } catch (Exception ignored) {}
        }
    }

    public ExportTask getTask(Long taskId) {
        return exportMapper.findTaskById(taskId);
    }

    public ExportTask submitTask(String tableName, int totalRows) {
        ExportTask task = new ExportTask();
        task.setTableName(tableName);
        task.setTotalRows(totalRows);
        exportMapper.insertTask(task);
        return task;
    }

    private Long getLastIdFromBatch(List<Map<String, Object>> batch, String pkColumn) {
        if (batch.isEmpty()) return 0L;
        Object idObj = batch.get(batch.size() - 1).get(pkColumn);
        if (idObj instanceof Number) return ((Number) idObj).longValue();
        throw new RuntimeException("主键列 " + pkColumn + " 不是数字类型，无法游标分页");
    }
}
