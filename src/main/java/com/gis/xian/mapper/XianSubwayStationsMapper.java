package com.gis.xian.mapper;

import com.gis.xian.entity.XianSubwayStations;

import java.util.List;

/**
* @author strong
* @description 针对表【xian_subway_stations(西安地铁站点有属性)】的数据库操作Mapper
* @createDate 2026-04-27 14:52:32
* @Entity com.gis.xian.entity.XianSubwayStations
*/
public interface XianSubwayStationsMapper {
    /**
     * 获取所有地铁站点基础点
     * @return 基础点列表
     */
    List<XianSubwayStations> getBasePoints();

    /**
     * 根据id获取地铁站点详情
     * @param id 站点id
     * @return 站点详情
     */
    XianSubwayStations getPointDetailById(Long id);
}
