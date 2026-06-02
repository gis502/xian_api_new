package com.gis.xian.core.config;

import com.gis.xian.utils.http.HttpRestClient;
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
    public HttpRestClient httpRestClient(RestTemplate restTemplate) {
        HttpRestClient client = new HttpRestClient(restTemplate);
        // 添加全局默认请求头
        // client.addDefaultHeader("X-App-Id", "my-app-id");
        return client;
    }
}

