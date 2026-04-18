package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianFirefighterBasePointVo;
import com.gis.xian.vo.XianFirefighterPointDetailVo;
import com.gis.xian.service.XianFirefighterService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firefighter")
public class XianFirefighterController extends BaseController{

    @Resource
    private XianFirefighterService xianFirefighterService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianFirefighterBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianFirefighterService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianFirefighterPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianFirefighterService.getPointDetailById(Long.parseLong(id)));
    }
}
