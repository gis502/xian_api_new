package com.gis.xian.service.dzxx;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.dzxx.DZXXInfluenceDTO;
import com.gis.xian.dto.pub.EqAssessmentDTO;
import com.gis.xian.entity.dzxx.DZXXInfluence;
import com.gis.xian.query.EqQuery;

import java.util.List;

/**
 * @author zzw
 * @description: 地震影响场接口
 * @date 2026/5/25 下午6:09
 */
@DataSource("slave1")
public interface IDZXXInfluenceService extends IService<DZXXInfluence> {

    // 处理地震影响场数据
    public void handle(EqAssessmentDTO trigger);

    // 根据地震编码查询影响场范围
    public List<DZXXInfluenceDTO> findInfluenceById(EqQuery query);

    // 获取最大烈度影响场
    public DZXXInfluenceDTO findInfluenceMaxIntyById(EqQuery query);

}
