package com.gis.xian.service.pub;

import com.gis.xian.config.DataSource;
import com.gis.xian.params.QgisArgs;

import java.util.List;

/**
 * @author zzw
 * @description: 三方服务接口
 * @date 2026/5/25 下午6:01
 */
@DataSource("slave1")
public interface IFeignService {

    // 调用专题图
    public void invoke(List<QgisArgs> args);
}
