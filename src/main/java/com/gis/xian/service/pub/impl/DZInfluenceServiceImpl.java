package com.gis.xian.service.pub.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gis.xian.config.DataSource;
import com.gis.xian.config.QgisProperties;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.dto.dzxx.DZXXInfluenceDTO;
import com.gis.xian.dto.pub.DZInfluenceDTO;
import com.gis.xian.dto.pub.IntyGeoJsonDTO;
import com.gis.xian.entity.pub.DZInfluence;
import com.gis.xian.handler.GeoFilesHandler;
import com.gis.xian.mapper.pub.DZInfluenceMapper;
import com.gis.xian.query.EqQuery;
import com.gis.xian.service.ex.ParmaException;
import com.gis.xian.service.ex.ServiceException;
import com.gis.xian.service.pub.IDZInfluenceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DZInfluenceServiceImpl extends ServiceImpl<DZInfluenceMapper, DZInfluence> implements IDZInfluenceService {

    @Resource
    private GeoFilesHandler handler;
    @Resource
    private QgisProperties qgisProperties;


    // 将影响场以文件形式保存
    @Async("xianPool")
    @Override
    public void handle(List<DZXXInfluenceDTO> dzxxs) {
        // 异常
        if (dzxxs == null || dzxxs.isEmpty()) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        try {
            // 影响场名称
            String fineName = "";
            IntyGeoJsonDTO features = new IntyGeoJsonDTO();
            DZInfluenceDTO influence = new DZInfluenceDTO();
            // 处理影响场数据
            for (DZXXInfluenceDTO dzxx : dzxxs) {
                // 接收面数据
                Polygon polygon = (Polygon) dzxx.getGeom();
                // 转换Polygon为GeoJSON Geometry结构
                IntyGeoJsonDTO.GeoJsonFeature feature = handler.convertPolygonToGeoJsonFeature(polygon, dzxx);
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
            handler.writeGeoJsonToFile(features, fineName);
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
    public Map<String, String> getInfluence(EqQuery query) {
        // 异常
        if (query == null) {
            throw new ParmaException(BaseConstants.PARAMS_ERROR);
        }
        // 条件
        LambdaQueryWrapper<DZInfluence> lambdaQuery = Wrappers.lambdaQuery(DZInfluence.class);
        lambdaQuery.select(DZInfluence::getPath);
        lambdaQuery.eq(DZInfluence::getEvent, query.getEvent());
        lambdaQuery.eq(DZInfluence::getEqQueueId, query.getEqQueueId());
        lambdaQuery.orderByDesc(DZInfluence::getCreateTime);    // 防止多条记录
        lambdaQuery.last("limit 1");
        // 查询
        DZInfluence influence = (DZInfluence) this.baseMapper.selectList(lambdaQuery);
        if (influence == null) {
            throw new ParmaException(BaseConstants.RESULT_ERROR);
        }
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("file", influence.getPath());
        return hashmap;
    }


    // 处理地震信息数据
    private void handleDzxxData(DZInfluenceDTO dto) {
        DZInfluence influence = new DZInfluence();
        BeanUtils.copyProperties(dto, influence);
        // 存库
        save(influence);
    }
}
