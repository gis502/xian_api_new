package com.gis.xian.config;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @Primary
    public DataSource dataSource() {
        DynamicDataSource ds = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put("master", master());
        map.put("slave", slave());
        ds.setTargetDataSources(map);
        ds.setDefaultTargetDataSource(master());
        return ds;
    }
}
