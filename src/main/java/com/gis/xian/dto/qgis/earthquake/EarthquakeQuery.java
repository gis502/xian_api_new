package com.gis.xian.dto.qgis.earthquake;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzw
 * @description: 地震请求参数
 * @date 2026/5/25 下午4:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EarthquakeQuery {

    private String event;
    private String eqQueueId;

}
