package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
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

    @Value("${init.data.base-points.hidden-danger.earthquake}")
    private String earthquakeBasePointsKey;

    @Override
    public List<XianHiddenDangerSpotsBasePointVo> getBasePoints(String disasterType) {
        // 从redis中读取基础点信息
        Object data = null;

        if(DisasterTypeEnum.RAINSTORM.getType().equals(disasterType)) {
            data = redisTemplate.opsForValue().get(rainstormBasePointsKey);
        }else {
            data = redisTemplate.opsForValue().get(earthquakeBasePointsKey);
        }

        if (data == null) {
            List<XianHiddenDangerSpotsBasePointVo> basePoints = xianHiddenDangerSpotsMapper.getBasePoints(disasterType);

            if(DisasterTypeEnum.RAINSTORM.getType().equals(disasterType)) {
                redisTemplate.opsForValue().set(rainstormBasePointsKey, JSON.toJSONString(basePoints));
            }else {
                redisTemplate.opsForValue().set(earthquakeBasePointsKey, JSON.toJSONString(basePoints));
            }
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianHiddenDangerSpotsBasePointVo.class);
    }

    @Override
    public XianHiddenDangerSpotsPointDetailVo getPointDetailById(Long id) {
        return xianHiddenDangerSpotsMapper.getPointDetailById(id);
    }
}
