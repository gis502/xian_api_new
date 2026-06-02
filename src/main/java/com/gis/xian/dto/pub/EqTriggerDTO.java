package com.gis.xian.dto.pub;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震触发DTO类
 * @date 2026/5/25 下午4:52
 */
@Data
public class EqTriggerDTO {

    private String eqName; // 地震名称

    private String eqAddr; // 震发地点

    private LocalDateTime eqTime; // 震发时间

    private Double longitude; // 经度

    private Double latitude; // 纬度

    private Double eqDepth; // 震源深度

    private Double eqMagnitude; // 震级

    private String eqFullName; // 地震全称

    private String eqType; // 地震类型（T:测试，Z:正式，Y:演练）
}
