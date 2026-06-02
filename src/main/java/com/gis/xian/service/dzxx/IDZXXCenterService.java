package com.gis.xian.service.dzxx;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gis.xian.config.DataSource;
import com.gis.xian.dto.dzxx.DZXXCenterDTO;
import com.gis.xian.entity.dzxx.DZXXCenter;

/**
 * @author zzw
 * @description: 地震信息
 * @date 2026/5/25 下午5:01
 */
@DataSource("slave1")
public interface IDZXXCenterService extends IService<DZXXCenter> {

    // 地震触发
    public void handle(DZXXCenterDTO trigger);

}
