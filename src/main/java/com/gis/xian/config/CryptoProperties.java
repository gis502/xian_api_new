package com.gis.xian.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 加解密配置属性类
 * 从application.yml中读取safety.crypto配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "safety.crypto")
public class CryptoProperties {
    
    /**
     * 响应无需加密的路径列表
     */
    private List<String> noEncryptPaths = Collections.emptyList();
    
    /**
     * 请求无需解密的路径列表
     */
    private List<String> noDecryptPaths = Collections.emptyList();
}
