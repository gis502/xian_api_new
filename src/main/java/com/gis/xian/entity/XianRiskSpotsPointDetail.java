package com.gis.xian.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class XianRiskSpotsPointDetail {
    /**
     * 序号
     */
    private Long id;

    /**
     * 风险区名称
     */
    private String riskName;

    /**
     * 统一编号
     */
    private String unitCode;

    /**
     * 位置
     */
    private String position;

    /**
     * 居民户数
     */
    private Integer residentCounts;

    /**
     * 威胁财产
     */
    private Integer riskProperty;

    /**
     * 常住人口
     */
    private Integer permanentPopulation;

    /**
     * 住房
     */
    private Integer housing;

    /**
     * 巡查员姓名
     */
    private String inspectorName;

    /**
     * 巡查员电话
     */
    private String inspectorTele;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianRiskSpotsPointDetail that = (XianRiskSpotsPointDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(riskName, that.riskName) && Objects.equals(unitCode, that.unitCode) && Objects.equals(position, that.position) && Objects.equals(residentCounts, that.residentCounts) && Objects.equals(riskProperty, that.riskProperty) && Objects.equals(permanentPopulation, that.permanentPopulation) && Objects.equals(housing, that.housing) && Objects.equals(inspectorName, that.inspectorName) && Objects.equals(inspectorTele, that.inspectorTele) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, riskName, unitCode, position, residentCounts, riskProperty, permanentPopulation, housing, inspectorName, inspectorTele, lon, lat);
    }
}
