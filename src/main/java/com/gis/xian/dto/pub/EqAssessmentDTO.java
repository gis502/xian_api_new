package com.gis.xian.dto.pub;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震评估dto类
 * @date 2026/5/25 下午6:04
 */
@Data
public class EqAssessmentDTO {

    private String event;   // 地震编码
    private String eqQueueId;   // 评估编码
    private LocalDateTime eqTime; // 地震发生时间
    private Double longitude; // 经度
    private Double latitude; // 纬度
    private String eqAddr; // 地址
    private Double eqMagnitude; // 震级
    private Double eqDepth; // 深度
    private String eqFullName; // 震源名称
    private String eqName; // 震源名称
    private String eqType; // 震源类型

}
