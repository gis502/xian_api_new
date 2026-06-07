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
     * 根据表名查询表的具体数据记录（分页）
     * @param tableName 表名
     * @param limit 每页条数
     * @param offset 偏移量
     * @return 表数据记录列表
     */
    List<Map<String, Object>> getTableData(@Param("tableName") String tableName, 
                                           @Param("limit") Integer limit, 
                                           @Param("offset") Integer offset);

    /**
     * 获取表的总记录数
     * @param tableName 表名
     * @return 总记录数
     */
    int getTableTotalCount(@Param("tableName") String tableName);

    /**
     * 获取表的字段信息
     * @param tableName 表名
     * @return 字段信息列表
     */
    List<Map<String, Object>> getTableColumns(@Param("tableName") String tableName);

    /**
     * 修改表信息
     * @param oldTableName 原表名
     * @param newTableName 新表名
     * @param newComment 新表描述
     */
    void updateTableInfo(@Param("oldTableName") String oldTableName,
                         @Param("newTableName") String newTableName,
                         @Param("newComment") String newComment);

    /**
     * 修改表数据
     * @param tableName 表名
     * @param whereConditions WHERE条件
     * @param updateData 更新数据
     */
    void updateTableData(@Param("tableName") String tableName,
                         @Param("whereConditions") Map<String, Object> whereConditions,
                         @Param("updateData") Map<String, Object> updateData);

    /**
     * 新增表数据
     * @param tableName 表名
     * @param insertData 新增数据
     */
    void insertTableData(@Param("tableName") String tableName,
                         @Param("insertData") Map<String, Object> insertData);

    /**
     * 删除表数据（物理删除）
     * @param tableName 表名
     * @param ids 要删除的记录主键ID列表
     */
    void deleteTableData(@Param("tableName") String tableName,
                         @Param("ids") List<Object> ids);
}

