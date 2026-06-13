package com.gis.xian.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 导出专用数据源的MyBatis配置
 * 独立的SqlSessionFactory和Mapper扫描路径
 */
@Configuration
@MapperScan(
    basePackages = "com.gis.xian.mapper.export",
    sqlSessionFactoryRef = "exportSqlSessionFactory"
)
public class ExportDataSourceConfig {

    /**
     * 导出专用 SqlSessionFactory
     */
    @Bean(name = "exportSqlSessionFactory")
    public SqlSessionFactory exportSqlSessionFactory(@Qualifier("exportDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        
        // 设置Mapper XML文件位置（可选，如果导出Mapper需要单独的XML目录）
        bean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mapper/export/*.xml")
        );
        
        // MyBatis配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        
        return bean.getObject();
    }
}
