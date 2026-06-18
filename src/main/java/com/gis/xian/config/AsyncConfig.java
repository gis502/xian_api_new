package com.gis.xian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置类
 */
@Configuration
@EnableAsync   // 开启异步支持
public class AsyncConfig {

    @Bean(name = "xianPool")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 队列容量
        executor.setQueueCapacity(100);
        // 线程名前缀
        executor.setThreadNamePrefix("async-xian-");
        // 拒绝策略：CallerRunsPolicy 表示由调用线程执行（不丢失任务）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}