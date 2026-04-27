package com.gis.xian.service;

import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;

import java.util.List;

public interface XianHiddenDangerSpotsService {

    /**
     * 获取所有基础点：滑坡、泥石流、山洪、内涝
     * @param type 灾害大类（rainstorm-暴雨, earthquake-地震）
     * @param disasterType 具体灾害类型（landslide-滑坡, debris_flow-泥石流, flash_flood-山洪, water_logging-内涝），可选
     * @return 基础点列表
     */
    List<XianHiddenDangerSpotsBasePointVo> getBasePoints(String type, String disasterType);

    /**
     * 根据id获取隐患点详情
     * @param id 隐患点id
     * @return 隐患点详情
     */
    XianHiddenDangerSpotsPointDetailVo getPointDetailById(Long id);
}
