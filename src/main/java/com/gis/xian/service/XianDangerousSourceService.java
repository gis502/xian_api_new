package com.gis.xian.service;

import com.gis.xian.vo.XianDangerousSourceBasePointVo;
import com.gis.xian.vo.XianDangerousSourcePointDetailVo;

import java.util.List;

public interface XianDangerousSourceService {

    /**
     * 获取所有危险源基础点
     * @return 基础点列表
     */
    List<XianDangerousSourceBasePointVo> getBasePoints();

    /**
     * 根据id获取危险源详情
     * @param id 危险源id
     * @return 危险源详情
     */
    XianDangerousSourcePointDetailVo getPointDetailById(Long id);
}
