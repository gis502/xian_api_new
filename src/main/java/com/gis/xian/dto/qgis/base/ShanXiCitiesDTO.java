package com.gis.xian.dto.qgis.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 陕西市州
 * @date 2026/5/26 上午8:36
 */
@Data
public class ShanXiCitiesDTO {

    private String NAME;
    private Double X;
    private Double Y;
    @JsonSerialize(using = ToStringSerializer.class)
    private Geometry geometry;
    private Double distance;
}
