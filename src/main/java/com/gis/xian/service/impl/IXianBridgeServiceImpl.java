package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.mapper.XianBridgeMapper;
import com.gis.xian.service.XianBridgeService;
import com.gis.xian.vo.XianBridgeBasePointVo;
import com.gis.xian.vo.XianBridgePointDetailVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianBridgeServiceImpl implements XianBridgeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianBridgeMapper xianBridgeMapper;

    @Value("${init.data.base-points.bridge}")
    private String bridgeBasePointsKey;

    @Override
    public List<XianBridgeBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(bridgeBasePointsKey);

        if (data == null) {
            List<XianBridgeBasePointVo> basePoints = XianBridgeBasePointVo.entity2Vo(xianBridgeMapper.getBasePoints());
            redisTemplate.opsForValue().set(bridgeBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianBridgeBasePointVo.class);
    }

    @Override
    public XianBridgePointDetailVo getPointDetailById(Long id) {
        return XianBridgePointDetailVo.entity2Vo(xianBridgeMapper.getPointDetailById(id));
    }
}
