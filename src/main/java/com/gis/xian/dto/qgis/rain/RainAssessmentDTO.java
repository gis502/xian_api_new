package com.gis.xian.dto.qgis.rain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 评估DTO类
 * @date 2026/5/26 下午5:51
 */
@Data
public class RainAssessmentDTO implements Serializable {

    private String rainId;
    private String rainQueueId;
    private String rainName;    // 暴雨名称
    private String position;    // 区县
    private double longitude;   // 经度
    private double latitude;    // 纬度
    private String rainfall;    // 降雨量
    private String duration;    // 已持续时间
    private String rainType;    // 暴雨类型
    private LocalDateTime occurrenceTime;   // 发生时间


}
