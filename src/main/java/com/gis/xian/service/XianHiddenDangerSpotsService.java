package com.gis.xian.service;

import com.gis.xian.vo.XianHiddenDangerSpotsBasePointVo;
import com.gis.xian.vo.XianHiddenDangerSpotsPointDetailVo;

import java.util.List;

public interface XianHiddenDangerSpotsService {

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
