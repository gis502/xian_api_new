package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianSchool;
import com.gis.xian.vo.XianSchoolBasePointVo;
import com.gis.xian.vo.XianSchoolPointDetailVo;
import com.gis.xian.mapper.XianSchoolMapper;
import com.gis.xian.service.XianSchoolService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianSchoolServiceImpl implements XianSchoolService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianSchoolMapper xianSchoolMapper;

    @Value("${init.data.base-points.school}")
    private String schoolBasePointsKey;

    @Override
    public List<XianSchoolBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(schoolBasePointsKey);

        if (data == null) {
            List<XianSchoolBasePointVo> basePoints = XianSchoolBasePointVo.entity2Vo(xianSchoolMapper.getBasePoints());
            redisTemplate.opsForValue().set(schoolBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianSchoolBasePointVo.class);
    }

    @Override
    public XianSchoolPointDetailVo getPointDetailById(Long id) {
        return XianSchoolPointDetailVo.entity2Vo(xianSchoolMapper.getPointDetailById(id));
    }
}
