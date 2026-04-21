package com.gis.xian.service;

import com.gis.xian.vo.XianSchoolBasePointVo;
import com.gis.xian.vo.XianSchoolPointDetailVo;

import java.util.List;

public interface XianSchoolService {

    /**
     * 获取所有学校基础点
     * @return 基础点列表
     */
    List<XianSchoolBasePointVo> getBasePoints();

    /**
     * 根据id获取学校详情
     * @param id 学校id
     * @return 学校详情
     */
    XianSchoolPointDetailVo getPointDetailById(Long id);
}
