package com.gis.xian.mapper;

import com.gis.xian.entity.XianFirefighter;

import java.util.List;

/**
* @author wzy
* @description 针对表【xian_firefighter(政府消防队伍)】的数据库操作Mapper
* @createDate 2026-04-18 20:19:23
* @Entity com.gis.xian.entity.XianFirefighter
*/
public interface XianFirefighterMapper {
    /**
     * 获取所有消防站基础点
     * @return 基础点列表
     */
    List<XianFirefighter> getBasePoints();

    /**
     * 根据id获取消防站详情
     * @param id 消防站id
     * @return 消防站详情
     */
    XianFirefighter getPointDetailById(Long id);
}




