package com.gis.xian.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 算法服务器配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "algorithm.server")
public class AlgorithmServerProperties {
    
    /**
     * 算法服务器地址
     */
    private String url;
}
