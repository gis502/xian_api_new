package com.gis.xian.service;

import com.gis.xian.entity.SysTableInfo;

import java.util.List;
import java.util.Map;

/**
 * 数据库表信息服务接口
 */
public interface SysTableInfoService {

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
    List<Map<String, Object>> getTableData(String tableName, Integer limit);

    /**
     * 获取表的字段信息
     * @param tableName 表名
     * @return 字段信息列表
     */
    List<Map<String, Object>> getTableColumns(String tableName);
}
