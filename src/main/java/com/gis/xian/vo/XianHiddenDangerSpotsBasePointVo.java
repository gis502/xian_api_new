package com.gis.xian.vo;

import com.gis.xian.entity.XianHiddenDangerSpots;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 地质灾害隐患点-基本点信息：滑坡、泥石流、内涝、山洪
 * @TableName xian_hidden_danger_spots
 */
@Data
public class XianHiddenDangerSpotsBasePointVo {
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

    public static XianHiddenDangerSpotsBasePointVo entity2Vo(XianHiddenDangerSpots entity) {
        XianHiddenDangerSpotsBasePointVo vo = new XianHiddenDangerSpotsBasePointVo();
        vo.setId(entity.getId());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        vo.setDisasterType(entity.getDisasterType());
        return vo;
    }

    public static List<XianHiddenDangerSpotsBasePointVo> entity2Vo(List<XianHiddenDangerSpots> entityList) {
        List<XianHiddenDangerSpotsBasePointVo> voList = new ArrayList<>();
        for (XianHiddenDangerSpots entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    /**
     * 灾害类型
     */
    private String disasterType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHiddenDangerSpotsBasePointVo that = (XianHiddenDangerSpotsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) && Objects.equals(disasterType, that.disasterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat, disasterType);
    }
}