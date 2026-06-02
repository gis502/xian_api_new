package com.gis.xian.service.pub;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.pub.EqTriggerDTO;
import com.gis.xian.entity.pub.DZEqEvent;
import com.gis.xian.query.EqQuery;

/**
 * @author zzw
 * @description: QGIS地震事件触发
 * @date 2026/5/25 下午3:23
 */
@DataSource("slave1")
public interface IDZEqEventService extends IService<DZEqEvent> {

    // 地震触发
    public EqQuery trigger(EqTriggerDTO trigger);

    // 删除地震事件
    public Boolean deletedById(Long Id);
}
