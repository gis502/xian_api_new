package com.gis.xian.service.pub;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.dto.pub.RAssessmentDTO;
import com.gis.xian.entity.pub.DZEqQueue;

/**
 * @author zzw
 * @description: 地震评估
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IDZEqQueueService extends IService<DZEqQueue> {

    // 地震评估
    public void assess(EqAssessmentDTO assess);

    // 暴雨评估
    public void assess(RAssessmentDTO assess);

    // 更新评估进度、状态
    public void updated(String event, String queueId, double progress, int state);
}
