package com.gis.xian.dto.dzxx;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zzw
 * @description: 地震信息触发类
 * @date 2026/5/25 下午5:19
 */
@Data
public class DZXXCenterDTO {

    private String event;
    private LocalDateTime eqTime;
    private Double longitude;
    private Double latitude;
    private String eqAddr;
    private Double eqMagnitude;
    private Double eqDepth;
    private String eqFullName;
    private String eqName;
    private String eqType;

}
