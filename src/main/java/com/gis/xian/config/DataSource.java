package com.gis.xian.config;

import java.lang.annotation.*;

/**
 * 数据源切换注解
 * 可用于类或方法上，指定使用的数据源
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    
    /**
     * 数据源名称
     * 可选值：master（主库）、slave等
     * @return 数据源名称
     */
    String value() default "master";
}
