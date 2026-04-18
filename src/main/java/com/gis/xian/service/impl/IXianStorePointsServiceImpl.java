package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianStorePoints;
import com.gis.xian.vo.XianStorePointsBasePointVo;
import com.gis.xian.vo.XianStorePointsPointDetailVo;
import com.gis.xian.mapper.XianStorePointsMapper;
import com.gis.xian.service.XianStorePointsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianStorePointsServiceImpl implements XianStorePointsService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianStorePointsMapper xianStorePointsMapper;

    @Value("${init.data.base-points.store-points}")
    private String storePointsBasePointsKey;

    @Override
    public List<XianStorePointsBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(storePointsBasePointsKey);

        if (data == null) {
            List<XianStorePointsBasePointVo> basePoints = XianStorePointsBasePointVo.entity2Vo(xianStorePointsMapper.getBasePoints());
            redisTemplate.opsForValue().set(storePointsBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianStorePointsBasePointVo.class);
    }

    @Override
    public XianStorePointsPointDetailVo getPointDetailById(Long id) {
        return XianStorePointsPointDetailVo.entity2Vo(xianStorePointsMapper.getPointDetailById(id));
    }
}
