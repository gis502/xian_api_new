package com.gis.xian.mapper;

import com.gis.xian.entity.XianHospitals;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_hospitals(医院医疗机构)】的数据库操作Mapper
* @createDate 2026-04-17 08:36:51
* @Entity com.gis.xian.entity.XianHospitals
*/
public interface XianHospitalsMapper {
    /**
     * 获取所有医院基础点
     * @return 基础点列表
     */
    List<XianHospitals> getBasePoints();

    /**
     * 根据id获取医院详情
     * @param id 医院id
     * @return 医院详情
     */
    XianHospitals getPointDetailById(Long id);
}




