package com.gis.xian.mapper;

import com.gis.xian.entity.XianBridge;

import java.util.List;

/**
* @author strong
* @description 针对表【xian_bridge(桥梁数据)】的数据库操作Mapper
* @createDate 2026-04-27 13:01:53
* @Entity com.gis.xian.entity.XianBridge
*/
public interface XianBridgeMapper {
    /**
     * 获取所有桥梁基础点
     * @return 基础点列表
     */
    List<XianBridge> getBasePoints();

    /**
     * 根据id获取桥梁详情
     * @param id 桥梁id
     * @return 桥梁详情
     */
    XianBridge getPointDetailById(Long id);

}




