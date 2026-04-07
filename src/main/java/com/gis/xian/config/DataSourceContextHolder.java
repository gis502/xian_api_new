package com.gis.xian.config;

/**
 * 数据源上下文持有者
 * 使用ThreadLocal存储当前线程使用的数据源名称
 */
public class DataSourceContextHolder {
    
    /**
     * 默认数据源名称
     */
    private static final String DEFAULT_DATASOURCE = "master";
    
    /**
     * ThreadLocal存储数据源名称
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    
    /**
     * 设置数据源名称
     * @param dataSourceName 数据源名称
     */
    public static void setDataSource(String dataSourceName) {
        CONTEXT_HOLDER.set(dataSourceName);
    }
    
    /**
     * 获取数据源名称
     * @return 数据源名称
     */
    public static String getDataSource() {
        String dataSource = CONTEXT_HOLDER.get();
        return dataSource == null ? DEFAULT_DATASOURCE : dataSource;
    }
    
    /**
     * 清除数据源名称
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
