package com.gis.xian.service;

import com.gis.xian.vo.XianRiskSpotsBasePointVo;
import com.gis.xian.vo.XianRiskSpotsPointDetailVo;

import java.util.List;

public interface XianRiskSpotsService {
    /**
     * 获取所有风险点基础信息
     * @return 风险点基础列表
     */
    List<XianRiskSpotsBasePointVo> getBasePoints();

    /**
     * 根据id获取风险点详情
     * @param id 风险点id
     * @return 风险点详情
     */
    XianRiskSpotsPointDetailVo getPointDetailById(Long id);
}
