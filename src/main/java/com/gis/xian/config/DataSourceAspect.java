package com.gis.xian.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class DataSourceAspect {
    
    @Around("@annotation(dataSource) || @within(dataSource)")
    public Object around(ProceedingJoinPoint point, DataSource dataSource) throws Throwable {
        try {
            String dsName = dataSource.value();
            log.debug("切换数据源: {}", dsName);
            DataSourceContextHolder.setDataSource(dsName);
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDataSource();
        }
    }
}
