package com.gis.xian.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * CSV文件流式写入工具类
 * 核心原则：读一批、写一批、释放内存
 */
public class CsvWriterUtil {

    /**
     * UTF-8 BOM头（解决Excel打开中文乱码问题）
     */
    private static final byte[] UTF8_BOM = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    /**
     * CSV格式配置（RFC4180标准，字段用双引号包裹）
     */
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setDelimiter(',')
            .setQuote('"')
            .setRecordSeparator("\n")
            .setNullString("")
            .setIgnoreHeaderCase(true)
            .setTrim(true)
            .build();

    /**
     * 缓冲区大小（64KB）
     */
    private static final int BUFFER_SIZE = 64 * 1024;

    /**
     * 创建带BOM头的输出流
     * @param file 目标文件
     * @return BufferedOutputStream
     * @throws IOException IO异常
     */
    public static OutputStream createBomOutputStream(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        // 写入UTF-8 BOM头
        fos.write(UTF8_BOM);
        fos.flush();
        return new BufferedOutputStream(fos, BUFFER_SIZE);
    }

    /**
     * 创建CSV打印机（自动处理BOM头）
     * @param outputStream 输出流
     * @return CSVPrinter
     * @throws IOException IO异常
     */
    public static CSVPrinter createCsvPrinter(OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        return new CSVPrinter(writer, CSV_FORMAT);
    }

    /**
     * 写入CSV表头
     * @param csvPrinter CSV打印机
     * @param headers 表头列表
     * @throws IOException IO异常
     */
    public static void writeHeaders(CSVPrinter csvPrinter, List<String> headers) throws IOException {
        for (String header : headers) {
            csvPrinter.print(header);
        }
        csvPrinter.println();
    }

    /**
     * 写入一行数据（Map形式）
     * @param csvPrinter CSV打印机
     * @param row 行数据（key为列名，value为值）
     * @param headers 列顺序
     * @throws IOException IO异常
     */
    public static void writeRow(CSVPrinter csvPrinter, Map<String, Object> row, List<String> headers) throws IOException {
        for (String header : headers) {
            Object value = row.get(header);
            if (value == null) {
                csvPrinter.print("");
            } else {
                csvPrinter.print(value.toString());
            }
        }
        csvPrinter.println();
    }

    /**
     * 批量写入多行数据
     * @param csvPrinter CSV打印机
     * @param rows 行数据列表
     * @param headers 列顺序
     * @throws IOException IO异常
     */
    public static void writeRows(CSVPrinter csvPrinter, List<Map<String, Object>> rows, List<String> headers) throws IOException {
        for (Map<String, Object> row : rows) {
            writeRow(csvPrinter, row, headers);
        }
    }

    /**
     * 刷新并关闭CSV打印机
     * @param csvPrinter CSV打印机
     * @throws IOException IO异常
     */
    public static void closeCsvPrinter(CSVPrinter csvPrinter) throws IOException {
        if (csvPrinter != null) {
            csvPrinter.flush();
            csvPrinter.close();
        }
    }

    /**
     * 获取文件的列名列表（从第一批数据中提取）
     * @param firstBatch 第一批数据
     * @return 列名列表（按字母顺序排序，保证一致性）
     */
    public static List<String> extractHeaders(List<Map<String, Object>> firstBatch) {
        if (firstBatch == null || firstBatch.isEmpty()) {
            return List.of();
        }

        // 从第一行数据中提取所有key，并按字母顺序排序
        return firstBatch.get(0).keySet().stream()
                .sorted()
                .toList();
    }
}
