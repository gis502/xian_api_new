package com.gis.xian.service;

import com.gis.xian.config.DataSource;
import com.gis.xian.mapper.export.ExportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
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
     * 导出表数据
     * 使用 @DataSource("export") 切换到导出专用数据源
     */
    @DataSource("export")
    public List<Map<String, Object>> exportTableData(String tableName, int page, int pageSize) {
        log.info("开始导出表数据: {}, 页码: {}, 每页: {}", tableName, page, pageSize);

        int offset = (page - 1) * pageSize;
        return exportMapper.queryTableData(tableName, pageSize, offset);
    }

    /**
     * 获取表的总记录数
     */
    @DataSource("export")
    public int getTableTotalCount(String tableName) {
        return exportMapper.getTableTotalCount(tableName);
    }

    /**
     * 流式导出（适用于超大数据量）
     */
    @DataSource("export")
    public void streamExport(String tableName, int batchSize) {
        log.info("开始流式导出表数据: {}, 批次大小: {}", tableName, batchSize);
        exportMapper.streamQueryTable(tableName, batchSize);
    }

    /**
     * 验证表是否有自增整数主键
     * @param tableName 表名
     * @return true 如果有自增整数主键，否则抛出异常
     */
    @DataSource("export")
    public boolean validateIntegerPrimaryKey(String tableName) {
        Map<String, Object> pkInfo = exportMapper.getPrimaryKeyInfo(tableName);

        if (pkInfo == null || pkInfo.isEmpty()) {
            throw new IllegalArgumentException("表 [" + tableName + "] 没有主键，无法使用游标分页");
        }

        String columnName = (String) pkInfo.get("column_name");
        String dataType = (String) pkInfo.get("data_type");

        log.info("表 [{}] 的主键: 字段={}, 类型={}", tableName, columnName, dataType);

        // 检查是否是整数类型
        if (!isIntegerType(dataType)) {
            throw new IllegalArgumentException(
                    "表 [" + tableName + "] 的主键类型是 [" + dataType + "]，不是自增整数类型，暂不支持游标分页"
            );
        }

        // 检查字段名是否是 id
        if (!"id".equalsIgnoreCase(columnName)) {
            log.warn("表 [{}] 的主键字段名是 [{}]，不是标准的 'id'，可能需要调整查询逻辑", tableName, columnName);
        }

        return true;
    }

    /**
     * 判断数据类型是否为整数类型
     */
    private boolean isIntegerType(String dataType) {
        if (dataType == null) {
            return false;
        }
        String lowerType = dataType.toLowerCase();
        return lowerType.contains("integer")
                || lowerType.contains("int")
                || lowerType.contains("serial")
                || lowerType.contains("bigint")
                || lowerType.contains("smallint");
    }

    /**
     * 游标分页查询（基于主键ID）
     * @param tableName 表名
     * @param lastId 上一批最后一条记录的ID（第一批传null或0）
     * @param batchSize 批次大小
     * @return 数据列表
     */
    @DataSource("export")
    public List<Map<String, Object>> cursorQuery(String tableName, Long lastId, int batchSize) {
        log.debug("游标查询: 表={}, lastId={}, batchSize={}", tableName, lastId, batchSize);
        return exportMapper.cursorQueryByPk(tableName, lastId != null ? lastId : 0L, batchSize);
    }

    /**
     * 测试游标分页性能
     * @param tableName 表名
     * @param batchSize 批次大小
     * @param maxBatches 最大批次数
     */
    @DataSource("export")
    public void testCursorPagination(String tableName, int batchSize, int maxBatches) {
        log.info("========== 开始测试游标分页性能 ==========");
        log.info("表名: {}, 批次大小: {}, 最大批次数: {}", tableName, batchSize, maxBatches);

        // 1. 验证主键类型
        try {
            validateIntegerPrimaryKey(tableName);
        } catch (IllegalArgumentException e) {
            log.error("主键验证失败: {}", e.getMessage());
            throw e;
        }

        // 2. 循环查询，记录每批耗时
        Long lastId = 0L;
        int batchNum = 0;
        long totalTime = 0;

        while (batchNum < maxBatches) {
            batchNum++;
            long startTime = System.currentTimeMillis();

            List<Map<String, Object>> batch = cursorQuery(tableName, lastId, batchSize);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            totalTime += duration;

            if (batch == null || batch.isEmpty()) {
                log.info("第 {} 批: 无数据，结束查询", batchNum);
                break;
            }

            // 获取本批最后一条记录的ID
            Map<String, Object> lastRecord = batch.get(batch.size() - 1);
            Object idObj = lastRecord.get("id");
            if (idObj instanceof Number) {
                lastId = ((Number) idObj).longValue();
            } else {
                log.error("第 {} 批: 无法获取最后一条记录的ID", batchNum);
                break;
            }

            log.info("第 {} 批: 查询 {} 条记录, 耗时 {} ms, lastId={}",
                    batchNum, batch.size(), duration, lastId);
        }

        double avgTime = totalTime / (double) batchNum;
        log.info("========== 测试完成 ==========");
        log.info("总批次数: {}, 总耗时: {} ms, 平均每批耗时: {:.2f} ms",
                batchNum, totalTime, avgTime);
    }
}
