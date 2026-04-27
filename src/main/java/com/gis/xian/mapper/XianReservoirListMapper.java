package com.gis.xian.mapper;

import com.gis.xian.entity.XianReservoirList;

import java.util.List;

/**
* @author strong
* @description 针对表【xian_reservoir_list(水库信息表)】的数据库操作Mapper
* @createDate 2026-04-27 14:25:32
* @Entity com.gis.xian.entity.XianReservoirList
*/
public interface XianReservoirListMapper {
    /**
     * 获取所有水库基础点
     * @return 基础点列表
     */
    List<XianReservoirList> getBasePoints();

    /**
     * 根据id获取水库详情
     * @param id 水库id
     * @return 水库详情
     */
    XianReservoirList getPointDetailById(Long id);
}
