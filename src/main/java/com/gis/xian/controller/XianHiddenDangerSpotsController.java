package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.entity.XianHiddenDangerSpotsBasePoint;
import com.gis.xian.entity.XianHiddenDangerSpotsPointDetail;
import com.gis.xian.service.XianHiddenDangerSpotsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hidden-danger-spots")
public class XianHiddenDangerSpotsController extends BaseController{

    @Resource
    private XianHiddenDangerSpotsService xianHiddenDangerSpotsService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianHiddenDangerSpotsBasePoint>> getBasePoints(@RequestParam String disasterType) {
        return ApiResponse.ok(xianHiddenDangerSpotsService.getBasePoints(disasterType));
    }

    @GetMapping("point-detail/{id}")
    public ApiResponse<XianHiddenDangerSpotsPointDetail> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianHiddenDangerSpotsService.getPointDetailById(Long.parseLong(id)));
    }
}
