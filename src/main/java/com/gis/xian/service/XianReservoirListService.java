package com.gis.xian.service;

import com.gis.xian.vo.XianReservoirListBasePointVo;
import com.gis.xian.vo.XianReservoirListPointDetailVo;

import java.util.List;

public interface XianReservoirListService {

    /**
     * 获取所有水库基础点
     * @return 基础点列表
     */
    List<XianReservoirListBasePointVo> getBasePoints();

    /**
     * 根据id获取水库详情
     * @param id 水库id
     * @return 水库详情
     */
    XianReservoirListPointDetailVo getPointDetailById(Long id);
}
