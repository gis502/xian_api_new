package com.gis.xian.controller;

import com.gis.xian.constant.BaseConstants;
import com.gis.xian.domain.ApiResponse;
import com.gis.xian.dto.pub.EqTriggerDTO;
import com.gis.xian.query.EqQuery;
import com.gis.xian.service.pub.IDZEqEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IDZEqEventService idzEqEventService;

    @PostMapping("/eq/trigger")
    public ApiResponse trigger(@RequestBody @Validated EqTriggerDTO trigger) {
        EqQuery query = idzEqEventService.trigger(trigger);
        if (query == null) {
            return ApiResponse.error(BaseConstants.RESULT_ERROR);
        }
        return ApiResponse.ok(query);
    }

    @PostMapping("/eq/delete/{Id}")
    public ApiResponse delete(@PathVariable Long Id) {
        Boolean deleted = idzEqEventService.deletedById(Id);
        if (!deleted) {
            return ApiResponse.error("删除失败！");
        }
        return ApiResponse.ok("删除成功！");
    }

}
