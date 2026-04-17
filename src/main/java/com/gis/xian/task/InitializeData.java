package com.gis.xian.task;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.XianHiddenDangerSpotsMapper;
import com.gis.xian.mapper.XianHospitalsMapper;
import com.gis.xian.mapper.XianRiskSpotsMapper;
import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHospitalsBasePointVo;
import com.gis.xian.vo.XianRiskSpotsBasePointVo;
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
    private XianRiskSpotsMapper xianRiskSpotsMapper;

    @Resource
    private XianHospitalsMapper xianHospitalsMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Value("${init.data.base-points.hidden-danger.rainstorm}")
    private String rainstormBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.earthquake}")
    private String earthquakeBasePointsKey;

    @Value("${init.data.base-points.risk}")
    private String riskBasePointsKey;

    @Value("${init.data.base-points.hospitals}")
    private String hospitalsBasePointsKey;

    @PostConstruct
    @Async("xianPool")
    public void init() {
        log.info("开始初始化数据");

        // 加载滑坡、泥石流、山洪、内涝隐患点信息并写入redis
        redisTemplate.opsForValue().set(rainstormBasePointsKey, JSON.toJSONString(
                XianHiddenDangerSpotsBasePointVo.entity2Vo(
                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType()))
                )
        );
        redisTemplate.opsForValue().set(earthquakeBasePointsKey, JSON.toJSONString(
                XianHiddenDangerSpotsBasePointVo.entity2Vo(
                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.EARTHQUAKE.getType()))
                )
        );

        // 加载风险点基本信息写入redis
        redisTemplate.opsForValue().set(riskBasePointsKey, JSON.toJSONString(
                XianRiskSpotsBasePointVo.entity2Vo(
                    xianRiskSpotsMapper.getBasePoints())
                )
        );

        // 加载医院基本信息写入redis
        redisTemplate.opsForValue().set(hospitalsBasePointsKey, JSON.toJSONString(
                XianHospitalsBasePointVo.entity2Vo(
                    xianHospitalsMapper.getBasePoints())
                )
        );
        log.info("初始化数据完成");
    }
}
