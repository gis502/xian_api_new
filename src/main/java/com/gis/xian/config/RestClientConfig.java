package com.gis.xian.config;

import com.gis.xian.utils.HttpRequestClientUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zzw
 * @description: 第三方请求配置类
 * @date 2026/5/26 下午6:40
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpRequestClientUtils httpRestClient(RestTemplate restTemplate) {
        HttpRequestClientUtils client = new HttpRequestClientUtils(restTemplate);
        // 添加全局默认请求头
        // client.addDefaultHeader("X-App-Id", "my-app-id");
        return client;
    }
}

