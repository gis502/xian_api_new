package com.gis.xian.mapper;

import com.gis.xian.entity.SysTableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysTableInfoMapper {

    /**
     * 获取所有数据库表信息
     * @return 数据库表信息列表
     */
    List<SysTableInfo> getAllTables();

    /**
     * 根据表名查询表的具体数据记录
     * @param tableName 表名
     * @param limit 限制返回的记录数
     * @return 表数据记录列表
     */
    List<Map<String, Object>> getTableData(@Param("tableName") String tableName, @Param("limit") Integer limit);

    /**
     * 获取表的字段信息
     * @param tableName 表名
     * @return 字段信息列表
     */
    List<Map<String, Object>> getTableColumns(@Param("tableName") String tableName);
}

