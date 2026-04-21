package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianSchoolBasePointVo;
import com.gis.xian.vo.XianSchoolPointDetailVo;
import com.gis.xian.service.XianSchoolService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class XianSchoolController extends BaseController{

    @Resource
    private XianSchoolService xianSchoolService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianSchoolBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianSchoolService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianSchoolPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianSchoolService.getPointDetailById(Long.parseLong(id)));
    }
}
