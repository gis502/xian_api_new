package com.gis.xian.service.qgis.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.base.ShanXiCitiesDTO;
import com.gis.xian.entity.qgis.base.SXCities;

import java.util.List;

/**
 * @author zzw
 * @description: 陕西省市区
 * @date 2026/5/26 上午8:31
 */
@DataSource("slave1")
public interface IShanXiCitiesService extends IService<SXCities> {

    // 查询距离震中最近的市州
    public List<ShanXiCitiesDTO> getMostIntensityAreaCities(double lon, double lat);

}
