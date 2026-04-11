package com.gis.xian.task;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.XianHiddenDangerSpotsMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 初始化数据
 */
@Component
@Slf4j
public class InitializeData {

    @Resource
    private XianHiddenDangerSpotsMapper xianHiddenDangerSpotsMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Value("${init.data.base-points.rainstorm}")
    private String rainstormBasePointsKey;

    @Value("${init.data.base-points.earthquake}")
    private String earthquakeBasePointsKey;

    @PostConstruct
    @Async("xianPool")
    public void init() {
        log.info("开始初始化数据");

        // 加载滑坡、泥石流、山洪、内涝隐患点信息并写入redis
        redisTemplate.opsForValue().set(rainstormBasePointsKey, JSON.toJSONString(xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType())));
        redisTemplate.opsForValue().set(earthquakeBasePointsKey, JSON.toJSONString(xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.EARTHQUAKE.getType())));

        log.info("初始化数据完成");
    }
}
