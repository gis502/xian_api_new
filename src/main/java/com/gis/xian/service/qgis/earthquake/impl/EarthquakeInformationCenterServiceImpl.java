package com.gis.xian.service.qgis.earthquake.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.qgis.earthquake.EarthquakeCenterDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeCenter;
import com.gis.xian.mapper.qgis.earthquake.EarthquakeCenterMapper;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInformationCenterService;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author zzw
 * @description: 地震事件
 * @date 2026/5/25 下午5:21
 */
@Slf4j
@Service
@DataSource("slave1")
public class EarthquakeInformationCenterServiceImpl extends ServiceImpl<EarthquakeCenterMapper, EarthquakeCenter> implements IEarthquakeInformationCenterService {

    // 地震触发
    @Override
    public void handle(EarthquakeCenterDTO dzxxdto) {
        // 抛出异常
        if (dzxxdto == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }

        try {
            EarthquakeCenter dzxx = new EarthquakeCenter();
            BeanUtils.copyProperties(dzxxdto, dzxx);
            // 处理空间数据
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(
                    dzxxdto.getLongitude(), dzxxdto.getLatitude()
            ));
            dzxx.setGeom(point);
            dzxx.getGeom().setSRID(4490);
            // 存库
            save(dzxx);
            log.info("震中位置信息已存库...");
        } catch (Exception ex) {
            log.error("地震触发：震中位置信息保存失败!", ex.getMessage());
            ex.printStackTrace();
            throw new ServiceException(BaseConstants.EQ_SERVER_ERROR);
        }
    }
}
