package com.gis.xian.vo;

import com.gis.xian.entity.XianSubwayStations;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 地铁站点-详细信息
 * @TableName xian_subway_stations
 */
@Data
public class XianSubwayStationsPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 站点名称（地铁站名称）
     */
    private String stationName;

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
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianSubwayStationsPointDetailVo entity2Vo(XianSubwayStations entity) {
        XianSubwayStationsPointDetailVo vo = new XianSubwayStationsPointDetailVo();
        vo.setId(entity.getId());
        vo.setStationName(entity.getStationName());
        vo.setReferToTheWaterAccumulationPoint(entity.getReferToTheWaterAccumulationPoint());
        vo.setDepthOfAccumulatedWater(entity.getDepthOfAccumulatedWater());
        vo.setAccumulatedWaterAfterAccounting(entity.getAccumulatedWaterAfterAccounting());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianSubwayStationsPointDetailVo> entity2Vo(List<XianSubwayStations> entityList) {
        List<XianSubwayStationsPointDetailVo> voList = new ArrayList<>();
        for (XianSubwayStations entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianSubwayStationsPointDetailVo that = (XianSubwayStationsPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(stationName, that.stationName) 
                && Objects.equals(referToTheWaterAccumulationPoint, that.referToTheWaterAccumulationPoint) 
                && Objects.equals(depthOfAccumulatedWater, that.depthOfAccumulatedWater) 
                && Objects.equals(accumulatedWaterAfterAccounting, that.accumulatedWaterAfterAccounting) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationName, referToTheWaterAccumulationPoint, depthOfAccumulatedWater, 
                accumulatedWaterAfterAccounting, lon, lat);
    }
}
