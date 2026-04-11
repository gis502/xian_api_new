package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianHiddenDangerSpotsBasePoint;
import com.gis.xian.entity.XianRiskSpotsBasePoint;
import com.gis.xian.entity.XianRiskSpotsPointDetail;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.XianRiskSpotsMapper;
import com.gis.xian.service.XianRiskSpotsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianRiskSpotsServiceImpl implements XianRiskSpotsService {

    @Resource
    private XianRiskSpotsMapper xianRiskSpotsMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${init.data.base-points.risk}")
    private String riskPointKey;

    @Override
    public List<XianRiskSpotsBasePoint> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(riskPointKey);

        if (data == null) {
            List<XianRiskSpotsBasePoint> basePoints = xianRiskSpotsMapper.getBasePoints();
            redisTemplate.opsForValue().set(riskPointKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianRiskSpotsBasePoint.class);
    }

    @Override
    public XianRiskSpotsPointDetail getPointDetailById(Long id) {
        return xianRiskSpotsMapper.getPointDetailById(id);
    }
}
