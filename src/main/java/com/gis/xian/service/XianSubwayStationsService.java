package com.gis.xian.service;

import com.gis.xian.vo.XianSubwayStationsBasePointVo;
import com.gis.xian.vo.XianSubwayStationsPointDetailVo;

import java.util.List;

public interface XianSubwayStationsService {

    /**
     * 获取所有地铁站点基础点
     * @return 基础点列表
     */
    List<XianSubwayStationsBasePointVo> getBasePoints();

    /**
     * 根据id获取地铁站点详情
     * @param id 站点id
     * @return 站点详情
     */
    XianSubwayStationsPointDetailVo getPointDetailById(Long id);
}
