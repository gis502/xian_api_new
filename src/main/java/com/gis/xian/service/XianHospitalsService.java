package com.gis.xian.service;

import com.gis.xian.vo.XianHospitalsBasePointVo;
import com.gis.xian.vo.XianHospitalsPointDetailVo;

import java.util.List;

public interface XianHospitalsService {

    /**
     * 获取所有医院基础点
     * @return 基础点列表
     */
    List<XianHospitalsBasePointVo> getBasePoints();

    /**
     * 根据id获取医院详情
     * @param id 医院id
     * @return 医院详情
     */
    XianHospitalsPointDetailVo getPointDetailById(Long id);
}
