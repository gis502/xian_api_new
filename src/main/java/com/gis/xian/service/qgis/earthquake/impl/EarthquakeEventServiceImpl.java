package com.gis.xian.service.qgis.earthquake.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.qgis.earthquake.EarthquakeCenterDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeTriggerDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeEvent;
import com.gis.xian.mapper.qgis.earthquake.EarthquakeEventMapper;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInformationCenterService;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServeException;
import com.gis.xian.service.qgis.earthquake.IEarthquakeEventService;
import com.gis.xian.service.qgis.earthquake.IEarthquakeQueueService;
import com.gis.xian.utils.BaseUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zzw
 * @description: 地震事件实现
 * @date 2026/5/25 下午5:01
 */
@Slf4j
@Service
@DataSource("slave1")
public class EarthquakeEventServiceImpl extends ServiceImpl<EarthquakeEventMapper, EarthquakeEvent> implements IEarthquakeEventService {

    @Resource
    private IEarthquakeInformationCenterService IEarthquakeInformationCenterService;
    @Resource
    private IEarthquakeQueueService IEarthquakeQueueService;

    // 地震业务触发
    @Transactional
    @Override
    public EarthquakeQuery trigger(EarthquakeTriggerDTO trigger) {
        log.info("地震参数：{}", trigger);
        // 异常值
        if (trigger == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 专题图命名代码
        String code = BaseUtils.generationCode(trigger.getEqTime());
        // 地震业务
        try {
            EarthquakeCenterDTO dzxx = new EarthquakeCenterDTO();
            BeanUtils.copyProperties(trigger, dzxx);
            dzxx.setEvent(code);
            log.info("地震专题图编码：{}", code);
            // 震中位置存储
            IEarthquakeInformationCenterService.handle(dzxx);
            // 地震基本信息存储
            EarthquakeEvent dzeq = new EarthquakeEvent();
            BeanUtils.copyProperties(dzxx, dzeq);
            save(dzeq);
            log.info("地震基本信息已存库...");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new ServeException(BaseConstants.EQ_SERVER_ERROR);
        }

        String batch = BaseUtils.generationBatchCode(code);
        // 评估业务
        try {
            EarthquakeAssessmentDTO assess = new EarthquakeAssessmentDTO();
            BeanUtils.copyProperties(trigger, assess);
            assess.setEvent(code);
            assess.setEqQueueId(batch);

            // 开始评估
            IEarthquakeQueueService.assess(assess);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new ServeException(BaseConstants.ASSESS_SERVER_ERROR);
        }
        // 地震编码
        return new EarthquakeQuery(code, batch);
    }

    // 删除地震
    @Override
    public Boolean deletedById(Long Id) {
        // 空值
        if (Id == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 条件构造
        LambdaQueryWrapper<EarthquakeEvent> lambdaQuery = Wrappers.lambdaQuery(EarthquakeEvent.class);
        lambdaQuery.eq(EarthquakeEvent::getId, Id);
        int flag = this.baseMapper.delete(lambdaQuery);
        return flag > 0 ? true : false;
    }

}
