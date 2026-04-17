package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianHospitals;
import com.gis.xian.vo.XianHospitalsBasePointVo;
import com.gis.xian.vo.XianHospitalsPointDetailVo;
import com.gis.xian.mapper.XianHospitalsMapper;
import com.gis.xian.service.XianHospitalsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianHospitalsServiceImpl implements XianHospitalsService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianHospitalsMapper xianHospitalsMapper;

    @Value("${init.data.base-points.hospitals}")
    private String hospitalsBasePointsKey;

    @Override
    public List<XianHospitalsBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(hospitalsBasePointsKey);

        if (data == null) {
            List<XianHospitalsBasePointVo> basePoints = XianHospitalsBasePointVo.entity2Vo(xianHospitalsMapper.getBasePoints());
            redisTemplate.opsForValue().set(hospitalsBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianHospitalsBasePointVo.class);
    }

    @Override
    public XianHospitalsPointDetailVo getPointDetailById(Long id) {
        return XianHospitalsPointDetailVo.entity2Vo(xianHospitalsMapper.getPointDetailById(id));
    }
}
