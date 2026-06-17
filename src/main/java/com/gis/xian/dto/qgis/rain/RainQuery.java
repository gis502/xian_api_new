package com.gis.xian.dto.qgis.rain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzw
 * @description: 暴雨参数
 * @date 2026/6/8 下午4:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RainQuery {

    private String rainId;
    private String rainQueueId;

}
