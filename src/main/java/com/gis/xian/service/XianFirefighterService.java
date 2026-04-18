package com.gis.xian.service;

import com.gis.xian.vo.XianFirefighterBasePointVo;
import com.gis.xian.vo.XianFirefighterPointDetailVo;

import java.util.List;

public interface XianFirefighterService {

    /**
     * 获取所有消防站基础点
     * @return 基础点列表
     */
    List<XianFirefighterBasePointVo> getBasePoints();

    /**
     * 根据id获取消防站详情
     * @param id 消防站id
     * @return 消防站详情
     */
    XianFirefighterPointDetailVo getPointDetailById(Long id);
}
