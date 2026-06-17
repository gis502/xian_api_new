package com.gis.xian.service.qgis.earthquake.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.qgis.base.ActiveFaultDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInformationInfluenceDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeGisInfluence;
import com.gis.xian.utils.qgis.EarthquakeHandler;
import com.gis.xian.utils.qgis.EllipseToWktHandler;
import com.gis.xian.mapper.qgis.earthquake.EarthquakeGISInfluenceMapper;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import com.gis.xian.dto.qgis.earthquake.EarthquakeIntensityQuery;
import com.gis.xian.service.qgis.base.IActiveFaultService;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInformationInfluenceService;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServiceException;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInfluenceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzw
 * @description: 地震影响场业务
 * @date 2026/5/26 上午10:13
 */
@Slf4j
@Service
@DataSource("slave1")
public class EarthquakeInformationInfluenceServiceImpl extends ServiceImpl<EarthquakeGISInfluenceMapper, EarthquakeGisInfluence> implements IEarthquakeInformationInfluenceService {


    @Resource
    private IActiveFaultService faultService;
    @Resource
    private IEarthquakeInfluenceService IEarthquakeInfluenceService;

    // 处理地震影响场数据
    @Override
    public void handle(EarthquakeAssessmentDTO assess) {
        // 异常处理
        if (assess == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 处理地震影响场数据
        try {
            // 获取烈度值
            int[] intensities = gainIntensityLevels(assess.getEqMagnitude());
            // 根据烈度值和震级生成影响场
            for (int intensity : intensities) {
                // 根据烈度和震级计算长轴
                double longUranium = EarthquakeHandler.calculateRa(assess.getEqMagnitude(), intensity);
                // 根据烈度和震级计算短轴
                double shortUranium = EarthquakeHandler.calculateRb(assess.getEqMagnitude(), intensity);
                // 根据长短轴计算面积
                double area = EarthquakeHandler.calculateArea(longUranium, shortUranium);

                // 获取断层走向 角度
                ActiveFaultDTO shortlyFault = faultService.getShortlyFault(assess.getLongitude(), assess.getLatitude());
                // 根据烈度长短轴、走向、面积计算生成影响场
                EarthquakeIntensityQuery query = compute(longUranium, shortUranium, shortlyFault, assess);
                // 影响场范围
                Polygon polygon = EllipseToWktHandler.ellipseToPolygonWkt(query);

                // 转换存库
                convert(longUranium, shortUranium, area, intensity, polygon, shortlyFault, assess);
                log.info("地震影响场已生成!");
            }

        } catch (Exception ex) {
            log.error("处理影响场数据时出错：" + ex.getMessage());
            ex.printStackTrace();
            throw new ServiceException(BaseConstants.EQ_SERVER_ERROR);
        }

        // 获取地震影响场数据
        List<EarthquakeInformationInfluenceDTO> influences = findInfluenceById(new EarthquakeQuery(assess.getEvent(), assess.getEqQueueId()));
        // 输出geojson文件
        IEarthquakeInfluenceService.handle(influences);

    }

    // 查询地震影响场
    @Override
    public List<EarthquakeInformationInfluenceDTO> findInfluenceById(EarthquakeQuery query) {
        // 查询条件
        LambdaQueryWrapper<EarthquakeGisInfluence> lambdaQuery = Wrappers.lambdaQuery(EarthquakeGisInfluence.class);
        lambdaQuery.eq(EarthquakeGisInfluence::getEvent, query.getEvent());
        lambdaQuery.eq(EarthquakeGisInfluence::getEqQueueId, query.getEqQueueId());

        // 该场地震影响场
        List<EarthquakeGisInfluence> influences = this.baseMapper.selectList(lambdaQuery);

        if (influences == null || influences.size() == 0) {
            log.error("查询地震影响场结果为空！");
            throw new ParmaException(BaseConstants.RESULT_ERROR);
        }
        // 处理
        List<EarthquakeInformationInfluenceDTO> dzxxs = new ArrayList<>();
        for (EarthquakeGisInfluence influence : influences) {
            EarthquakeInformationInfluenceDTO dzxx = new EarthquakeInformationInfluenceDTO();
            BeanUtils.copyProperties(influence, dzxx);
            dzxxs.add(dzxx);
        }

        return dzxxs;
    }


    // 获取最大烈度影响场
    @Override
    public EarthquakeInformationInfluenceDTO findInfluenceMaxIntyById(EarthquakeQuery query) {

        LambdaQueryWrapper<EarthquakeGisInfluence> lambdaQuery = Wrappers.lambdaQuery(EarthquakeGisInfluence.class);
        lambdaQuery
                .eq(EarthquakeGisInfluence::getEvent, query.getEvent())
                .eq(EarthquakeGisInfluence::getEqQueueId, query.getEqQueueId())
                .orderByDesc(EarthquakeGisInfluence::getInty)
                .last("limit 1");   // 最大烈度

        // 获取单条数据
        EarthquakeGisInfluence influence = this.baseMapper.selectOne(lambdaQuery);

        if (influence == null) {
            log.error("查询地震影响场结果为空！");
            throw new ParmaException(BaseConstants.RESULT_ERROR);
        }
        EarthquakeInformationInfluenceDTO dzxx = new EarthquakeInformationInfluenceDTO();
        BeanUtils.copyProperties(influence, dzxx);

        return dzxx;
    }


    // 根据震级获取对应的烈度值数组
    private int[] gainIntensityLevels(double magnitude) {
        // 对震级进行四舍五入处理，取整数级别
        int magLevel = (int) Math.round(magnitude);
        // 根据震级范围返回对应的烈度值数组
        if (magLevel >= 5.5 && magLevel < 7.0) {
            return new int[]{6, 7, 8};
        } else if (magLevel >= 7.0 && magLevel < 8.0) {
            return new int[]{6, 7, 8, 9};
        } else if (magLevel >= 8.0 && magLevel < 9.0) {
            return new int[]{6, 7, 8, 9, 10};
        } else if (magLevel >= 9.0) {
            // 9级及以上都返回9-12级烈度
            return new int[]{6, 7, 8, 9, 10, 11, 12};
        } else {
            // 对于6级以下的震级，默认返回低烈度值
            return new int[]{6};
        }
    }

    // 根据烈度长短轴、走向、面积计算生成影响场
    private EarthquakeIntensityQuery compute(double longUranium, double shortUranium,
                                             ActiveFaultDTO shortlyFault, EarthquakeAssessmentDTO assess) {

        EarthquakeIntensityQuery query = new EarthquakeIntensityQuery();
        query.setCenterLon(assess.getLongitude());
        query.setCenterLat(assess.getLatitude());
        query.setSemiMajor(longUranium);
        query.setSemiMinor(shortUranium);
        query.setRotation(shortlyFault.getStrike());
        query.setNumVertices(BaseConstants.NUM_VERTICES);

        return query;
    }

    // 转换存库
    private void convert(double longUranium, double shortUranium, double area, int intensity,
                         Polygon polygon, ActiveFaultDTO shortlyFault, EarthquakeAssessmentDTO assess) {
        EarthquakeInformationInfluenceDTO dzxxDto = new EarthquakeInformationInfluenceDTO();
        dzxxDto.setArea(area);
        dzxxDto.setGeom(polygon);
        dzxxDto.setDirection(Double.valueOf(shortlyFault.getDirection()));
        dzxxDto.setLongitude(assess.getLongitude());
        dzxxDto.setLatitude(assess.getLatitude());
        dzxxDto.setEqName(assess.getEqName());
        dzxxDto.setEqQueueId(assess.getEqQueueId());
        dzxxDto.setEvent(assess.getEvent());
        dzxxDto.setInty(intensity);
        dzxxDto.setSInty(BaseConstants.SEISMIC_INTENSITY_MAPPING.get(intensity));
        dzxxDto.setLongUranium(longUranium);
        dzxxDto.setShortUranium(shortUranium);

        // 存库
        EarthquakeGisInfluence earthquakeGisInfluence = new EarthquakeGisInfluence();
        BeanUtils.copyProperties(dzxxDto, earthquakeGisInfluence);
        earthquakeGisInfluence.getGeom().setSRID(4490);
        save(earthquakeGisInfluence);
        log.info("地震影响场已保存!");
    }

}
