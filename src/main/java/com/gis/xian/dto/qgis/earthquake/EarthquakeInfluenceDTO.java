package com.gis.xian.dto.qgis.earthquake;

import lombok.Data;

/**
 * @author zzw
 * @description: 影响场文件
 * @date 2026/5/26 上午10:46
 */
@Data
public class EarthquakeInfluenceDTO {

    private String eqQueueId;
    private String event;
    private String name;
    private String file;
    private String path;
    private String content;
    private String intensityColumn;
    private String source;
    private Integer isEdit;
    private Integer isPg;

}
