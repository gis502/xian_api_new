package com.gis.xian.mapper;

import com.gis.xian.entity.XianSchool;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_school(西安学校表)】的数据库操作Mapper
* @createDate 2026-04-21 20:30:50
* @Entity com.gis.xian.entity.XianSchool
*/
public interface XianSchoolMapper {
    /**
     * 获取所有学校基础点
     * @return 基础点列表
     */
    List<XianSchool> getBasePoints();

    /**
     * 根据id获取学校详情
     * @param id 学校id
     * @return 学校详情
     */
    XianSchool getPointDetailById(Long id);
}




