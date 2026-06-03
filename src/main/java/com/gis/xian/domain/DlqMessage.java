package com.gis.xian.domain;

import com.gis.xian.params.QgisArgs;
import lombok.Builder;
import lombok.Data;

/**
 * @author zzw
 * @description: 死信队列参数
 * @date 2026/5/26 下午5:04
 */
@Data
@Builder
public class DlqMessage {

    private QgisArgs qgisArgs;       // 原始参数
    private String failReason;      // 失败原因
    private Long failTime;          // 失败时间戳
    private Integer retryCount;     // 已重试次数


}
