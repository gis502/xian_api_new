package com.gis.xian.task;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.XianDangerousSourceMapper;
import com.gis.xian.mapper.XianEmergencyShelterMapper;
import com.gis.xian.mapper.XianFirefighterMapper;
import com.gis.xian.mapper.XianHiddenDangerSpotsMapper;
import com.gis.xian.mapper.XianHospitalsMapper;
import com.gis.xian.mapper.XianRiskSpotsMapper;
import com.gis.xian.mapper.XianStorePointsMapper;
import com.gis.xian.vo.XianDangerousSourceBasePointVo;
import com.gis.xian.vo.XianEmergencyShelterBasePointVo;
import com.gis.xian.vo.XianFirefighterBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHospitalsBasePointVo;
import com.gis.xian.vo.XianRiskSpotsBasePointVo;
import com.gis.xian.vo.XianStorePointsBasePointVo;
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
    private XianDangerousSourceMapper xianDangerousSourceMapper;

    @Resource
    private XianEmergencyShelterMapper xianEmergencyShelterMapper;

    @Resource
    private XianFirefighterMapper xianFirefighterMapper;

    @Resource
    private XianStorePointsMapper xianStorePointsMapper;

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

    @Value("${init.data.base-points.dangerous-source}")
    private String dangerousSourceBasePointsKey;

    @Value("${init.data.base-points.emergency-shelter}")
    private String emergencyShelterBasePointsKey;

    @Value("${init.data.base-points.firefighter}")
    private String firefighterBasePointsKey;

    @Value("${init.data.base-points.store-points}")
    private String storePointsBasePointsKey;

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

        // 加载危险源基本信息写入redis
        redisTemplate.opsForValue().set(dangerousSourceBasePointsKey, JSON.toJSONString(
                XianDangerousSourceBasePointVo.entity2Vo(
                    xianDangerousSourceMapper.getBasePoints())
                )
        );

        // 加载应急避难所基本信息写入redis
        redisTemplate.opsForValue().set(emergencyShelterBasePointsKey, JSON.toJSONString(
                XianEmergencyShelterBasePointVo.entity2Vo(
                    xianEmergencyShelterMapper.getBasePoints())
                )
        );

        // 加载消防站基本信息写入redis
        redisTemplate.opsForValue().set(firefighterBasePointsKey, JSON.toJSONString(
                XianFirefighterBasePointVo.entity2Vo(
                    xianFirefighterMapper.getBasePoints())
                )
        );

        // 加载物资储备点基本信息写入redis
        redisTemplate.opsForValue().set(storePointsBasePointsKey, JSON.toJSONString(
                XianStorePointsBasePointVo.entity2Vo(
                    xianStorePointsMapper.getBasePoints())
                )
        );
        log.info("初始化数据完成");
    }
}
