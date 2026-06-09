package com.gis.xian.service.pub;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.pub.RTriggerDTO;
import com.gis.xian.entity.pub.REvent;
import com.gis.xian.query.RQuery;

/**
 * @author zzw
 * @description: IREventService
 * @date 2026/6/8 下午6:01
 */
@DataSource("slave1")
public interface IREventService extends IService<REvent> {
    // 暴雨触发
    public RQuery trigger(RTriggerDTO trigger);

    // 删除暴雨事件
    public Boolean deletedById(Long Id);
}
