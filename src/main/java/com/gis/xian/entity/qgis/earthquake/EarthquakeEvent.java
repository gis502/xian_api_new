package com.gis.xian.entity.qgis.earthquake;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震事件表
 * @date 2026/5/25 下午3:24
 */
@Data
@TableName("public.dz_eqevent")
public class EarthquakeEvent {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long Id;
    @TableField("event")
    private String event;
    @TableField("eq_time")
    private LocalDateTime eqTime;
    @TableField("eq_addr")
    private String eqAddr;
    @TableField("longitude")
    private Double longitude;
    @TableField("latitude")
    private Double latitude;
    @TableField("eq_magnitude")
    private Double eqMagnitude;
    @TableField("eq_depth")
    private Double eqDepth;
    @TableField("eq_full_name")
    private String eqFullName;
    @TableField("eq_name")
    private String eqName;
    @TableField("eq_type")
    private String eqType;
    @TableField("eq_addr_code")
    private String eqAddrCode;
    @TableField("town_code")
    private String townCode;
    @TableField("distance")
    private Double distance;
    @TableField("region")
    private Integer region;
    @TableField("damage_type")
    private Integer damageType;
    @TableField("direction")
    private Double direction;
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag = 0;
    @TableField(value = "create_by",fill = FieldFill.INSERT_UPDATE)
    private String createBy;
    @TableField(value = "create_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    @TableField(value = "create_dept",fill = FieldFill.INSERT_UPDATE)
    private String createDept;
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField("remark")
    private String remark;
    @TableField("xyjb")
    private String xyjb;    // 相应级别
    @TableField("source")
    private Integer source;

}
