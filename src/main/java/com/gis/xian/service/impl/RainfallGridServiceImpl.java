package com.gis.xian.service.impl;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.config.RainfallGridProperties;
import com.gis.xian.service.RainfallGridService;
import com.gis.xian.service.ex.ServiceException;
import com.gis.xian.vo.RainfallGridVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 降雨网格服务实现类
 */
@Service
public class RainfallGridServiceImpl implements RainfallGridService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Resource
    private RainfallGridProperties rainfallGridProperties;

    @Override
    public RainfallGridVo getRainfallGridData() {
        try {
            String redisKey = rainfallGridProperties.getStationGrid();
            Object gridDataObj = redisTemplate.opsForValue().get(redisKey);
            
            if (gridDataObj == null) {
                throw new ServiceException("降雨网格数据不存在，Redis Key: " + redisKey);
            }
            
            // 将 Redis 中的 JSON 字符串解析为 Map
            String gridDataJson;
            if (gridDataObj instanceof String) {
                gridDataJson = (String) gridDataObj;
            } else {
                gridDataJson = JSON.toJSONString(gridDataObj);
            }
            
            // 解析 JSON 为 Map（下划线命名）
            Map<String, Object> gridDataMap = JSON.parseObject(gridDataJson, Map.class);
            
            // 转换为 RainfallGridVo（小驼峰命名）
            return convertToVo(gridDataMap);
        } catch (Exception e) {
            throw new ServiceException("解析降雨网格数据失败: " + e.getMessage(), e);
        }
    }

    @Override
    public String getCurrentIdentifier() {
        try {
            String redisKey = rainfallGridProperties.getStationIdentifier();
            Object identifierObj = redisTemplate.opsForValue().get(redisKey);
            
            if (identifierObj == null) {
                return null;
            }
            return identifierObj.toString();
        } catch (Exception e) {
            throw new ServiceException("获取降雨站点标识符失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将 Map 转换为 RainfallGridVo
     */
    @SuppressWarnings("unchecked")
    private RainfallGridVo convertToVo(Map<String, Object> gridDataMap) {
        RainfallGridVo vo = new RainfallGridVo();
        
        // 基本字段
        vo.setId(getLongValue(gridDataMap, "id"));
        vo.setPngPath((String) gridDataMap.get("png_path"));
        vo.setResolution(getDoubleValue(gridDataMap, "resolution"));
        vo.setStationCount(getIntValue(gridDataMap, "station_count"));
        
        // 解析 query_time
        String queryTimeStr = (String) gridDataMap.get("query_time");
        if (queryTimeStr != null) {
            vo.setQueryTime(LocalDateTime.parse(
                queryTimeStr.replace(" ", "T"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            ));
        }
        
        // 解析 cesium_config
        Map<String, Object> cesiumConfigMap = (Map<String, Object>) gridDataMap.get("cesium_config");
        if (cesiumConfigMap != null) {
            vo.setCesiumConfig(parseCesiumConfig(cesiumConfigMap));
        }
        
        return vo;
    }

    /**
     * 解析 Cesium 配置
     */
    @SuppressWarnings("unchecked")
    private RainfallGridVo.CesiumConfig parseCesiumConfig(Map<String, Object> cesiumConfigMap) {
        RainfallGridVo.CesiumConfig cesiumConfig = new RainfallGridVo.CesiumConfig();
        cesiumConfig.setWidth(getDoubleValue(cesiumConfigMap, "width"));
        cesiumConfig.setHeight(getDoubleValue(cesiumConfigMap, "height"));
        
        // 解析 rectangle
        Map<String, Object> rectangleMap = (Map<String, Object>) cesiumConfigMap.get("rectangle");
        if (rectangleMap != null) {
            cesiumConfig.setRectangle(parseRectangle(rectangleMap));
        }
        
        return cesiumConfig;
    }

    /**
     * 解析矩形区域
     */
    private RainfallGridVo.CesiumConfig.Rectangle parseRectangle(Map<String, Object> rectangleMap) {
        RainfallGridVo.CesiumConfig.Rectangle rectangle = new RainfallGridVo.CesiumConfig.Rectangle();
        rectangle.setWest(getDoubleValue(rectangleMap, "west"));
        rectangle.setSouth(getDoubleValue(rectangleMap, "south"));
        rectangle.setEast(getDoubleValue(rectangleMap, "east"));
        rectangle.setNorth(getDoubleValue(rectangleMap, "north"));
        return rectangle;
    }

    /**
     * 辅助方法：从 Map 中获取 Long 值
     */
    private Long getLongValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return Long.parseLong(value.toString());
    }

    /**
     * 辅助方法：从 Map 中获取 Double 值
     */
    private Double getDoubleValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return Double.parseDouble(value.toString());
    }

    /**
     * 辅助方法：从 Map 中获取 Integer 值
     */
    private Integer getIntValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return Integer.parseInt(value.toString());
    }
}
