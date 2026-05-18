package com.gis.xian.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * 路径匹配工具类
 * 支持 Ant 风格通配符：
 * - ? 匹配一个字符
 * - * 匹配零个或多个字符（不包括路径分隔符）
 * - ** 匹配零个或多个目录
 */
public class PathMatcherUtils {
    
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    
    /**
     * 检查请求路径是否匹配给定的模式列表
     * 
     * @param requestUri 请求URI
     * @param patterns 通配符模式列表
     * @return 如果匹配返回true，否则返回false
     */
    public static boolean matches(String requestUri, List<String> patterns) {
        if (patterns == null || patterns.isEmpty()) {
            return false;
        }
        
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, requestUri)) {
                return true;
            }
        }
        return false;
    }
}
