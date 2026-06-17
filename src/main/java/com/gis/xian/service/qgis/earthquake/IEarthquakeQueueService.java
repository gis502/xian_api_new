package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.dto.qgis.rain.RainAssessmentDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeQueue;

/**
 * @author zzw
 * @description: 地震评估
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IEarthquakeQueueService extends IService<EarthquakeQueue> {

    // 地震评估
    public void assess(EarthquakeAssessmentDTO assess);

    // 暴雨评估
    public void assess(RainAssessmentDTO assess);

    // 更新评估进度、状态
    public void updated(String event, String queueId, double progress, int state);
}
