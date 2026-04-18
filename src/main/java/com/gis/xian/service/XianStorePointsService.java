package com.gis.xian.service;

import com.gis.xian.vo.XianStorePointsBasePointVo;
import com.gis.xian.vo.XianStorePointsPointDetailVo;

import java.util.List;

public interface XianStorePointsService {

    /**
     * 获取所有物资储备点基础点
     * @return 基础点列表
     */
    List<XianStorePointsBasePointVo> getBasePoints();

    /**
     * 根据id获取物资储备点详情
     * @param id 物资储备点id
     * @return 物资储备点详情
     */
    XianStorePointsPointDetailVo getPointDetailById(Long id);
}
