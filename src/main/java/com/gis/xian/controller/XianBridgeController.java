package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.service.XianBridgeService;
import com.gis.xian.vo.XianBridgeBasePointVo;
import com.gis.xian.vo.XianBridgePointDetailVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bridge")
public class XianBridgeController extends BaseController{

    @Resource
    private XianBridgeService xianBridgeService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianBridgeBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianBridgeService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianBridgePointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianBridgeService.getPointDetailById(Long.parseLong(id)));
    }
}
