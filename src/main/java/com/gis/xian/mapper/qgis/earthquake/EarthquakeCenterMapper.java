package com.gis.xian.mapper.qgis.earthquake;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gis.xian.entity.qgis.earthquake.EarthquakeCenter;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzw
 * @description: 地震事件
 * @date 2026/5/25 下午5:21
 */
@Mapper
public interface EarthquakeCenterMapper extends BaseMapper<EarthquakeCenter> {
}
