package com.gis.xian.mapper.export;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface jichuExportMapper {

    List<Map<String, Object>> cursorQueryByPkColumn(
            @Param("tableName") String tableName,
            @Param("pkColumn") String pkColumn,
            @Param("lastId") Long lastId,
            @Param("batchSize") int batchSize
    );

    int getTableTotalCount(@Param("tableName") String tableName);

    Map<String, Object> getPrimaryKeyInfo(@Param("tableName") String tableName);
}
