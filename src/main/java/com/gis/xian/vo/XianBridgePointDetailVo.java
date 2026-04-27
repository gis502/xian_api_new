package com.gis.xian.vo;

import com.gis.xian.entity.XianBridge;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 桥梁数据
 * @TableName xian_bridge
 */
@Data
public class XianBridgePointDetailVo {
    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String bridgeName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;
    /**
     * 类型
     */
    private String bridgeType;

    /**
     * 技术类型
     */
    private String techType;


    public static XianBridgePointDetailVo entity2Vo(XianBridge entity) {
        XianBridgePointDetailVo vo = new XianBridgePointDetailVo();
        vo.setId(entity.getId());
        vo.setBridgeName(entity.getBridgeName());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setBridgeType(entity.getBridgeType());
        vo.setTechType(entity.getTechType());
        return vo;
    }

    public static List<XianBridgePointDetailVo> entity2Vo(List<XianBridge> entityList) {
        List<XianBridgePointDetailVo> voList = new ArrayList<>();
        for (XianBridge entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        XianBridgePointDetailVo that = (XianBridgePointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(bridgeName, that.bridgeName) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) && Objects.equals(bridgeType, that.bridgeType) && Objects.equals(techType, that.techType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bridgeName, lon, lat, bridgeType, techType);
    }
}