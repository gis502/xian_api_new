package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeProductDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.dto.qgis.rain.RainAssessmentDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeProduct;
import com.gis.xian.dto.qgis.base.ProductQuery;

import java.util.List;

/**
 * @author zzw
 * @description: IDZProductService
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IEarthquakeProductService extends IService<EarthquakeProduct> {

    // qgis 地震制图服务
    public void makeEarthquakeMaps(EarthquakeAssessmentDTO assess);

    // qgis 暴雨制图服务
    public void makeRainstormMaps(RainAssessmentDTO assess);

    // 获取产品
    public List<EarthquakeProductDTO> getProducts(ProductQuery query);
}
