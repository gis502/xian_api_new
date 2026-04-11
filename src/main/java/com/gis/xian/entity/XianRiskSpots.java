package com.gis.xian.entity;

import lombok.Data;

/**
 * 地质灾害风险区
 * @TableName xian_risk_spots
 */
@Data
public class XianRiskSpots {
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
     * 风险区等级
     */
    private String riskLevel;

    /**
     * 面积
     */
    private Double area;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 乡
     */
    private String country;

    /**
     * 村
     */
    private String village;

    /**
     * 位置
     */
    private String position;

    /**
     * 居民户数（户）
     */
    private Integer residentCounts;

    /**
     * 居民人口（人）
     */
    private Integer addressPopulation;

    /**
     * 威胁财产（万元）
     */
    private Integer riskProperty;

    /**
     * 常住人口（人）
     */
    private Integer permanentPopulation;

    /**
     * 住房（间）
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

    /**
     * 空间
     */
    private Object geom;

    /**
     * 逻辑删除标识，0未删除，1已删除
     */
    private Integer isDelete;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        XianRiskSpots other = (XianRiskSpots) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRiskName() == null ? other.getRiskName() == null : this.getRiskName().equals(other.getRiskName()))
            && (this.getUnitCode() == null ? other.getUnitCode() == null : this.getUnitCode().equals(other.getUnitCode()))
            && (this.getRiskLevel() == null ? other.getRiskLevel() == null : this.getRiskLevel().equals(other.getRiskLevel()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getResidentCounts() == null ? other.getResidentCounts() == null : this.getResidentCounts().equals(other.getResidentCounts()))
            && (this.getAddressPopulation() == null ? other.getAddressPopulation() == null : this.getAddressPopulation().equals(other.getAddressPopulation()))
            && (this.getRiskProperty() == null ? other.getRiskProperty() == null : this.getRiskProperty().equals(other.getRiskProperty()))
            && (this.getPermanentPopulation() == null ? other.getPermanentPopulation() == null : this.getPermanentPopulation().equals(other.getPermanentPopulation()))
            && (this.getHousing() == null ? other.getHousing() == null : this.getHousing().equals(other.getHousing()))
            && (this.getInspectorName() == null ? other.getInspectorName() == null : this.getInspectorName().equals(other.getInspectorName()))
            && (this.getInspectorTele() == null ? other.getInspectorTele() == null : this.getInspectorTele().equals(other.getInspectorTele()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getGeom() == null ? other.getGeom() == null : this.getGeom().equals(other.getGeom()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRiskName() == null) ? 0 : getRiskName().hashCode());
        result = prime * result + ((getUnitCode() == null) ? 0 : getUnitCode().hashCode());
        result = prime * result + ((getRiskLevel() == null) ? 0 : getRiskLevel().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getResidentCounts() == null) ? 0 : getResidentCounts().hashCode());
        result = prime * result + ((getAddressPopulation() == null) ? 0 : getAddressPopulation().hashCode());
        result = prime * result + ((getRiskProperty() == null) ? 0 : getRiskProperty().hashCode());
        result = prime * result + ((getPermanentPopulation() == null) ? 0 : getPermanentPopulation().hashCode());
        result = prime * result + ((getHousing() == null) ? 0 : getHousing().hashCode());
        result = prime * result + ((getInspectorName() == null) ? 0 : getInspectorName().hashCode());
        result = prime * result + ((getInspectorTele() == null) ? 0 : getInspectorTele().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getGeom() == null) ? 0 : getGeom().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}