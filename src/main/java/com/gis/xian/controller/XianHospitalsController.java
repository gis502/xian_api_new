package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianHospitalsBasePointVo;
import com.gis.xian.vo.XianHospitalsPointDetailVo;
import com.gis.xian.service.XianHospitalsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class XianHospitalsController extends BaseController{

    @Resource
    private XianHospitalsService xianHospitalsService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianHospitalsBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianHospitalsService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianHospitalsPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianHospitalsService.getPointDetailById(Long.parseLong(id)));
    }
}
