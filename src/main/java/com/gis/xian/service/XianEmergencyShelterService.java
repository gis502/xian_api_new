package com.gis.xian.service;

import com.gis.xian.vo.XianEmergencyShelterBasePointVo;
import com.gis.xian.vo.XianEmergencyShelterPointDetailVo;

import java.util.List;

public interface XianEmergencyShelterService {

    /**
     * 获取所有应急避难所基础点
     * @return 基础点列表
     */
    List<XianEmergencyShelterBasePointVo> getBasePoints();

    /**
     * 根据id获取应急避难所详情
     * @param id 应急避难所id
     * @return 应急避难所详情
     */
    XianEmergencyShelterPointDetailVo getPointDetailById(Long id);
}
