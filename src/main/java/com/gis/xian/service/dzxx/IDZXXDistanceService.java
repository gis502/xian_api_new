package com.gis.xian.service.dzxx;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.entity.dzxx.DZXXDistance;

/**
 * @ClassName IDZXXDistanceService
 * @Description 震中到省市县乡距离
 * @Author zzw
 * @Date 2026/5/25 12:06
 */
@DataSource("slave1")
public interface IDZXXDistanceService extends IService<DZXXDistance> {

    // 处理震中到省市区镇
    public void handle(double lon, double lat, String eqQueueId);

}
