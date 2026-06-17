package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.qgis.earthquake.EarthquakeProductDTO;
import com.gis.xian.dto.qgis.base.ProductQuery;
import com.gis.xian.service.qgis.earthquake.IEarthquakeProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzw
 * @description: 产出结果控制类
 * @date 2026/6/4 下午3:38
 */
@Slf4j
@RestController
@RequestMapping("/open")
public class DZProductController {

    @Resource
    private IEarthquakeProductService IEarthquakeProductService;

    @PostMapping("/product")
    public ApiResponse<List<EarthquakeProductDTO>> getProducts(@RequestBody ProductQuery query) {
        List<EarthquakeProductDTO> products = IEarthquakeProductService.getProducts(query);
        return ApiResponse.ok(products);
    }
}
