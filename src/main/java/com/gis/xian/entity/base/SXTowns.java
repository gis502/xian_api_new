package com.gis.xian.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 陕西省乡镇
 * @date 2026/5/26 上午9:56
 */
@Data
@TableName("base.sx_towns")
public class SXTowns {


    @TableField("\"SmUserID\"")
    private Integer SmUserID;
    @TableField("\"Field_SmUserID\"")
    private Integer Field_SmUserID;
    @TableField("\"CLASS\"")
    private String CLASS;
    @TableField("\"NAME\"")
    private String NAME;
    @TableField("\"PINYIN\"")
    private String PINYIN;
    @TableField("\"GNID\"")
    private String GNID;
    @TableField("\"XZNAME\"")
    private String XZNAME;
    @TableField("\"X\"")
    private Double X;
    @TableField("\"Y\"")
    private Double Y;
    @TableField("\"Geometry\"")
    @JsonSerialize(using = ToStringSerializer.class)
    private Geometry geometry;



}
