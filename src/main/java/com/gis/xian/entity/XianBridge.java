package com.gis.xian.entity;

import lombok.Data;

/**
 * 桥梁数据
 * @TableName xian_bridge
 */
@Data
public class XianBridge {
    /**
     * ID
     */
    private Long id;

    /**
     * 区域
     */
    private String region;

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
     * 位置
     */
    private String location;

    /**
     * 建成时间
     */
    private String buildTime;

    /**
     * 类型
     */
    private String bridgeType;

    /**
     * 养护类型
     */
    private String maintainType;

    /**
     * 技术类型
     */
    private String techType;

    /**
     * 规模
     */
    private String scale;

    /**
     * 面积
     */
    private Double area;

    /**
     * 所属单位
     */
    private String master;

    /**
     * 养护单位
     */
    private String maint;

    /**
     * 备注
     */
    private String note;

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
        XianBridge other = (XianBridge) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getBridgeName() == null ? other.getBridgeName() == null : this.getBridgeName().equals(other.getBridgeName()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getBuildTime() == null ? other.getBuildTime() == null : this.getBuildTime().equals(other.getBuildTime()))
            && (this.getBridgeType() == null ? other.getBridgeType() == null : this.getBridgeType().equals(other.getBridgeType()))
            && (this.getMaintainType() == null ? other.getMaintainType() == null : this.getMaintainType().equals(other.getMaintainType()))
            && (this.getTechType() == null ? other.getTechType() == null : this.getTechType().equals(other.getTechType()))
            && (this.getScale() == null ? other.getScale() == null : this.getScale().equals(other.getScale()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getMaster() == null ? other.getMaster() == null : this.getMaster().equals(other.getMaster()))
            && (this.getMaint() == null ? other.getMaint() == null : this.getMaint().equals(other.getMaint()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getBridgeName() == null) ? 0 : getBridgeName().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getBuildTime() == null) ? 0 : getBuildTime().hashCode());
        result = prime * result + ((getBridgeType() == null) ? 0 : getBridgeType().hashCode());
        result = prime * result + ((getMaintainType() == null) ? 0 : getMaintainType().hashCode());
        result = prime * result + ((getTechType() == null) ? 0 : getTechType().hashCode());
        result = prime * result + ((getScale() == null) ? 0 : getScale().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getMaster() == null) ? 0 : getMaster().hashCode());
        result = prime * result + ((getMaint() == null) ? 0 : getMaint().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
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
        sb.append(", region=").append(region);
        sb.append(", bridgeName=").append(bridgeName);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", location=").append(location);
        sb.append(", buildTime=").append(buildTime);
        sb.append(", bridgeType=").append(bridgeType);
        sb.append(", maintainType=").append(maintainType);
        sb.append(", techType=").append(techType);
        sb.append(", scale=").append(scale);
        sb.append(", area=").append(area);
        sb.append(", master=").append(master);
        sb.append(", maint=").append(maint);
        sb.append(", note=").append(note);
        sb.append(", point=").append(point);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}