package com.gis.xian.entity;

import lombok.Data;

/**
 * 水库信息表
 * @TableName xian_reservoir_list
 */
@Data
public class XianReservoirList {
    /**
     * ID
     */
    private Long id;

    /**
     * 行政区划代码
     */
    private String adminCode;

    /**
     * 水库名称
     */
    private String reservoirName;

    /**
     * 水库位置
     */
    private String location;

    /**
     * 水库安全状态编码
     */
    private Integer safetyStatus;

    /**
     * 安全评定时间
     */
    private String safetyAssessTime;

    /**
     * 安全评定结果
     */
    private String safetyAssessResult;

    /**
     * 除险加固情况
     */
    private String reinforceInfo;

    /**
     * 净防洪库容(万立方米)
     */
    private Integer netFloodCapacity;

    /**
     * 工程规模编码
     */
    private Integer projectScale;

    /**
     * 是否有隐患
     */
    private String hasHiddenDanger;

    /**
     * 备注1
     */
    private Integer note1;

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
        XianReservoirList other = (XianReservoirList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdminCode() == null ? other.getAdminCode() == null : this.getAdminCode().equals(other.getAdminCode()))
            && (this.getReservoirName() == null ? other.getReservoirName() == null : this.getReservoirName().equals(other.getReservoirName()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getSafetyStatus() == null ? other.getSafetyStatus() == null : this.getSafetyStatus().equals(other.getSafetyStatus()))
            && (this.getSafetyAssessTime() == null ? other.getSafetyAssessTime() == null : this.getSafetyAssessTime().equals(other.getSafetyAssessTime()))
            && (this.getSafetyAssessResult() == null ? other.getSafetyAssessResult() == null : this.getSafetyAssessResult().equals(other.getSafetyAssessResult()))
            && (this.getReinforceInfo() == null ? other.getReinforceInfo() == null : this.getReinforceInfo().equals(other.getReinforceInfo()))
            && (this.getNetFloodCapacity() == null ? other.getNetFloodCapacity() == null : this.getNetFloodCapacity().equals(other.getNetFloodCapacity()))
            && (this.getProjectScale() == null ? other.getProjectScale() == null : this.getProjectScale().equals(other.getProjectScale()))
            && (this.getHasHiddenDanger() == null ? other.getHasHiddenDanger() == null : this.getHasHiddenDanger().equals(other.getHasHiddenDanger()))
            && (this.getNote1() == null ? other.getNote1() == null : this.getNote1().equals(other.getNote1()))
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
        result = prime * result + ((getAdminCode() == null) ? 0 : getAdminCode().hashCode());
        result = prime * result + ((getReservoirName() == null) ? 0 : getReservoirName().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getSafetyStatus() == null) ? 0 : getSafetyStatus().hashCode());
        result = prime * result + ((getSafetyAssessTime() == null) ? 0 : getSafetyAssessTime().hashCode());
        result = prime * result + ((getSafetyAssessResult() == null) ? 0 : getSafetyAssessResult().hashCode());
        result = prime * result + ((getReinforceInfo() == null) ? 0 : getReinforceInfo().hashCode());
        result = prime * result + ((getNetFloodCapacity() == null) ? 0 : getNetFloodCapacity().hashCode());
        result = prime * result + ((getProjectScale() == null) ? 0 : getProjectScale().hashCode());
        result = prime * result + ((getHasHiddenDanger() == null) ? 0 : getHasHiddenDanger().hashCode());
        result = prime * result + ((getNote1() == null) ? 0 : getNote1().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getGeom() == null) ? 0 : getGeom().hashCode());
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
        sb.append(", adminCode=").append(adminCode);
        sb.append(", reservoirName=").append(reservoirName);
        sb.append(", location=").append(location);
        sb.append(", safetyStatus=").append(safetyStatus);
        sb.append(", safetyAssessTime=").append(safetyAssessTime);
        sb.append(", safetyAssessResult=").append(safetyAssessResult);
        sb.append(", reinforceInfo=").append(reinforceInfo);
        sb.append(", netFloodCapacity=").append(netFloodCapacity);
        sb.append(", projectScale=").append(projectScale);
        sb.append(", hasHiddenDanger=").append(hasHiddenDanger);
        sb.append(", note1=").append(note1);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", geom=").append(geom);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}