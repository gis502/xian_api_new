package com.gis.xian.mapper;

import com.gis.xian.entity.XianDangerousSource;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_dangerous_source(危险源点)】的数据库操作Mapper
* @createDate 2026-04-18 00:00:00
* @Entity com.gis.xian.entity.XianDangerousSource
*/
public interface XianDangerousSourceMapper {
    /**
     * 获取所有危险源基础点
     * @return 基础点列表
     */
    List<XianDangerousSource> getBasePoints();

    /**
     * 根据id获取危险源详情
     * @param id 危险源id
     * @return 危险源详情
     */
    XianDangerousSource getPointDetailById(Long id);
}
