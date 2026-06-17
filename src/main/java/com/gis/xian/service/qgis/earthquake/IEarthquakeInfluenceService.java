package com.gis.xian.service.qgis.earthquake;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.qgis.earthquake.EarthquakeInformationInfluenceDTO;
import com.gis.xian.entity.qgis.earthquake.EarthquakeInfluence;
import com.gis.xian.dto.qgis.earthquake.EarthquakeQuery;
import java.util.List;
import java.util.Map;

/**
 * @author zzw
 * @description: 地震影响场
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IEarthquakeInfluenceService extends IService<EarthquakeInfluence> {

    // 以文件形式 保存影响场
    public void handle(List<EarthquakeInformationInfluenceDTO> dzxx);

    // 获取影响场文件
    public Map<String, String> getInfluence(EarthquakeQuery query);

}
