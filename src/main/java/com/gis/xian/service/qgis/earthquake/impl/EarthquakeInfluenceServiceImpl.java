package com.gis.xian.service.qgis.earthquake.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.config.QgisProperties;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInformationInfluenceDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInfluenceDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInfluenceGeoJsonDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeInfluence;
import com.gis.xian.service.qgis.earthquake.IEarthquakeInfluenceService;
import com.gis.xian.utils.qgis.GeoFilesHandler;
import com.gis.xian.mapper.qgis.earthquake.EarthquakeInfluenceMapper;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServiceException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzw
 * @description: 地震影响场实现
 * @date 2026/5/26 上午10:39
 */
@Slf4j
@Service
@DataSource("slave1")
public class EarthquakeInfluenceServiceImpl extends ServiceImpl<EarthquakeInfluenceMapper, EarthquakeInfluence> implements IEarthquakeInfluenceService {

    @Resource
    private QgisProperties qgisProperties;


    // 将影响场以文件形式保存
    @Async("xianPool")
    @Override
    public void handle(List<EarthquakeInformationInfluenceDTO> dzxxs) {
        // 异常
        if (dzxxs == null || dzxxs.isEmpty()) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        try {
            // 影响场名称
            String fineName = "";
            EarthquakeInfluenceGeoJsonDTO features = new EarthquakeInfluenceGeoJsonDTO();
            EarthquakeInfluenceDTO influence = new EarthquakeInfluenceDTO();
            // 处理影响场数据
            for (EarthquakeInformationInfluenceDTO dzxx : dzxxs) {
                // 接收面数据
                Polygon polygon = (Polygon) dzxx.getGeom();
                // 转换Polygon为GeoJSON Geometry结构
                EarthquakeInfluenceGeoJsonDTO.GeoJsonFeature feature = GeoFilesHandler.convertPolygonToGeoJsonFeature(polygon, dzxx);
                features.getFeatures().add(feature);
                // 根据批次编码拼接目录和文件名
                fineName = dzxx.getEqQueueId() + "/" + dzxx.getEqName();
                // 获取地震影响场信息
                influence.setEqQueueId(dzxx.getEqQueueId());
                influence.setEvent(dzxx.getEvent());
                influence.setName(dzxx.getEqName());
            }

            if (features.getFeatures() == null || features.getFeatures().isEmpty()) {
                throw new ServiceException(BaseConstants.INFLUENCE_CONVERT_ERROR);
            }

            // 保存GeoJSON文件
            GeoFilesHandler.writeGeoJsonToFile(features, fineName, qgisProperties);
            log.info("地震影响场GeoJson文件已生成成功!");

            influence.setPath(qgisProperties.getBasePath() + qgisProperties.getIntensityGeojsonPath() + fineName + ".geojson");
            handleDzxxData(influence);

        } catch (Exception ex) {
            log.error("影响场保存为geojson失败!：", ex.getMessage());
            throw new ServiceException(BaseConstants.INFLUENCE_CONVERT_ERROR);
        }
    }

    // 获取影响场文件
    @Override
    public Map<String, String> getInfluence(EarthquakeQuery query) {
        // 异常
        if (query == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 条件
        LambdaQueryWrapper<EarthquakeInfluence> lambdaQuery = Wrappers.lambdaQuery(EarthquakeInfluence.class);
        lambdaQuery.select(EarthquakeInfluence::getPath);
        lambdaQuery.eq(EarthquakeInfluence::getEvent, query.getEvent());
        lambdaQuery.eq(EarthquakeInfluence::getEqQueueId, query.getEqQueueId());
        lambdaQuery.orderByDesc(EarthquakeInfluence::getCreateTime);    // 防止多条记录
        lambdaQuery.last("limit 1");
        // 查询
        EarthquakeInfluence influence = (EarthquakeInfluence) this.baseMapper.selectList(lambdaQuery);
        if (influence == null) {
            throw new ParmaException(BaseConstants.RESULT_ERROR);
        }
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("file", influence.getPath());
        return hashmap;
    }


    // 处理地震信息数据
    private void handleDzxxData(EarthquakeInfluenceDTO dto) {
        EarthquakeInfluence influence = new EarthquakeInfluence();
        BeanUtils.copyProperties(dto, influence);
        // 存库
        save(influence);
    }
}
