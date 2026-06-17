package com.gis.xian.dto.qgis.earthquake;

import lombok.Data;

/**
 * @author zzw
 * @description: 烈度参数
 * @date 2026/5/26 上午10:33
 */
@Data
public class EarthquakeIntensityQuery {

    private double centerLon;   // 中心点经度
    private double centerLat;   // 中心点纬度
    private double semiMajor;   // 长半轴（米）
    private double semiMinor;   // 短半轴（米）
    private double rotation;    // 旋转角度（度，顺时针为正，以北为0度）
    private int numVertices;    // 生成的顶点数，越多越接近椭圆

}
