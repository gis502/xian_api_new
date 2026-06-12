package com.gis.xian.entity;

import lombok.Data;

/**
 * 西安地铁站点有属性
 * @TableName xian_subway_stations
 */
@Data
public class XianSubwayStations {
    /**
     * ID
     */
    private Long id;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 地铁线路
     */
    private String line;

    /**
     * 地铁站点名称
     */
    private String pointName;

    /**
     * 参照积水点
     */
    private String referToTheWaterAccumulationPoint;

    /**
     * 积水深度
     */
    private String depthOfAccumulatedWater;

    /**
     * 核算后积水深度
     */
    private String accumulatedWaterAfterAccounting;

    /**
     * 站点防水物资
     */
    private String pointOtherDefenses;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 站点经纬度
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
        XianSubwayStations other = (XianSubwayStations) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStationName() == null ? other.getStationName() == null : this.getStationName().equals(other.getStationName()))
            && (this.getLine() == null ? other.getLine() == null : this.getLine().equals(other.getLine()))
            && (this.getPointName() == null ? other.getPointName() == null : this.getPointName().equals(other.getPointName()))
            && (this.getReferToTheWaterAccumulationPoint() == null ? other.getReferToTheWaterAccumulationPoint() == null : this.getReferToTheWaterAccumulationPoint().equals(other.getReferToTheWaterAccumulationPoint()))
            && (this.getDepthOfAccumulatedWater() == null ? other.getDepthOfAccumulatedWater() == null : this.getDepthOfAccumulatedWater().equals(other.getDepthOfAccumulatedWater()))
            && (this.getAccumulatedWaterAfterAccounting() == null ? other.getAccumulatedWaterAfterAccounting() == null : this.getAccumulatedWaterAfterAccounting().equals(other.getAccumulatedWaterAfterAccounting()))
            && (this.getPointOtherDefenses() == null ? other.getPointOtherDefenses() == null : this.getPointOtherDefenses().equals(other.getPointOtherDefenses()))
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
        result = prime * result + ((getStationName() == null) ? 0 : getStationName().hashCode());
        result = prime * result + ((getLine() == null) ? 0 : getLine().hashCode());
        result = prime * result + ((getPointName() == null) ? 0 : getPointName().hashCode());
        result = prime * result + ((getReferToTheWaterAccumulationPoint() == null) ? 0 : getReferToTheWaterAccumulationPoint().hashCode());
        result = prime * result + ((getDepthOfAccumulatedWater() == null) ? 0 : getDepthOfAccumulatedWater().hashCode());
        result = prime * result + ((getAccumulatedWaterAfterAccounting() == null) ? 0 : getAccumulatedWaterAfterAccounting().hashCode());
        result = prime * result + ((getPointOtherDefenses() == null) ? 0 : getPointOtherDefenses().hashCode());
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
        sb.append(", stationName=").append(stationName);
        sb.append(", line=").append(line);
        sb.append(", pointName=").append(pointName);
        sb.append(", referToTheWaterAccumulationPoint=").append(referToTheWaterAccumulationPoint);
        sb.append(", depthOfAccumulatedWater=").append(depthOfAccumulatedWater);
        sb.append(", accumulatedWaterAfterAccounting=").append(accumulatedWaterAfterAccounting);
        sb.append(", pointOtherDefenses=").append(pointOtherDefenses);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", geom=").append(geom);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}