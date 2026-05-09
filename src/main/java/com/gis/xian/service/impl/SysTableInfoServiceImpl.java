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
    public List<Map<String, Object>> getTableData(String tableName, Integer limit) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        // 不限制记录数，返回所有数据
        return sysTableInfoMapper.getTableData(tableName, limit);
    }

    @Override
    public List<Map<String, Object>> getTableColumns(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        return sysTableInfoMapper.getTableColumns(tableName);
    }
}

