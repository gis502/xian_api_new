package com.gis.xian.entity;

import lombok.Data;

/**
 * 应急避难所
 * @TableName xian_emergency_shelter
 */
@Data
public class XianEmergencyShelter {
    /**
     * 
     */
    private Long id;

    /**
     * 避难所时间
     */
    private String year;

    /**
     * 避难所区县
     */
    private String district;

    /**
     * 避难所名字
     */
    private String name;

    /**
     * 避难所地址
     */
    private String address;

    /**
     * 避难所类型
     */
    private String type;

    /**
     * 避难所性质
     */
    private String constructionCategory;

    /**
     * 占地面积
     */
    private String coverArea;

    /**
     * 有效占地面积
     */
    private String effectiveRefugeArea;

    /**
     * 有效容纳人数
     */
    private String effectiveNumberOfRefugees;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 位置
     */
    private Object point;

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
        XianEmergencyShelter other = (XianEmergencyShelter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getDistrict() == null ? other.getDistrict() == null : this.getDistrict().equals(other.getDistrict()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getConstructionCategory() == null ? other.getConstructionCategory() == null : this.getConstructionCategory().equals(other.getConstructionCategory()))
            && (this.getCoverArea() == null ? other.getCoverArea() == null : this.getCoverArea().equals(other.getCoverArea()))
            && (this.getEffectiveRefugeArea() == null ? other.getEffectiveRefugeArea() == null : this.getEffectiveRefugeArea().equals(other.getEffectiveRefugeArea()))
            && (this.getEffectiveNumberOfRefugees() == null ? other.getEffectiveNumberOfRefugees() == null : this.getEffectiveNumberOfRefugees().equals(other.getEffectiveNumberOfRefugees()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getDistrict() == null) ? 0 : getDistrict().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getConstructionCategory() == null) ? 0 : getConstructionCategory().hashCode());
        result = prime * result + ((getCoverArea() == null) ? 0 : getCoverArea().hashCode());
        result = prime * result + ((getEffectiveRefugeArea() == null) ? 0 : getEffectiveRefugeArea().hashCode());
        result = prime * result + ((getEffectiveNumberOfRefugees() == null) ? 0 : getEffectiveNumberOfRefugees().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
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
        sb.append(", year=").append(year);
        sb.append(", district=").append(district);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", type=").append(type);
        sb.append(", constructionCategory=").append(constructionCategory);
        sb.append(", coverArea=").append(coverArea);
        sb.append(", effectiveRefugeArea=").append(effectiveRefugeArea);
        sb.append(", effectiveNumberOfRefugees=").append(effectiveNumberOfRefugees);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", point=").append(point);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}