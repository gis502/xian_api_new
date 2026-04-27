package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianSubwayStationsBasePointVo;
import com.gis.xian.vo.XianSubwayStationsPointDetailVo;
import com.gis.xian.service.XianSubwayStationsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subway")
public class XianSubwayStationsController extends BaseController{

    @Resource
    private XianSubwayStationsService xianSubwayStationsService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianSubwayStationsBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianSubwayStationsService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianSubwayStationsPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianSubwayStationsService.getPointDetailById(Long.parseLong(id)));
    }
}
