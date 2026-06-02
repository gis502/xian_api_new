package com.gis.xian.dto.dzxx;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

/**
 * @author zzw
 * @description: 地震影响场DTO
 * @date 2026/5/26 上午10:11
 */
@Data
public class DZXXInfluenceDTO {

    private Geometry geom;
    private String eqQueueId;
    private String event;
    private String eqName;
    private Integer inty;   // 烈度值
    private String sInty;
    private double area;
    private double longUranium;
    private double shortUranium;
    private double direction;
    private double longitude;
    private double latitude;
}
