package com.gis.xian.dto.pub;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: RTriggerDTO
 * @date 2026/6/8 下午4:44
 */
@Data
public class RTriggerDTO {

    private String position;    // 区县
    private double longitude;   // 经度
    private double latitude;    // 纬度
    private String rainfall;    // 降雨量
    private String duration;    // 已持续时间
    private String rainType;    // 暴雨类型
    private LocalDateTime occurrenceTime;   // 发生时间
}
