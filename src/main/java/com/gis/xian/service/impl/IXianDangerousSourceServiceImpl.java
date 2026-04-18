package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianDangerousSource;
import com.gis.xian.vo.XianDangerousSourceBasePointVo;
import com.gis.xian.vo.XianDangerousSourcePointDetailVo;
import com.gis.xian.mapper.XianDangerousSourceMapper;
import com.gis.xian.service.XianDangerousSourceService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianDangerousSourceServiceImpl implements XianDangerousSourceService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianDangerousSourceMapper xianDangerousSourceMapper;

    @Value("${init.data.base-points.dangerous-source}")
    private String dangerousSourceBasePointsKey;

    @Override
    public List<XianDangerousSourceBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(dangerousSourceBasePointsKey);

        if (data == null) {
            List<XianDangerousSourceBasePointVo> basePoints = XianDangerousSourceBasePointVo.entity2Vo(xianDangerousSourceMapper.getBasePoints());
            redisTemplate.opsForValue().set(dangerousSourceBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianDangerousSourceBasePointVo.class);
    }

    @Override
    public XianDangerousSourcePointDetailVo getPointDetailById(Long id) {
        return XianDangerousSourcePointDetailVo.entity2Vo(xianDangerousSourceMapper.getPointDetailById(id));
    }
}
