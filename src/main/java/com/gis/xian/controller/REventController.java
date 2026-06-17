package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.qgis.rain.RainTriggerDTO;
import com.gis.xian.dto.qgis.rain.RainQuery;
import com.gis.xian.service.qgis.rain.IREventService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzw
 * @description: 暴雨事件控制类
 * @date 2026/6/8 下午4:36
 */
@Slf4j
@RestController
@RequestMapping("/open")
public class REventController {

    @Resource
    private IREventService irEventService;

    @PostMapping("/rs/trigger")
    public ApiResponse<RainQuery> trigger(@RequestBody @Validated RainTriggerDTO trigger) {
        RainQuery query = irEventService.trigger(trigger);
        return ApiResponse.ok(query);
    }

    @PostMapping("/rs/delete/{Id}")
    public ApiResponse<Boolean> delete(@PathVariable Long Id) {
        Boolean deleted = irEventService.deletedById(Id);
        return ApiResponse.ok(deleted);
    }
}
