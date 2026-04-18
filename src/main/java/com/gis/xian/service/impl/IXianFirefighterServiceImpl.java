package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianFirefighter;
import com.gis.xian.vo.XianFirefighterBasePointVo;
import com.gis.xian.vo.XianFirefighterPointDetailVo;
import com.gis.xian.mapper.XianFirefighterMapper;
import com.gis.xian.service.XianFirefighterService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianFirefighterServiceImpl implements XianFirefighterService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianFirefighterMapper xianFirefighterMapper;

    @Value("${init.data.base-points.firefighter}")
    private String firefighterBasePointsKey;

    @Override
    public List<XianFirefighterBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(firefighterBasePointsKey);

        if (data == null) {
            List<XianFirefighterBasePointVo> basePoints = XianFirefighterBasePointVo.entity2Vo(xianFirefighterMapper.getBasePoints());
            redisTemplate.opsForValue().set(firefighterBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianFirefighterBasePointVo.class);
    }

    @Override
    public XianFirefighterPointDetailVo getPointDetailById(Long id) {
        return XianFirefighterPointDetailVo.entity2Vo(xianFirefighterMapper.getPointDetailById(id));
    }
}
