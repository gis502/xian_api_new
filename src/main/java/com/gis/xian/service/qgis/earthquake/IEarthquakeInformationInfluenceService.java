package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInformationInfluenceDTO;
import com.gis.xian.dto.qgis.earthquake.EarthquakeAssessmentDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeGisInfluence;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;

import java.util.List;

/**
 * @author zzw
 * @description: 地震影响场接口
 * @date 2026/5/25 下午6:09
 */
@DataSource("slave1")
public interface IEarthquakeInformationInfluenceService extends IService<EarthquakeGisInfluence> {

    // 处理地震影响场数据
    public void handle(EarthquakeAssessmentDTO trigger);

    // 根据地震编码查询影响场范围
    public List<EarthquakeInformationInfluenceDTO> findInfluenceById(EarthquakeQuery query);

    // 获取最大烈度影响场
    public EarthquakeInformationInfluenceDTO findInfluenceMaxIntyById(EarthquakeQuery query);

}
