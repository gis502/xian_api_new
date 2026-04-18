package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianEmergencyShelterBasePointVo;
import com.gis.xian.vo.XianEmergencyShelterPointDetailVo;
import com.gis.xian.service.XianEmergencyShelterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-shelter")
public class XianEmergencyShelterController extends BaseController{

    @Resource
    private XianEmergencyShelterService xianEmergencyShelterService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianEmergencyShelterBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianEmergencyShelterService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianEmergencyShelterPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianEmergencyShelterService.getPointDetailById(Long.parseLong(id)));
    }
}
