package com.gis.xian.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zzw
 * @description: QGIS制图服务配置属性
 * @date 2026/6/3
 */
@Data
@Component
@ConfigurationProperties(prefix = "qgis")
public class QgisProperties {

    /**
     * QGIS服务URL
     */
    private String url;

    /**
     * QGIS基础路径
     */
    private String basePath;

    /**
     * 地震专题图模板路径
     */
    private String eqMapsTemplatePath;

    /**
     * 暴雨专题图模板路径
     */
    private String rainMapsTemplatePath;

    /**
     * 影响场GeoJSON保存路径
     */
    private String intensityGeojsonPath;

    /**
     * 地震专题图输出路径
     */
    private String eqMapsOutputPath;

    /**
     * 暴雨专题图输出路径
     */
    private String rainMapsOutputPath;


}
