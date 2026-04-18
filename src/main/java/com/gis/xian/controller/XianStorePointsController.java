package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianStorePointsBasePointVo;
import com.gis.xian.vo.XianStorePointsPointDetailVo;
import com.gis.xian.service.XianStorePointsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store-points")
public class XianStorePointsController extends BaseController{

    @Resource
    private XianStorePointsService xianStorePointsService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianStorePointsBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianStorePointsService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianStorePointsPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianStorePointsService.getPointDetailById(Long.parseLong(id)));
    }
}
