package com.gis.xian.mapper;

import com.gis.xian.entity.XianHiddenDangerSpots;

import java.util.List;

/**
 * @author wzy
 * @description 针对表【xian_hidden_danger_spots(地质灾害隐患点)】的数据库操作Mapper
 * @createDate 2026-04-09 16:18:17
 * @Entity com.gis.xian.entity.XianHiddenDangerSpots
 */
public interface XianHiddenDangerSpotsMapper {
    /**
     * 获取所有基础点：滑坡、泥石流、山洪、内涝
     * @param type 灾害大类（rainstorm-暴雨, earthquake-地震）
     * @param disasterType 具体灾害类型（landslide-滑坡, debris_flow-泥石流, flash_flood-山洪, water_logging-内涝），可选
     * @return 基础点列表
     */
    List<XianHiddenDangerSpots> getBasePoints(String type, String disasterType);

    /**
     * 根据id获取隐患点详情
     * @param id 隐患点id
     * @return 隐患点详情
     */
    XianHiddenDangerSpots getPointDetailById(Long id);
}




