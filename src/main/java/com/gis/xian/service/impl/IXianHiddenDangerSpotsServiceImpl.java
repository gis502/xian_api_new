package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianHiddenDangerSpots;
import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.XianHiddenDangerSpotsMapper;
import com.gis.xian.service.XianHiddenDangerSpotsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianHiddenDangerSpotsServiceImpl implements XianHiddenDangerSpotsService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianHiddenDangerSpotsMapper xianHiddenDangerSpotsMapper;

    @Value("${init.data.base-points.hidden-danger.rainstorm}")
    private String rainstormBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-landslide}")
    private String rainstormLandslideKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-debris-flow}")
    private String rainstormDebrisFlowKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-flash-flood}")
    private String rainstormMountainFloodKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-water-logging}")
    private String rainstormWaterLoggingKey;

    @Value("${init.data.base-points.hidden-danger.earthquake}")
    private String earthquakeBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.earthquake-landslide}")
    private String earthquakeLandslideKey;

    @Value("${init.data.base-points.hidden-danger.earthquake-debris-flow}")
    private String earthquakeDebrisFlowKey;

    @Override
    public List<XianHiddenDangerSpotsBasePointVo> getBasePoints(String type, String disasterType) {
        // 构建Redis key
        String redisKey = buildRedisKey(type, disasterType);
        
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(redisKey);

        if (data == null) {
            // 从数据库查询
            List<XianHiddenDangerSpotsBasePointVo> basePoints = 
                XianHiddenDangerSpotsBasePointVo.entity2Vo(xianHiddenDangerSpotsMapper.getBasePoints(type, disasterType));
            
            // 存入Redis
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianHiddenDangerSpotsBasePointVo.class);
    }
    
    /**
     * 根据type和disasterType构建Redis key
     */
    private String buildRedisKey(String type, String disasterType) {
        if (DisasterTypeEnum.RAINSTORM.getType().equals(type)) {
            if (disasterType == null || disasterType.isEmpty()) {
                return rainstormBasePointsKey;
            }
            switch (disasterType) {
                case "landslide":
                    return rainstormLandslideKey;
                case "debris_flow":
                    return rainstormDebrisFlowKey;
                case "flash_flood":
                    return rainstormMountainFloodKey;
                case "waterlogging":
                    return rainstormWaterLoggingKey;
                default:
                    return rainstormBasePointsKey;
            }
        } else if (DisasterTypeEnum.EARTHQUAKE.getType().equals(type)) {
            if (disasterType == null || disasterType.isEmpty()) {
                return earthquakeBasePointsKey;
            }
            switch (disasterType) {
                case "landslide":
                    return earthquakeLandslideKey;
                case "debris_flow":
                    return earthquakeDebrisFlowKey;
                default:
                    return earthquakeBasePointsKey;
            }
        }
        // 默认返回暴雨的key
        return rainstormBasePointsKey;
    }

    @Override
    public XianHiddenDangerSpotsPointDetailVo getPointDetailById(Long id) {
        return XianHiddenDangerSpotsPointDetailVo.entity2Vo(xianHiddenDangerSpotsMapper.getPointDetailById(id));
    }
}
