package com.gis.xian.dto.qgis.rain;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 暴雨信息
 * @date 2026/6/8 下午4:49
 */
@Data
public class RainEventDTO {

    private String rainId;
    private String rainQueueId;
    private String disasterName;
    private String position;
    private LocalDateTime occurrenceTime;
    private String rainfall;
    private String duration;
    private Geometry geom;   //经纬度
    private Double longitude;
    private Double latitude;
    private String rainType;
}
