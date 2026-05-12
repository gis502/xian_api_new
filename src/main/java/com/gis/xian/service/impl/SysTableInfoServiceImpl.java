package com.gis.xian.service.impl;

import com.gis.xian.entity.SysTableInfo;
import com.gis.xian.mapper.SysTableInfoMapper;
import com.gis.xian.service.SysTableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据库表信息服务实现类
 */
@Service
public class SysTableInfoServiceImpl implements SysTableInfoService {

    @Autowired
    private SysTableInfoMapper sysTableInfoMapper;

    @Override
    public List<SysTableInfo> getAllTables() {
        return sysTableInfoMapper.getAllTables();
    }

    @Override
    public List<Map<String, Object>> getTableData(String tableName, Integer limit, Integer offset) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        return sysTableInfoMapper.getTableData(tableName, limit, offset);
    }

    @Override
    public int getTableTotalCount(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        return sysTableInfoMapper.getTableTotalCount(tableName);
    }

    @Override
    public List<Map<String, Object>> getTableColumns(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        return sysTableInfoMapper.getTableColumns(tableName);
    }

    @Override
    public void updateTableInfo(String oldTableName, String newTableName, String newComment) {
        if (oldTableName == null || oldTableName.trim().isEmpty()) {
            throw new IllegalArgumentException("原表名不能为空");
        }
        
        if (newTableName == null && newComment == null) {
            throw new IllegalArgumentException("新表名和新描述至少需要提供一个");
        }
        
        sysTableInfoMapper.updateTableInfo(oldTableName, newTableName, newComment);
    }

    @Override
    public void updateTableData(String tableName, Map<String, Object> whereConditions, Map<String, Object> updateData) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        if (whereConditions == null || whereConditions.isEmpty()) {
            throw new IllegalArgumentException("WHERE条件不能为空");
        }
        if (updateData == null || updateData.isEmpty()) {
            throw new IllegalArgumentException("更新数据不能为空");
        }
        
        sysTableInfoMapper.updateTableData(tableName, whereConditions, updateData);
    }
}

