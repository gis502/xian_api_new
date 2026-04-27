package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianSubwayStations;
import com.gis.xian.vo.XianSubwayStationsBasePointVo;
import com.gis.xian.vo.XianSubwayStationsPointDetailVo;
import com.gis.xian.mapper.XianSubwayStationsMapper;
import com.gis.xian.service.XianSubwayStationsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianSubwayStationsServiceImpl implements XianSubwayStationsService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianSubwayStationsMapper xianSubwayStationsMapper;

    @Value("${init.data.base-points.subway}")
    private String subwayBasePointsKey;

    @Override
    public List<XianSubwayStationsBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(subwayBasePointsKey);

        if (data == null) {
            List<XianSubwayStationsBasePointVo> basePoints = XianSubwayStationsBasePointVo.entity2Vo(xianSubwayStationsMapper.getBasePoints());
            redisTemplate.opsForValue().set(subwayBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianSubwayStationsBasePointVo.class);
    }

    @Override
    public XianSubwayStationsPointDetailVo getPointDetailById(Long id) {
        return XianSubwayStationsPointDetailVo.entity2Vo(xianSubwayStationsMapper.getPointDetailById(id));
    }
}
