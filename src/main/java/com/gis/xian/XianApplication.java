package com.gis.xian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 后台启动入口
 * 启动时过滤DataSourceAutoConfiguration，避免数据源自动配置
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gis.xian.mapper") // 扫描MyBatis的Mapper接口
public class XianApplication {

    // 使用非静态变量
    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {
        // 启动Spring Boot应用并获取应用上下文
        var context = SpringApplication.run(XianApplication.class, args);

        // 从上下文中获取当前实例
        XianApplication app = context.getBean(XianApplication.class);
        System.out.println("后端服务启动成功！访问地址: http://localhost:" + app.port);
    }
}