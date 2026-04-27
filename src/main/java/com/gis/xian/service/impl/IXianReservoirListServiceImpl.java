package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.entity.XianReservoirList;
import com.gis.xian.vo.XianReservoirListBasePointVo;
import com.gis.xian.vo.XianReservoirListPointDetailVo;
import com.gis.xian.mapper.XianReservoirListMapper;
import com.gis.xian.service.XianReservoirListService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IXianReservoirListServiceImpl implements XianReservoirListService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private XianReservoirListMapper xianReservoirListMapper;

    @Value("${init.data.base-points.reservoir}")
    private String reservoirBasePointsKey;

    @Override
    public List<XianReservoirListBasePointVo> getBasePoints() {
        // 从redis中读取基础点信息
        Object data = redisTemplate.opsForValue().get(reservoirBasePointsKey);

        if (data == null) {
            List<XianReservoirListBasePointVo> basePoints = XianReservoirListBasePointVo.entity2Vo(xianReservoirListMapper.getBasePoints());
            redisTemplate.opsForValue().set(reservoirBasePointsKey, JSON.toJSONString(basePoints));
            return basePoints;
        }

        return JSON.parseArray(data.toString(), XianReservoirListBasePointVo.class);
    }

    @Override
    public XianReservoirListPointDetailVo getPointDetailById(Long id) {
        return XianReservoirListPointDetailVo.entity2Vo(xianReservoirListMapper.getPointDetailById(id));
    }
}
