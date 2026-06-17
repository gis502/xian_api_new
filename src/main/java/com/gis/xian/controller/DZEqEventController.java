package com.gis.xian.controller;

import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.qgis.earthquake.EarthquakeTriggerDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import com.gis.xian.service.qgis.earthquake.IEarthquakeEventService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzw
 * @description: QGIS地震事件触发
 * @date 2026/5/25 下午3:12
 */
@Slf4j
@RestController
@RequestMapping("/open")
public class DZEqEventController {
    @Resource
    private IEarthquakeEventService IEarthquakeEventService;

    @PostMapping("/eq/trigger")
    public ApiResponse<EarthquakeQuery> trigger(@RequestBody @Validated EarthquakeTriggerDTO trigger) {
        EarthquakeQuery query = IEarthquakeEventService.trigger(trigger);
        return ApiResponse.ok(query);
    }

    @PostMapping("/eq/delete/{Id}")
    public ApiResponse<Boolean> delete(@PathVariable Long Id) {
        Boolean deleted = IEarthquakeEventService.deletedById(Id);
        return ApiResponse.ok(deleted);
    }

}
