package com.gis.xian.mapper.export;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 导出专用Mapper
 * 使用独立的导出数据源，避免影响业务连接池
 */
@Mapper
public interface ExportMapper {

    /**
     * 分页查询表数据（用于大数据量导出）
     * @param tableName 表名
     * @param limit 每页条数
     * @param offset 偏移量
     * @return 数据列表
     */
    List<Map<String, Object>> queryTableData(
            @Param("tableName") String tableName,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    /**
     * 获取表的总记录数
     * @param tableName 表名
     * @return 总记录数
     */
    int getTableTotalCount(@Param("tableName") String tableName);

    /**
     * 流式查询表数据（适用于超大数据量）
     * 使用游标方式逐条读取，避免一次性加载到内存
     * @param tableName 表名
     * @param batchSize 批次大小
     */
    void streamQueryTable(
            @Param("tableName") String tableName,
            @Param("batchSize") int batchSize
    );

    /**
     * 游标分页查询（基于主键ID）
     * 替代传统的 LIMIT OFFSET，性能更稳定
     * @param tableName 表名
     * @param lastId 上一批最后一条记录的ID（第一批传null或0）
     * @param batchSize 批次大小
     * @return 数据列表
     */
    List<Map<String, Object>> cursorQueryByPk(
            @Param("tableName") String tableName,
            @Param("lastId") Long lastId,
            @Param("batchSize") int batchSize
    );

    /**
     * 获取表的主键字段名和类型
     * @param tableName 表名
     * @return 主键信息 Map，包含 column_name 和 data_type
     */
    Map<String, Object> getPrimaryKeyInfo(@Param("tableName") String tableName);
}
