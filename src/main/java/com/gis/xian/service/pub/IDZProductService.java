package com.gis.xian.service.pub;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.pub.DZProductDTO;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.dto.pub.RAssessmentDTO;
import com.gis.xian.entity.pub.DZProduct;
import com.gis.xian.query.ProductQuery;

import java.util.List;

/**
 * @author zzw
 * @description: IDZProductService
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IDZProductService extends IService<DZProduct> {

    // qgis 地震制图服务
    public void makeEarthquakeMaps(EqAssessmentDTO assess);

    // qgis 暴雨制图服务
    public void makeRainstormMaps(RAssessmentDTO assess);

    // 获取产品
    public List<DZProductDTO> getProducts(ProductQuery query);
}
