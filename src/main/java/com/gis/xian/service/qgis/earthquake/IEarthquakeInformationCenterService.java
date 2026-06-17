package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeCenterDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeCenter;

/**
 * @author zzw
 * @description: 地震信息
 * @date 2026/5/25 下午5:01
 */
@DataSource("slave1")
public interface IEarthquakeInformationCenterService extends IService<EarthquakeCenter> {

    // 地震触发
    public void handle(EarthquakeCenterDTO trigger);

}
