package com.gis.xian.service.qgis.rain.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.qgis.rain.RainAssessmentDTO;
import com.gis.xian.dto.qgis.rain.RainEventDTO;
import com.gis.xian.dto.qgis.rain.RainTriggerDTO;
import com.gis.xian.entity.qgis.rain.RainEvent;
import com.gis.xian.mapper.qgis.rain.RainEventMapper;
import com.gis.xian.dto.qgis.rain.RainQuery;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServeException;
import com.gis.xian.service.ex.ServiceException;
import com.gis.xian.service.qgis.earthquake.IEarthquakeQueueService;
import com.gis.xian.service.qgis.rain.IREventService;
import com.gis.xian.utils.BaseUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zzw
 * @description: REventServiceImpl
 * @date 2026/6/8 下午4:45
 */
@Slf4j
@Service
@DataSource("slave1")
public class RainEventServiceImpl extends ServiceImpl<RainEventMapper, RainEvent> implements IREventService {
    @Resource
    private IEarthquakeQueueService IEarthquakeQueueService;

    // 暴雨触发
    @Transactional
    @Override
    public RainQuery trigger(RainTriggerDTO trigger) {
        log.info("暴雨参数：{}", trigger);
        // 异常值
        if (trigger == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 专题图命名代码
        String code = BaseUtils.generationRainCode(trigger.getOccurrenceTime());
        // 暴雨业务
        try {
            // 暴雨信息存储
            RainEventDTO eventdto = new RainEventDTO();
            BeanUtils.copyProperties(trigger, eventdto);
            eventdto.setRainId(code);
            log.info("暴雨专题图编码：{}", code);
            handle(eventdto);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new ServeException(BaseConstants.RAIN_SERVER_ERROR);
        }
        String batch = BaseUtils.generationBatchCode(code);
        // 评估业务
        try {
            RainAssessmentDTO assess = new RainAssessmentDTO();
            BeanUtils.copyProperties(trigger, assess);
            assess.setRainId(code);
            assess.setRainQueueId(batch);
            // 开始评估
            IEarthquakeQueueService.assess(assess);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new ServeException(BaseConstants.ASSESS_SERVER_ERROR);
        }
        // 地震编码
        return new RainQuery(code, batch);
    }


    // 删除暴雨
    @Override
    public Boolean deletedById(Long Id) {
        // 空值
        if (Id == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 条件构造
        LambdaQueryWrapper<RainEvent> lambdaQuery = Wrappers.lambdaQuery(RainEvent.class);
        lambdaQuery.eq(RainEvent::getId, Id);
        int flag = this.baseMapper.delete(lambdaQuery);
        return flag > 0 ? true : false;
    }


    // 处理暴雨数据
    private void handle(RainEventDTO eventdto) {
        // 抛出异常
        if (eventdto == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }

        try {
            RainEvent revent = new RainEvent();
            BeanUtils.copyProperties(eventdto, revent);
            // 处理空间数据
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(
                    eventdto.getLongitude(), eventdto.getLatitude()
            ));
            revent.setGeom(point);
            revent.getGeom().setSRID(4490);
            // 存库
            save(revent);
            log.info("暴雨位基本信息已存库...");
        } catch (Exception ex) {
            log.error("暴雨触发：暴雨基本信息保存失败!", ex.getMessage());
            ex.printStackTrace();
            throw new ServiceException(BaseConstants.RAIN_SERVER_ERROR);
        }


    }

}
