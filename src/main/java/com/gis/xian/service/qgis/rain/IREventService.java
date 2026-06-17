package com.gis.xian.service.qgis.rain;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.rain.RainTriggerDTO;
import com.gis.xian.entity.qgis.rain.RainEvent;
import com.gis.xian.dto.qgis.rain.RainQuery;

/**
 * @author zzw
 * @description: IREventService
 * @date 2026/6/8 下午6:01
 */
@DataSource("slave1")
public interface IREventService extends IService<RainEvent> {
    // 暴雨触发
    public RainQuery trigger(RainTriggerDTO trigger);

    // 删除暴雨事件
    public Boolean deletedById(Long Id);
}
