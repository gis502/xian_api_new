package com.gis.xian.dto.qgis.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 活动断层DTO类
 * @date 2026/5/26 上午10:20
 */
@Data
public class ActiveFaultDTO {

    private String name;        // 断层名称
    private Integer strike;     // 走向
    private Integer direction;  // 倾向
    private Integer clination;  // 倾角
    private Double shapeLen;    //形状长度
    @JsonSerialize(using = ToStringSerializer.class)
    private Geometry geometry;  // 范围
    private Double distance;
}
