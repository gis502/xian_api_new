package com.gis.xian.service;

import com.gis.xian.vo.RainfallGridVo;

/**
 * 降雨网格服务接口
 */
public interface RainfallGridService {
    
    /**
     * 从 Redis 获取降雨网格数据
     * @return 降雨网格数据
     */
    RainfallGridVo getRainfallGridData();
    
    /**
     * 获取当前的降雨站点标识符
     * @return 标识符
     */
    String getCurrentIdentifier();
}
