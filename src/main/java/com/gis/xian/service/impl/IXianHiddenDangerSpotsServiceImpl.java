package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianHiddenDangerSpots;
import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;
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

    @Value("${init.data.base-points.hidden-danger.all}")
    private String allBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.landslide}")
    private String landslideKey;

    @Value("${init.data.base-points.hidden-danger.debris-flow}")
    private String debrisFlowKey;

    @Value("${init.data.base-points.hidden-danger.flash-flood}")
    private String flashFloodKey;

    @Value("${init.data.base-points.hidden-danger.water-logging}")
    private String waterLoggingKey;

    @Override
    public List<XianHiddenDangerSpotsBasePointVo> getBasePoints(String disasterType) {
        // 构建Redis key
        String redisKey = buildRedisKey(disasterType);
        
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(redisKey);

        if (data == null) {
            // 从数据库查询
            List<XianHiddenDangerSpotsBasePointVo> basePoints = 
                XianHiddenDangerSpotsBasePointVo.entity2Vo(xianHiddenDangerSpotsMapper.getBasePoints(disasterType));
            
            // 存入Redis
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianHiddenDangerSpotsBasePointVo.class);
    }
    
    /**
     * 根据disasterType构建Redis key
     */
    private String buildRedisKey(String disasterType) {
        if (disasterType == null || disasterType.isEmpty()) {
            return allBasePointsKey;
        }
        switch (disasterType) {
            case "landslide":
                return landslideKey;
            case "debris_flow":
                return debrisFlowKey;
            case "flash_flood":
                return flashFloodKey;
            case "waterlogging":
                return waterLoggingKey;
            default:
                return allBasePointsKey;
        }
    }

    @Override
    public XianHiddenDangerSpotsPointDetailVo getPointDetailById(Long id) {
        return XianHiddenDangerSpotsPointDetailVo.entity2Vo(xianHiddenDangerSpotsMapper.getPointDetailById(id));
    }
}
