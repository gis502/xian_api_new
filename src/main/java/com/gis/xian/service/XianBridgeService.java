package com.gis.xian.service;

import com.gis.xian.vo.XianBridgeBasePointVo;
import com.gis.xian.vo.XianBridgePointDetailVo;

import java.util.List;

public interface XianBridgeService {

    /**
     * 获取所有桥梁基础点
     * @return 基础点列表
     */
    List<XianBridgeBasePointVo> getBasePoints();

    /**
     * 根据id获取桥梁详情
     * @param id 桥梁id
     * @return 桥梁详情
     */
    XianBridgePointDetailVo getPointDetailById(Long id);
}
