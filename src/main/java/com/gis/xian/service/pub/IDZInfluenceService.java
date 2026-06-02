package com.gis.xian.service.pub;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.dzxx.DZXXInfluenceDTO;
import com.gis.xian.entity.pub.DZInfluence;
import com.gis.xian.query.EqQuery;
import java.util.List;
import java.util.Map;

/**
 * @author zzw
 * @description: 地震影响场
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IDZInfluenceService extends IService<DZInfluence> {

    // 以文件形式 保存影响场
    public void handle(List<DZXXInfluenceDTO> dzxx);

    // 获取影响场文件
    public Map<String, String> getInfluence(EqQuery query);

}
