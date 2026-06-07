package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.gis.xian.entity.SysTableInfo;
import com.gis.xian.mapper.SysTableInfoMapper;
import com.gis.xian.service.SysTableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 数据库表信息服务实现类
 */
@Service
public class SysTableInfoServiceImpl implements SysTableInfoService {

    @Autowired
    private SysTableInfoMapper sysTableInfoMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${init.data.table-info.list}")
    private String tableInfoListKey;

    @Override
    public List<SysTableInfo> getAllTables() {
        // 1. 先从Redis缓存中获取
        Object cached = redisTemplate.opsForValue().get(tableInfoListKey);
        if (cached != null) {
            return JSON.parseArray(cached.toString(), SysTableInfo.class);
        }
        
        // 2. 缓存未命中，查询数据库
        List<SysTableInfo> tables = sysTableInfoMapper.getAllTables();
        
        // 3. 写入Redis缓存（5分钟过期）
        if (tables != null && !tables.isEmpty()) {
            redisTemplate.opsForValue().set(tableInfoListKey, JSON.toJSONString(tables), 5, TimeUnit.MINUTES);
        }
        
        return tables;
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
        
        // 1. 先从Redis缓存中获取（表结构几乎不变，缓存1小时）
        String cacheKey = "table:columns:" + tableName;
        Object cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            return JSON.parseObject(cached.toString(), new TypeReference<List<Map<String, Object>>>(){});
        }
        
        // 2. 缓存未命中，查询数据库
        List<Map<String, Object>> columns = sysTableInfoMapper.getTableColumns(tableName);
        
        // 3. 写入Redis缓存（1小时过期）
        if (columns != null && !columns.isEmpty()) {
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(columns), 1, TimeUnit.HOURS);
        }
        
        return columns;
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
        
        // 清除相关缓存
        redisTemplate.delete(tableInfoListKey);
        redisTemplate.delete("table:columns:" + oldTableName);
        if (newTableName != null) {
            redisTemplate.delete("table:columns:" + newTableName);
        }
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

    @Override
    public void insertTableData(String tableName, Map<String, Object> insertData) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        if (insertData == null || insertData.isEmpty()) {
            throw new IllegalArgumentException("新增数据不能为空");
        }
        
        // 移除id字段（由数据库自动生成）
        insertData.remove("id");
        
        sysTableInfoMapper.insertTableData(tableName, insertData);
        
        // 清除表数据缓存
        String cacheKeyPattern = "xian:table:data:" + tableName + ":*";
        redisTemplate.delete(cacheKeyPattern);
    }

    @Override
    public void deleteTableData(String tableName, List<Object> ids) {
        if (tableName == null || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("表名不能为空");
        }
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("要删除的ID列表不能为空");
        }
        
        sysTableInfoMapper.deleteTableData(tableName, ids);
        
        // 清除表数据缓存
        String cacheKeyPattern = "xian:table:data:" + tableName + ":*";
        redisTemplate.delete(cacheKeyPattern);
    }
}

