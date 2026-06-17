package com.gis.xian.entity.qgis.earthquake;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震影响场文件表
 * @date 2026/5/26 上午10:36
 */
@Data
@TableName("public.dz_influence")
public class EarthquakeInfluence {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long Id;
    @TableField("event")
    private String event;
    @TableField("name")
    private String name;
    @TableField("file")
    private String file;
    @TableField("path")
    private String path;
    @TableField("content")
    private String content;
    @TableField("intensity_column")
    private String intensityColumn;
    @TableField("source")
    private String source;
    @TableField("is_edit")
    private Integer isEdit;
    @TableField("is_pg")
    private Integer isPg;
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag = 0;
    @TableField(value = "create_by",fill = FieldFill.INSERT_UPDATE)
    private String createBy;
    @TableField(value = "create_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    @TableField(value = "create_dept",fill = FieldFill.INSERT_UPDATE)
    private Integer createDept;
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("remark")
    private String remark;
    @TableField("eqqueue_id")
    private String eqQueueId;


}

