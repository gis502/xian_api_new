package com.gis.xian.mapper.pub;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gis.xian.config.DataSource;
import com.gis.xian.entity.pub.DZEqEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzw
 * @description: 地震事件表
 * @date 2026/5/25 下午5:01
 */
@Mapper
public interface DZEqEventMapper extends BaseMapper<DZEqEvent> {
}
