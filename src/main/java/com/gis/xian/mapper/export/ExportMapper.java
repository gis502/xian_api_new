package com.gis.xian.mapper.export;

import com.gis.xian.entity.ExportTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExportMapper {

    // ---- 数据查询 ----
    List<Map<String, Object>> queryTableData(
            @Param("tableName") String tableName,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int getTableTotalCount(@Param("tableName") String tableName);

    List<Map<String, Object>> cursorQueryByPkColumn(
            @Param("tableName") String tableName,
            @Param("pkColumn") String pkColumn,
            @Param("lastId") Long lastId,
            @Param("batchSize") int batchSize
    );

    Map<String, Object> getPrimaryKeyInfo(@Param("tableName") String tableName);

    // ---- 导出任务管理 ----
    int insertTask(ExportTask task);
    int updateTaskProgress(@Param("id") Long id, @Param("processedRows") int processedRows);
    int updateTaskCompleted(@Param("id") Long id, @Param("filePath") String filePath, @Param("totalRows") int totalRows);
    int updateTaskFailed(@Param("id") Long id, @Param("errorMessage") String errorMessage);
    ExportTask findTaskById(@Param("id") Long id);
}
