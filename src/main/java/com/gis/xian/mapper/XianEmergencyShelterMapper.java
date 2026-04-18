package com.gis.xian.mapper;

import com.gis.xian.entity.XianEmergencyShelter;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_emergency_shelter(应急避难所)】的数据库操作Mapper
* @createDate 2026-04-18 19:39:01
* @Entity com.gis.xian.entity.XianEmergencyShelter
*/
public interface XianEmergencyShelterMapper {
    /**
     * 获取所有应急避难所基础点
     * @return 基础点列表
     */
    List<XianEmergencyShelter> getBasePoints();

    /**
     * 根据id获取应急避难所详情
     * @param id 应急避难所id
     * @return 应急避难所详情
     */
    XianEmergencyShelter getPointDetailById(Long id);
}




