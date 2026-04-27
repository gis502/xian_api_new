package com.gis.xian.vo;

import com.gis.xian.entity.XianSubwayStations;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 地铁站点-基本点信息
 * @TableName xian_subway_stations
 */
@Data
public class XianSubwayStationsBasePointVo {
    /**
     * id
     */
    private Long id;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianSubwayStationsBasePointVo entity2Vo(XianSubwayStations entity) {
        XianSubwayStationsBasePointVo vo = new XianSubwayStationsBasePointVo();
        vo.setId(entity.getId());
        vo.setStationName(entity.getStationName());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianSubwayStationsBasePointVo> entity2Vo(List<XianSubwayStations> entityList) {
        List<XianSubwayStationsBasePointVo> voList = new ArrayList<>();
        for (XianSubwayStations entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianSubwayStationsBasePointVo that = (XianSubwayStationsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(stationName, that.stationName) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationName, lon, lat);
    }
}
