package com.gis.xian.mapper;

import com.gis.xian.entity.XianStorePoints;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_store_points(物资储备点)】的数据库操作Mapper
* @createDate 2026-04-18 20:51:10
* @Entity com.gis.xian.entity.XianStorePoints
*/
public interface XianStorePointsMapper {
    /**
     * 获取所有物资储备点基础点
     * @return 基础点列表
     */
    List<XianStorePoints> getBasePoints();

    /**
     * 根据id获取物资储备点详情
     * @param id 物资储备点id
     * @return 物资储备点详情
     */
    XianStorePoints getPointDetailById(Long id);
}




