package com.gis.basic_template_not_login_back.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源路由
 * 根据DataSourceContextHolder中设置的数据源名称，动态选择数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
