package com.gis.xian.service.qgis.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.base.ShanXiTownsDTO;
import com.gis.xian.entity.qgis.base.SXTowns;

import java.util.List;

/**
 * @author zzw
 * @description: 陕西省乡镇
 * @date 2026/5/26 上午9:56
 */
@DataSource("slave1")
public interface IShanXiTownsService extends IService<SXTowns> {

    /**
     * 根据来查询附近公里的承载体数量
     * @param dis 距离（km）
     * @return 返回极震区乡镇
     */
    public List<ShanXiTownsDTO> getMostIntensityAreaTowns(double dis, double lon, double lat);

}
