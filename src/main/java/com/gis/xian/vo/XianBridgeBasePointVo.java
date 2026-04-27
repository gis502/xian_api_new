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
public class XianBridgeBasePointVo {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        XianBridgeBasePointVo that = (XianBridgeBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(bridgeName, that.bridgeName) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bridgeName, lon, lat);
    }
    public static XianBridgeBasePointVo entity2Vo(XianBridge entity) {
        XianBridgeBasePointVo vo = new XianBridgeBasePointVo();
        vo.setId(entity.getId());
        vo.setBridgeName(entity.getBridgeName());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianBridgeBasePointVo> entity2Vo(List<XianBridge> entityList) {
        List<XianBridgeBasePointVo> voList = new ArrayList<>();
        for (XianBridge entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

}