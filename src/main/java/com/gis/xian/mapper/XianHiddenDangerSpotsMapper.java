package com.gis.xian.mapper;

import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;

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
     * @return 基础点列表
     */
    List<XianHiddenDangerSpotsBasePointVo> getBasePoints(String disasterType);

    /**
     * 根据id获取隐患点详情
     * @param id 隐患点id
     * @return 隐患点详情
     */
    XianHiddenDangerSpotsPointDetailVo getPointDetailById(Long id);
}




