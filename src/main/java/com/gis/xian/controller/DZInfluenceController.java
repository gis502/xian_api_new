package com.gis.xian.controller;

import com.gis.xian.constant.BaseConstants;
import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.query.EqQuery;
import com.gis.xian.service.dzxx.IDZXXInfluenceService;
import com.gis.xian.service.pub.IDZInfluenceService;
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
    private IDZInfluenceService idzInfluenceService;
    @Resource
    private IDZXXInfluenceService idzxxInfluenceService;

    @PostMapping("/influence")
    public ApiResponse<Map<String, String>> getInfluence(@RequestBody @Validated EqQuery query) {
        Map<String, String> influence = idzInfluenceService.getInfluence(query);
        return ApiResponse.ok(influence);
    }

    @PostMapping("/generate/influence")
    public ApiResponse<String> generateInfluence(@RequestBody @Validated EqAssessmentDTO assess) {
        idzxxInfluenceService.handle(assess);
        return ApiResponse.ok("地震影响场已生成!");
    }
}
