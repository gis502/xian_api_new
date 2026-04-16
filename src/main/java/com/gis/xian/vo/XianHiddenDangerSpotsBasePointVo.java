package com.gis.xian.vo;

import lombok.Data;

import java.util.Objects;

/**
 * 地质灾害隐患点-基本点信息：滑坡、泥石流、内涝、山洪
 * @TableName xian_hidden_danger_spots
 */
@Data
public class XianHiddenDangerSpotsBasePointVo {
    /**
     * 序号
     */
    private Long id;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 维度
     */
    private Double lat;

    /**
     * 灾害类型
     */
    private String disasterType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHiddenDangerSpotsBasePointVo that = (XianHiddenDangerSpotsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) && Objects.equals(disasterType, that.disasterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat, disasterType);
    }
}