package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInformationInfluenceService;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInfluenceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zzw
 * @description: 影响场控制类
 * @date 2026/6/4 下午3:09
 */
@Slf4j
@RestController
@RequestMapping("/open")
public class DZInfluenceController {

    @Resource
    private IEarthquakeInfluenceService IEarthquakeInfluenceService;
    @Resource
    private IEarthquakeInformationInfluenceService IEarthquakeInformationInfluenceService;

    @PostMapping("/influence")
    public ApiResponse<Map<String, String>> getInfluence(@RequestBody @Validated EarthquakeQuery query) {
        Map<String, String> influence = IEarthquakeInfluenceService.getInfluence(query);
        return ApiResponse.ok(influence);
    }

    @PostMapping("/generate/influence")
    public ApiResponse<String> generateInfluence(@RequestBody @Validated EarthquakeAssessmentDTO assess) {
        IEarthquakeInformationInfluenceService.handle(assess);
        return ApiResponse.ok("地震影响场已生成!");
    }
}
