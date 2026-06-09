package com.gis.xian.handler;

import com.gis.xian.config.QgisProperties;
import com.gis.xian.constant.BaseConstants;
import com.gis.xian.enums.EqMapsEnums;
import com.gis.xian.enums.RainMapsEnums;
import com.gis.xian.utils.BaseUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zzw
 * @description: 地震烈度核心服务
 * @date 2026/5/26 上午10:47
 */
@Component
public class EarthquakeHandler {

    @Resource
    private QgisProperties qgisProperties;

    /**
     * 获取不同烈度下的面积
     *
     * @param a 长轴
     * @param b 短轴
     * @return 返回椭圆的面积
     */
    public double calculateArea(double a, double b) {
        return Math.PI * a * b;
    }

    /**
     * @param M  震级
     * @param Ia 烈度
     * @author: xiaodemos
     * @date: 2025/3/25 10:57
     * @description: 计算椭圆的长轴
     * @return: 返回长轴
     */
    public double calculateRa(double M, double Ia) {
        return (Math.pow(10, (4.0293 + 1.3003 * M - Ia) / 3.6404) - 10) * 1000;
    }

    /**
     * @param M  震级
     * @param Ib 烈度
     * @author: xiaodemos
     * @date: 2025/3/25 10:58
     * @description: 计算椭圆的短轴
     * @return: 返回短轴
     */
    public double calculateRb(double M, double Ib) {
        return (Math.pow(10, (2.3816 + 1.3003 * M - Ib) / 2.8573) - 5) * 1000;
    }


    // 格式化地震三要素信息
    public String parseInfo(LocalDateTime eqTime, double magnitude, String addr) {
        String time = BaseUtils.formatTime(eqTime, true);
        return String.format("时间：%s\r\n震级：%s级\r\n位置：%s", time, magnitude, addr);
    }

    // 格式化暴雨三要素信息
    public String parseRInfo(LocalDateTime eqTime, String magnitude, String addr) {
        String time = BaseUtils.formatTime(eqTime, true);
        return String.format("时间：%s\r\n累计降雨量：%s毫米\r\n已持续：%s小时", time, magnitude, addr);
    }

    // 拼接地震图件标题
    public String combine(String eqName, String eqType, EqMapsEnums map) {
        // 地点 + 震级
        String name = eqName;
        // 图名称 + 地震类型
        String mapName = map.getName() + BaseConstants.EQ_TYPE.get(eqType);
        return String.format("%s", name + mapName);
    }

    // 拼接暴雨图件标题
    public String combineR(String rainName, String rainType, RainMapsEnums map) {
        // 地点 + 暴雨名称
        String name = rainName;
        // 图名称 + 暴雨类型
        String mapName = map.getName() + BaseConstants.RAIN_TYPE.get(rainType);
        return String.format("%s", name + mapName);
    }

    // 拼接导出路径
    public String getPath(String event, String queueId, String size, EqMapsEnums map) {
        // 地震事件 + 批次
        String batch = queueId.substring(event.length());
        String path = event + "/" + batch + "/" + size + "/" + map.getName();
        return String.format("%s", qgisProperties.getBasePath() + qgisProperties.getEqMapsOutputPath() + path + ".jpg");
    }

    // 拼接导出路径
    public String getRPath(String rainId, String queueId, String size, RainMapsEnums map) {
        // 地震事件 + 批次
        String batch = queueId.substring(rainId.length());
        String path = rainId + "/" + batch + "/" + size + "/" + map.getName();
        return String.format("%s", qgisProperties.getBasePath() + qgisProperties.getRainMapsOutputPath() + path + ".jpg");
    }

    public String format(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM月dd日HH时mm分");
        String timestamp = time.format(formatter);
        return timestamp;
    }

    public String formatTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分");
        String timestamp = time.format(formatter);
        return timestamp;
    }
}
