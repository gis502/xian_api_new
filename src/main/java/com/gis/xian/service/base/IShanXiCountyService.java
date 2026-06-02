package com.gis.xian.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.base.ShanXiCountyDTO;
import com.gis.xian.entity.base.SXCounty;

import java.util.List;

/**
 * @author zzw
 * @description: 陕西省区县
 * @date 2026/5/26 上午9:46
 */
@DataSource("slave1")
public interface IShanXiCountyService extends IService<SXCounty> {

    /**
     * 根据距离来查询附近公里的承载体数量
     *
     * @param dis 距离（km）
     * @return 返回极震区县区
     */
    public List<ShanXiCountyDTO> getMostIntensityAreaCounty(double dis, double lon, double lat);

}
