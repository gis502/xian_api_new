package com.gis.xian.entity;

import lombok.Data;

/**
 * 隐患点（滑坡、泥石流、山洪、内涝）
 * @TableName xian_hidden_danger_spots
 */
@Data
public class XianHiddenDangerSpots {
    /**
     * 序号
     */
    private Long id;

    /**
     * 野外编号
     */
    private String fieldCode;

    /**
     * 省
     */
    private String province;

    /**
     * 省编号
     */
    private String provinceId;

    /**
     * 市
     */
    private String city;

    /**
     * 市编号
     */
    private String cityId;

    /**
     * 县
     */
    private String county;

    /**
     * 县编号
     */
    private String countyId;

    /**
     * 乡镇
     */
    private String village;

    /**
     * 灾害点名称
     */
    private String disasterName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 维度
     */
    private Double lat;

    /**
     * 空间
     */
    private Object geom;

    /**
     * 位置
     */
    private String position;

    /**
     * 灾害类型
     */
    private String disasterType;

    /**
     * 规模等级
     */
    private String scaleGrade;

    /**
     * 险情等级
     */
    private String riskGrade;

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
        XianHiddenDangerSpots other = (XianHiddenDangerSpots) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFieldCode() == null ? other.getFieldCode() == null : this.getFieldCode().equals(other.getFieldCode()))
                && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
                && (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
                && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
                && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
                && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
                && (this.getCountyId() == null ? other.getCountyId() == null : this.getCountyId().equals(other.getCountyId()))
                && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
                && (this.getDisasterName() == null ? other.getDisasterName() == null : this.getDisasterName().equals(other.getDisasterName()))
                && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
                && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
                && (this.getGeom() == null ? other.getGeom() == null : this.getGeom().equals(other.getGeom()))
                && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
                && (this.getDisasterType() == null ? other.getDisasterType() == null : this.getDisasterType().equals(other.getDisasterType()))
                && (this.getScaleGrade() == null ? other.getScaleGrade() == null : this.getScaleGrade().equals(other.getScaleGrade()))
                && (this.getRiskGrade() == null ? other.getRiskGrade() == null : this.getRiskGrade().equals(other.getRiskGrade()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFieldCode() == null) ? 0 : getFieldCode().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getCountyId() == null) ? 0 : getCountyId().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getDisasterName() == null) ? 0 : getDisasterName().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getGeom() == null) ? 0 : getGeom().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getDisasterType() == null) ? 0 : getDisasterType().hashCode());
        result = prime * result + ((getScaleGrade() == null) ? 0 : getScaleGrade().hashCode());
        result = prime * result + ((getRiskGrade() == null) ? 0 : getRiskGrade().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fieldCode=").append(fieldCode);
        sb.append(", province=").append(province);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", city=").append(city);
        sb.append(", cityId=").append(cityId);
        sb.append(", county=").append(county);
        sb.append(", countyId=").append(countyId);
        sb.append(", village=").append(village);
        sb.append(", disasterName=").append(disasterName);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", geom=").append(geom);
        sb.append(", position=").append(position);
        sb.append(", disasterType=").append(disasterType);
        sb.append(", scaleGrade=").append(scaleGrade);
        sb.append(", riskGrade=").append(riskGrade);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}
