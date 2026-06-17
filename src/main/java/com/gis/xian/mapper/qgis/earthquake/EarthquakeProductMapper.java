package com.gis.xian.mapper.qgis.earthquake;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gis.xian.entity.qgis.earthquake.EarthquakeProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzw
 * @description: EarthquakeProductMapper
 * @date 2026/5/25 下午6:01
 */
@Mapper
public interface EarthquakeProductMapper extends BaseMapper<EarthquakeProduct> {
}
