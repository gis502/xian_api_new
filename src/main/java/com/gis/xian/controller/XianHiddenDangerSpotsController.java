package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;
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
    public ApiResponse<List<XianHiddenDangerSpotsBasePointVo>> getBasePoints(
            @RequestParam String type,
            @RequestParam(required = false) String disasterType) {
        return ApiResponse.ok(xianHiddenDangerSpotsService.getBasePoints(type, disasterType));
    }

    @GetMapping("point-detail/{id}")
    public ApiResponse<XianHiddenDangerSpotsPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianHiddenDangerSpotsService.getPointDetailById(Long.parseLong(id)));
    }
}
