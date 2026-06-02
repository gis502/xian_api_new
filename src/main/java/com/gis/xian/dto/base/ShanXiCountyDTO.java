package com.gis.xian.dto.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 陕西省区县
 * @date 2026/5/26 上午9:48
 */
@Data
public class ShanXiCountyDTO {

    private String NAME;
    private Double X;
    private Double Y;
    @JsonSerialize(using = ToStringSerializer.class)
    private Geometry geometry;
    private Double distance;
}
