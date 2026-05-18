package com.gis.xian.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 降雨网格配置属性类
 * 从application.yml中读取rainfall.grid配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "rainfall.grid")
public class RainfallGridProperties {
    
    /**
     * 降雨站点网格数据 Redis Key
     */
    private String stationGrid;
    
    /**
     * 降雨站点标识符 Redis Key
     */
    private String stationIdentifier;
}
