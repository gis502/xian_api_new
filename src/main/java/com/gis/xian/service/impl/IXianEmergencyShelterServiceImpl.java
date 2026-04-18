package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianEmergencyShelter;
import com.gis.xian.vo.XianEmergencyShelterBasePointVo;
import com.gis.xian.vo.XianEmergencyShelterPointDetailVo;
import com.gis.xian.mapper.XianEmergencyShelterMapper;
import com.gis.xian.service.XianEmergencyShelterService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianEmergencyShelterServiceImpl implements XianEmergencyShelterService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianEmergencyShelterMapper xianEmergencyShelterMapper;

    @Value("${init.data.base-points.emergency-shelter}")
    private String emergencyShelterBasePointsKey;

    @Override
    public List<XianEmergencyShelterBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(emergencyShelterBasePointsKey);

        if (data == null) {
            List<XianEmergencyShelterBasePointVo> basePoints = XianEmergencyShelterBasePointVo.entity2Vo(xianEmergencyShelterMapper.getBasePoints());
            redisTemplate.opsForValue().set(emergencyShelterBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianEmergencyShelterBasePointVo.class);
    }

    @Override
    public XianEmergencyShelterPointDetailVo getPointDetailById(Long id) {
        return XianEmergencyShelterPointDetailVo.entity2Vo(xianEmergencyShelterMapper.getPointDetailById(id));
    }
}
