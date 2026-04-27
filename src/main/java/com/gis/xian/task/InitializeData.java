package com.gis.xian.task;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.enums.DisasterTypeEnum;
import com.gis.xian.mapper.*;
import com.gis.xian.vo.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

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
    private XianSchoolMapper xianSchoolMapper;

    @Resource
    private XianBridgeMapper xianBridgeMapper;

    @Resource
    private XianReservoirListMapper xianReservoirListMapper;

    @Resource
    private XianSubwayStationsMapper xianSubwayStationsMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Value("${init.data.base-points.hidden-danger.rainstorm}")
    private String rainstormBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-landslide}")
    private String rainstormLandslideKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-debris-flow}")
    private String rainstormDebrisFlowKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-flash-flood}")
    private String rainstormMountainFloodKey;

    @Value("${init.data.base-points.hidden-danger.rainstorm-water-logging}")
    private String rainstormWaterLoggingKey;

    @Value("${init.data.base-points.hidden-danger.earthquake}")
    private String earthquakeBasePointsKey;

    @Value("${init.data.base-points.hidden-danger.earthquake-landslide}")
    private String earthquakeLandslideKey;

    @Value("${init.data.base-points.hidden-danger.earthquake-debris-flow}")
    private String earthquakeDebrisFlowKey;

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

    @Value("${init.data.base-points.school}")
    private String schoolBasePointsKey;

    @Value("${init.data.base-points.bridge}")
    private String bridgeBasePointsKey;

    @Value("${init.data.base-points.reservoir}")
    private String reservoirBasePointsKey;

    @Value("${init.data.base-points.subway}")
    private String subwayBasePointsKey;

    @EventListener(ApplicationReadyEvent.class)
    @Async("xianPool")
    public void init() {
        log.info("开始初始化数据");
        // 并行执行所有数据库查询和Redis写入
        
        // 暴雨类隐患点 - 总体
        CompletableFuture<Void> rainstormFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(rainstormBasePointsKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType(), null))
                    )
            );
            log.info("加载暴雨隐患点信息（总体）并写入redis完成");
        });
        
        // 暴雨类隐患点 - 滑坡
        CompletableFuture<Void> rainstormLandslideFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(rainstormLandslideKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType(), "landslide"))
                    )
            );
            log.info("加载暴雨隐患点信息（滑坡）并写入redis完成");
        });
        
        // 暴雨类隐患点 - 泥石流
        CompletableFuture<Void> rainstormDebrisFlowFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(rainstormDebrisFlowKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType(), "debris_flow"))
                    )
            );
            log.info("加载暴雨隐患点信息（泥石流）并写入redis完成");
        });
        
        // 暴雨类隐患点 - 山洪
        CompletableFuture<Void> rainstormMountainFloodFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(rainstormMountainFloodKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType(), "flash_flood"))
                    )
            );
            log.info("加载暴雨隐患点信息（山洪）并写入redis完成");
        });
        
        // 暴雨类隐患点 - 内涝
        CompletableFuture<Void> rainstormWaterLoggingFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(rainstormWaterLoggingKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.RAINSTORM.getType(), "water_logging"))
                    )
            );
            log.info("加载暴雨隐患点信息（内涝）并写入redis完成");
        });

        // 地震类隐患点 - 总体
        CompletableFuture<Void> earthquakeFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(earthquakeBasePointsKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.EARTHQUAKE.getType(), null))
                    )
            );
            log.info("加载地震隐患点信息（总体）并写入redis完成");
        });
        
        // 地震类隐患点 - 滑坡
        CompletableFuture<Void> earthquakeLandslideFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(earthquakeLandslideKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.EARTHQUAKE.getType(), "landslide"))
                    )
            );
            log.info("加载地震隐患点信息（滑坡）并写入redis完成");
        });
        
        // 地震类隐患点 - 泥石流
        CompletableFuture<Void> earthquakeDebrisFlowFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(earthquakeDebrisFlowKey, JSON.toJSONString(
                            XianHiddenDangerSpotsBasePointVo.entity2Vo(
                                    xianHiddenDangerSpotsMapper.getBasePoints(DisasterTypeEnum.EARTHQUAKE.getType(), "debris_flow"))
                    )
            );
            log.info("加载地震隐患点信息（泥石流）并写入redis完成");
        });

        CompletableFuture<Void> riskFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(riskBasePointsKey, JSON.toJSONString(
                            XianRiskSpotsBasePointVo.entity2Vo(
                                    xianRiskSpotsMapper.getBasePoints())
                    )
            );
            log.info("加载风险点基本信息写入redis完成");
        });

        CompletableFuture<Void> hospitalsFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(hospitalsBasePointsKey, JSON.toJSONString(
                            XianHospitalsBasePointVo.entity2Vo(
                                    xianHospitalsMapper.getBasePoints())
                    )
            );
            log.info("加载医院基本信息写入redis完成");
        });

        CompletableFuture<Void> dangerousSourceFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(dangerousSourceBasePointsKey, JSON.toJSONString(
                            XianDangerousSourceBasePointVo.entity2Vo(
                                    xianDangerousSourceMapper.getBasePoints())
                    )
            );
            log.info("加载危险源基本信息写入redis完成");
        });

        CompletableFuture<Void> emergencyShelterFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(emergencyShelterBasePointsKey, JSON.toJSONString(
                            XianEmergencyShelterBasePointVo.entity2Vo(
                                    xianEmergencyShelterMapper.getBasePoints())
                    )
            );
            log.info("加载应急避难所基本信息写入redis完成");
        });

        CompletableFuture<Void> firefighterFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(firefighterBasePointsKey, JSON.toJSONString(
                            XianFirefighterBasePointVo.entity2Vo(
                                    xianFirefighterMapper.getBasePoints())
                    )
            );
            log.info("加载消防站基本信息写入redis完成");
        });

        CompletableFuture<Void> storePointsFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(storePointsBasePointsKey, JSON.toJSONString(
                            XianStorePointsBasePointVo.entity2Vo(
                                    xianStorePointsMapper.getBasePoints())
                    )
            );
            log.info("加载物资储备点基本信息写入redis完成");
        });

        CompletableFuture<Void> schoolFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(schoolBasePointsKey, JSON.toJSONString(
                            XianSchoolBasePointVo.entity2Vo(
                                    xianSchoolMapper.getBasePoints())
                    )
            );
            log.info("加载学校基本信息写入redis完成");
        });

        CompletableFuture<Void> bridgeFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(bridgeBasePointsKey, JSON.toJSONString(
                            XianBridgeBasePointVo.entity2Vo(
                                    xianBridgeMapper.getBasePoints())
                    )
            );
            log.info("加载桥梁基本信息写入redis完成");
        });

        CompletableFuture<Void> reservoirFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(reservoirBasePointsKey, JSON.toJSONString(
                            XianReservoirListBasePointVo.entity2Vo(
                                    xianReservoirListMapper.getBasePoints())
                    )
            );
            log.info("加载水库基本信息写入redis完成");
        });

        CompletableFuture<Void> subwayFuture = CompletableFuture.runAsync(() -> {
            redisTemplate.opsForValue().set(subwayBasePointsKey, JSON.toJSONString(
                            XianSubwayStationsBasePointVo.entity2Vo(
                                    xianSubwayStationsMapper.getBasePoints())
                    )
            );
            log.info("加载地铁站点基本信息写入redis完成");
        });

        // 等待所有任务完成
        CompletableFuture.allOf(
                rainstormFuture, rainstormLandslideFuture, rainstormDebrisFlowFuture,
                rainstormMountainFloodFuture, rainstormWaterLoggingFuture,
                earthquakeFuture, earthquakeLandslideFuture, earthquakeDebrisFlowFuture,
                riskFuture, hospitalsFuture,
                dangerousSourceFuture, emergencyShelterFuture, firefighterFuture, storePointsFuture, schoolFuture,
                bridgeFuture, reservoirFuture, subwayFuture
        ).join();

        log.info("初始化数据完成");
    }
}
