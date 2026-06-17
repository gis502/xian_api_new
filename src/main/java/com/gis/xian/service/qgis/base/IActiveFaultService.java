package com.gis.xian.service.qgis.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.base.ActiveFaultDTO;
import com.gis.xian.entity.qgis.base.ActiveFault;

/**
 * @author zzw
 * @description: 活动断层服务
 * @date 2026/5/26 上午8:31
 */
@DataSource("slave1")
public interface IActiveFaultService extends IService<ActiveFault> {

    // 查找距离震中最近的一条断层数据
    public ActiveFaultDTO getShortlyFault(double lon, double lat);

}
