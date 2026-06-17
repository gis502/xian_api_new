package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeTriggerDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeEvent;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;

/**
 * @author zzw
 * @description: QGIS地震事件触发
 * @date 2026/5/25 下午3:23
 */
@DataSource("slave1")
public interface IEarthquakeEventService extends IService<EarthquakeEvent> {

    // 地震触发
    public EarthquakeQuery trigger(EarthquakeTriggerDTO trigger);

    // 删除地震事件
    public Boolean deletedById(Long Id);
}
