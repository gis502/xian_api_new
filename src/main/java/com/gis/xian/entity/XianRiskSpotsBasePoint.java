package com.gis.xian.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class XianRiskSpotsBasePoint {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianRiskSpotsBasePoint that = (XianRiskSpotsBasePoint) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat);
    }
}
