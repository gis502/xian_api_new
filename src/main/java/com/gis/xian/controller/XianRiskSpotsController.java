package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.entity.XianRiskSpotsBasePoint;
import com.gis.xian.entity.XianRiskSpotsPointDetail;
import com.gis.xian.service.XianRiskSpotsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/risk-spots")
public class XianRiskSpotsController {

    @Resource
    private XianRiskSpotsService xianRiskSpotsService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianRiskSpotsBasePoint>> getBasePoints() {
        return ApiResponse.ok(xianRiskSpotsService.getBasePoints());
    }

    @GetMapping("point-detail/{id}")
    public ApiResponse<XianRiskSpotsPointDetail> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianRiskSpotsService.getPointDetailById(Long.parseLong(id)));
    }
}
