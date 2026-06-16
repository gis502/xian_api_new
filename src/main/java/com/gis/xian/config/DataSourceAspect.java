package com.gis.xian.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
@Slf4j
public class DataSourceAspect {

    @Around("@annotation(com.gis.xian.config.DataSource) || @within(com.gis.xian.config.DataSource)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSourceAnnotation(point);
        if (dataSource == null) {
            return point.proceed();
        }
        try {
            String dsName = dataSource.value();
            log.debug("切换数据源: {}", dsName);
            DataSourceContextHolder.setDataSource(dsName);
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDataSource();
        }
    }

    private DataSource getDataSourceAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds != null) {
            return ds;
        }
        return point.getTarget().getClass().getAnnotation(DataSource.class);
    }
}
