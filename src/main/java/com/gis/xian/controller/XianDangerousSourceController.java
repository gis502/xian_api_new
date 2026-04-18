package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianDangerousSourceBasePointVo;
import com.gis.xian.vo.XianDangerousSourcePointDetailVo;
import com.gis.xian.service.XianDangerousSourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dangerous-source")
public class XianDangerousSourceController extends BaseController{

    @Resource
    private XianDangerousSourceService xianDangerousSourceService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianDangerousSourceBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianDangerousSourceService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianDangerousSourcePointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianDangerousSourceService.getPointDetailById(Long.parseLong(id)));
    }
}
