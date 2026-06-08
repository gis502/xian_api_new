package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.pub.DZProductDTO;
import com.gis.xian.query.ProductQuery;
import com.gis.xian.service.pub.IDZProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zzw
 * @description: 产出结果控制类
 * @date 2026/6/4 下午3:38
 */
public class DZProductController {

    @Resource
    private IDZProductService idzProductService;

    @PostMapping("/product")
    public ApiResponse<List<DZProductDTO>> getProducts(@RequestBody ProductQuery query) {
        List<DZProductDTO> products = idzProductService.getProducts(query);
        return ApiResponse.ok(products);
    }
}
