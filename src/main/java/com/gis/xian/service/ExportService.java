package com.gis.xian.service;

import com.gis.xian.config.DataSource;
import com.gis.xian.mapper.export.ExportMapper;
import com.gis.xian.utils.safety.CsvWriterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 导出服务
 * 使用独立的导出数据源，避免影响业务连接池
 */
@Service
@Slf4j
public class ExportService {

    @Resource
    private ExportMapper exportMapper;

    /**
     * 默认批次大小（每批查询5万条）
     */
    private static final int DEFAULT_BATCH_SIZE = 50000;

    /**
     * 游标分页测试接口
     * @param tableName 表名
     * @return 第一批数据
     */
    @DataSource("export")
    public Map<String, Object> testCursorPagination(String tableName) {
        log.info("开始测试游标分页，表名: {}", tableName);
        
        long startTime = System.currentTimeMillis();
        
        // 查询第一批数据（lastId=0）
        List<Map<String, Object>> batch = exportMapper.cursorQueryByPk(tableName, 0L, DEFAULT_BATCH_SIZE);
        
        long endTime = System.currentTimeMillis();
        log.info("第一批数据查询完成，数量: {}, 耗时: {} ms", batch.size(), (endTime - startTime));
        
        // 返回统计信息
        return Map.of(
            "tableName", tableName,
            "batchSize", batch.size(),
            "elapsedMs", (endTime - startTime),
            "hasNext", !batch.isEmpty() && batch.size() >= DEFAULT_BATCH_SIZE
        );
    }

    /**
     * 流式导出表数据到CSV文件
     * @param tableName 表名
     * @param outputFilePath 输出文件路径
     * @return 导出统计信息
     */
    @DataSource("export")
    public Map<String, Object> streamExportToCsv(String tableName, String outputFilePath) {
        log.info("开始流式导出，表名: {}, 输出路径: {}", tableName, outputFilePath);
        
        long totalStartTime = System.currentTimeMillis();
        File outputFile = new File(outputFilePath);
        
        try (OutputStream outputStream = CsvWriterUtil.createBomOutputStream(outputFile)) {
            CSVPrinter csvPrinter = CsvWriterUtil.createCsvPrinter(outputStream);
            
            int batchIndex = 0;
            Long lastId = 0L;
            int totalRows = 0;
            List<String> headers = null;
            
            while (true) {
                long batchStartTime = System.currentTimeMillis();
                
                // 游标分页查询
                List<Map<String, Object>> batch = exportMapper.cursorQueryByPk(tableName, lastId, DEFAULT_BATCH_SIZE);
                
                if (batch.isEmpty()) {
                    log.info("第{}批数据为空，导出结束", batchIndex + 1);
                    break;
                }
                
                // 第一批数据时，提取并写入表头
                if (headers == null) {
                    headers = CsvWriterUtil.extractHeaders(batch);
                    CsvWriterUtil.writeHeaders(csvPrinter, headers);
                    log.info("CSV表头已写入，列数: {}", headers.size());
                }
                
                // 批量写入CSV
                CsvWriterUtil.writeRows(csvPrinter, batch, headers);
                
                // 更新统计信息
                int batchSize = batch.size();
                totalRows += batchSize;
                lastId = getLastIdFromBatch(batch);
                
                long batchEndTime = System.currentTimeMillis();
                batchIndex++;
                
                log.info("第{}批导出完成，本批数量: {}, 累计数量: {}, 耗时: {} ms", 
                        batchIndex, batchSize, totalRows, (batchEndTime - batchStartTime));
                
                // 如果本批数据小于批次大小，说明已经是最后一批
                if (batchSize < DEFAULT_BATCH_SIZE) {
                    log.info("已达到最后一批数据");
                    break;
                }
                
                // TODO: 这里可以接入进度更新机制（下一步实现）
                // updateProgress(tableName, totalRows, batchIndex);
            }
            
            // 关闭CSV打印机（会自动flush）
            CsvWriterUtil.closeCsvPrinter(csvPrinter);
            
            long totalEndTime = System.currentTimeMillis();
            long totalTime = totalEndTime - totalStartTime;
            
            log.info("导出完成！总行数: {}, 总耗时: {} ms, 平均速度: {} 行/秒", 
                    totalRows, totalTime, totalRows * 1000 / totalTime);
            
            return Map.of(
                "success", true,
                "tableName", tableName,
                "outputPath", outputFilePath,
                "totalRows", totalRows,
                "totalBatches", batchIndex,
                "elapsedMs", totalTime,
                "avgSpeed", totalRows * 1000 / totalTime
            );
            
        } catch (Exception e) {
            log.error("导出失败，表名: {}, 错误: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("导出失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从批次数据中提取最后一个ID
     * @param batch 批次数据
     * @return 最后一个ID
     */
    private Long getLastIdFromBatch(List<Map<String, Object>> batch) {
        if (batch.isEmpty()) {
            return 0L;
        }
        
        Map<String, Object> lastRow = batch.get(batch.size() - 1);
        Object idObj = lastRow.get("id");
        
        if (idObj instanceof Number) {
            return ((Number) idObj).longValue();
        }
        
        // 如果没有id字段，尝试其他常见主键字段
        for (String possibleIdField : new String[]{"gid", "objectid", "oid"}) {
            Object val = lastRow.get(possibleIdField);
            if (val instanceof Number) {
                return ((Number) val).longValue();
            }
        }
        
        throw new RuntimeException("未找到自增整数主键字段（id/gid/objectid/oid），无法使用游标分页");
    }
}
