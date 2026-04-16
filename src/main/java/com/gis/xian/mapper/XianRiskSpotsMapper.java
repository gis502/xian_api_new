package com.gis.xian.mapper;

import com.gis.xian.entity.XianRiskSpots;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_risk_spots(地质灾害风险区)】的数据库操作Mapper
* @createDate 2026-04-11 10:38:29
* @Entity com.gis.xian.entity.XianRiskSpots
*/
public interface XianRiskSpotsMapper {
    /**
     * 获取所有风险点基础信息
     * @return 风险点基础列表
     */
    List<XianRiskSpots> getBasePoints();


    /**
     * 根据id获取风险点详情
     * @param id 风险点id
     * @return 风险点详情
     */
    XianRiskSpots getPointDetailById(Long id);
}




