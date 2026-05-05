package com.gis.xian.controller;

import com.alibaba.fastjson2.JSON;
import com.gis.xian.config.AlgorithmServerProperties;
import com.gis.xian.domain.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

/**
 * 算法代理控制器
 * 所有以 /algorithm-api 开头的请求都会转发到算法服务器
 */
@RestController
@RequestMapping("/algorithm-api")
@Slf4j
public class AlgorithmProxyController extends BaseController {

    @Resource
    private AlgorithmServerProperties algorithmServerProperties;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 处理所有 GET 请求
     */
    @GetMapping("/**")
    public ApiResponse<Object> proxyGet(HttpServletRequest request) {
        return proxyRequest(request, HttpMethod.GET);
    }

    /**
     * 处理所有 POST 请求
     */
    @PostMapping("/**")
    public ApiResponse<Object> proxyPost(HttpServletRequest request) throws IOException {
        return proxyRequest(request, HttpMethod.POST);
    }

    /**
     * 处理所有 PUT 请求
     */
    @PutMapping("/**")
    public ApiResponse<Object> proxyPut(HttpServletRequest request) throws IOException {
        return proxyRequest(request, HttpMethod.PUT);
    }

    /**
     * 处理所有 DELETE 请求
     */
    @DeleteMapping("/**")
    public ApiResponse<Object> proxyDelete(HttpServletRequest request) {
        return proxyRequest(request, HttpMethod.DELETE);
    }

    /**
     * 处理所有 PATCH 请求
     */
    @PatchMapping("/**")
    public ApiResponse<Object> proxyPatch(HttpServletRequest request) throws IOException {
        return proxyRequest(request, HttpMethod.PATCH);
    }

    /**
     * 通用的请求代理方法
     */
    @SuppressWarnings("unchecked")
    private ApiResponse<Object> proxyRequest(HttpServletRequest request, HttpMethod httpMethod) {
        long startTime = System.currentTimeMillis();

        try {
            // 构建目标 URL
            String targetUrl = buildTargetUrl(request);
            log.info("代理请求: {} -> {}", request.getRequestURI(), targetUrl);

            // 构建请求头
            HttpHeaders headers = buildRequestHeaders(request);

            // 构建请求体
            Object requestBody = null;
            if (httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT || httpMethod == HttpMethod.PATCH) {
                String contentType = request.getContentType();
                if (contentType != null && !contentType.isEmpty()) {
                    headers.setContentType(MediaType.parseMediaType(contentType));

                    // 读取请求体
                    byte[] bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
                    if (bodyBytes.length > 0) {
                        // 尝试解析为 JSON，如果不是 JSON 则直接使用字节数组
                        if (contentType.contains("application/json")) {
                            String jsonBody = new String(bodyBytes, StandardCharsets.UTF_8);
                            try {
                                requestBody = JSON.parse(jsonBody);
                            } catch (Exception e) {
                                requestBody = jsonBody;
                            }
                        } else {
                            requestBody = bodyBytes;
                        }
                    }
                }
            }

            // 创建 HTTP 实体
            HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

            // 发送请求到算法服务器
            ResponseEntity<String> response = restTemplate.exchange(
                targetUrl,
                httpMethod,
                entity,
                String.class
            );

            // 解析响应
            long endTime = System.currentTimeMillis();
            log.info("代理请求完成: {} -> {}, 耗时: {}ms, 状态码: {}",
                request.getRequestURI(), targetUrl, (endTime - startTime), response.getStatusCode());

            // 尝试将响应解析为 ApiResponse
            String responseBody = response.getBody();
            if (responseBody != null && !responseBody.isEmpty()) {
                try {
                    // 尝试解析为 ApiResponse 格式
                    Map<String, Object> responseMap = JSON.parseObject(responseBody, Map.class);
                    if (responseMap.containsKey("code") && responseMap.containsKey("message")) {
                        // 如果已经是 ApiResponse 格式，直接返回
                        Integer code = (Integer) responseMap.get("code");
                        String message = (String) responseMap.get("message");
                        Object data = responseMap.get("data");
                        return new ApiResponse<>(code, message, data);
                    }
                } catch (Exception e) {
                    log.debug("响应不是标准 ApiResponse 格式，将作为数据返回");
                }

                // 如果不是 ApiResponse 格式，将整个响应作为 data 返回
                return ApiResponse.ok((Object) responseBody);
            }

            return ApiResponse.ok((Object) null);

        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            log.error("代理请求失败: {} , 耗时: {}ms, 错误: {}",
                request.getRequestURI(), (endTime - startTime), e.getMessage(), e);
            return ApiResponse.error(502, "算法服务调用失败: " + e.getMessage(), null);
        }
    }

    /**
     * 构建目标 URL
     */
    private String buildTargetUrl(HttpServletRequest request) {
        // 获取原始请求路径，去掉 /algorithm-api 前缀
        String requestUri = request.getRequestURI();
        String path = requestUri.substring("/algorithm-api".length());
        
        // 确保路径以 / 开头
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        // 拼接算法服务器地址和路径
        String baseUrl = algorithmServerProperties.getUrl();
        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        
        String targetUrl = baseUrl + path;

        // 添加查询参数
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            targetUrl += "?" + queryString;
        }

        return targetUrl;
    }

    /**
     * 构建请求头
     */
    private HttpHeaders buildRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        
        // 复制所有请求头（排除一些特定的头）
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            // 排除一些不应该转发的头
            if (!shouldExcludeHeader(headerName)) {
                Enumeration<String> headerValues = request.getHeaders(headerName);
                while (headerValues.hasMoreElements()) {
                    headers.add(headerName, headerValues.nextElement());
                }
            }
        }

        return headers;
    }

    /**
     * 判断是否应该排除该请求头
     */
    private boolean shouldExcludeHeader(String headerName) {
        String lowerName = headerName.toLowerCase();
        // 排除 Host、Content-Length 等头
        return lowerName.equals("host") || 
               lowerName.equals("content-length") ||
               lowerName.equals("transfer-encoding");
    }
}
