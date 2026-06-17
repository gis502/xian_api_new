package com.gis.xian.mapper.qgis.earthquake;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gis.xian.entity.qgis.earthquake.EarthquakeDistance;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzw
 * @description: 震中到省市县乡距离表
 * @date 2026/5/25 下午6:20
 */
@Mapper
public interface EarthquakeDistanceMapper extends BaseMapper<EarthquakeDistance> {
}
