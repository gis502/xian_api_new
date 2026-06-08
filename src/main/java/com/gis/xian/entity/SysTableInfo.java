package com.gis.xian.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 * 数据库表信息
 */
@Data
public class SysTableInfo {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释/描述
     */
    private String tableComment;

    /**
     * 行数（记录数）
     */
    private Long rowCount;

    /**
     * 数据大小
     */
    private String dataSize;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
