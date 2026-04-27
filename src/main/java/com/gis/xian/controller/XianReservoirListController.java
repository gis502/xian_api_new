package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.vo.XianReservoirListBasePointVo;
import com.gis.xian.vo.XianReservoirListPointDetailVo;
import com.gis.xian.service.XianReservoirListService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservoir")
public class XianReservoirListController extends BaseController{

    @Resource
    private XianReservoirListService xianReservoirListService;

    @GetMapping("/base-points")
    public ApiResponse<List<XianReservoirListBasePointVo>> getBasePoints() {
        return ApiResponse.ok(xianReservoirListService.getBasePoints());
    }

    @GetMapping("/point-detail/{id}")
    public ApiResponse<XianReservoirListPointDetailVo> getPointDetailById(@PathVariable String id) {
        return ApiResponse.ok(xianReservoirListService.getPointDetailById(Long.parseLong(id)));
    }
}
