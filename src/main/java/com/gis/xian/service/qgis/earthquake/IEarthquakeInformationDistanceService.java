package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.entity.qgis.earthquake.EarthquakeDistance;

/**
 * @ClassName IDZXXDistanceService
 * @Description 震中到省市县乡距离
 * @Author zzw
 * @Date 2026/5/25 12:06
 */
@DataSource("slave1")
public interface IEarthquakeInformationDistanceService extends IService<EarthquakeDistance> {

    // 处理震中到省市区镇
    public void handle(double lon, double lat, String eqQueueId);

}
