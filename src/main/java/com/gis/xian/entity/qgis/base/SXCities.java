package com.gis.xian.entity.qgis.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 陕西省市区
 * @date 2026/5/26 上午8:31
 */
@Data
@TableName("base.sx_zb_city")
public class SXCities {

    @TableField("\"SmUserID\"")
    private Integer SmUserID;
    @TableField("\"Field_SmUserID\"")
    private Integer Field_SmUserID;
    @TableField("\"CLASS\"")
    private String CLASS;
    @TableField("\"NAME\"")
    private String NAME;
    @TableField("\"NAMEABBR\"")
    private String NAMEABBR;
    @TableField("\"X\"")
    private Double X;
    @TableField("\"Y\"")
    private Double Y;
    @TableField("\"Geometry\"")
    @JsonSerialize(using = ToStringSerializer.class)
    private Geometry geometry;


}
