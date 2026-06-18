package com.gis.xian.service;

import com.gis.xian.mapper.export.jichuExportMapper;
import com.gis.xian.utils.safety.CsvWriterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import jakarta.annotation.Resource;
import java.io.*;
import java.sql.*;
import java.util.Map;

@Service
@Slf4j
public class ExportService {

    @Resource
    private jichuExportMapper exportMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private TransactionTemplate transactionTemplate;

    private static final byte[] UTF8_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    public void streamExportToStream(String tableName, OutputStream outputStream) {
        log.info("导出开始，表名: {}", tableName);
        long t0 = System.currentTimeMillis();

        try {
            transactionTemplate.execute(status -> {
                jdbcTemplate.execute((Connection con) -> {
                    String sql = "SELECT * FROM " + tableName + " ORDER BY " + getPkColumn(tableName);
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setFetchSize(5000);
                        try (ResultSet rs = ps.executeQuery()) {
                            ResultSetMetaData meta = rs.getMetaData();
                            int colCount = meta.getColumnCount();

                            outputStream.write(UTF8_BOM);
                            CSVPrinter csvPrinter = CsvWriterUtil.createCsvPrinter(outputStream);

                            for (int i = 1; i <= colCount; i++) {
                                csvPrinter.print(meta.getColumnName(i));
                            }
                            csvPrinter.println();

                            int rowCount = 0;
                            long lastLog = System.currentTimeMillis();
                            while (rs.next()) {
                                for (int i = 1; i <= colCount; i++) {
                                    csvPrinter.print(rs.getString(i));
                                }
                                csvPrinter.println();
                                rowCount++;

                                if (rowCount % 5000 == 0) {
                                    csvPrinter.flush();
                                    long now = System.currentTimeMillis();
                                    log.info("已导出 {} 行, 本段 {} ms", rowCount, now - lastLog);
                                    lastLog = now;
                                }
                            }
                            csvPrinter.flush();
                            CsvWriterUtil.closeCsvPrinter(csvPrinter);

                            log.info("导出完成！{} 行, {} ms", rowCount, System.currentTimeMillis() - t0);
                        }
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
                return null;
            });
        } catch (Exception e) {
            log.error("导出失败，表名: {}, 错误: {}", tableName, e.getMessage(), e);
            throw new RuntimeException("导出失败: " + e.getMessage(), e);
        }
    }

    private String getPkColumn(String tableName) {
        try {
            Map<String, Object> pk = exportMapper.getPrimaryKeyInfo(tableName);
            if (pk != null && pk.get("column_name") != null) {
                return pk.get("column_name").toString();
            }
        } catch (Exception e) {
            log.warn("获取主键失败: {}", e.getMessage());
        }
        return "id";
    }
}
