package com.gis.xian.vo;

import com.gis.xian.entity.XianRiskSpots;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class XianRiskSpotsBasePointVo {
    /**
     * 序号
     */
    private Long id;

    /**
     * 风险点名称
     */
    private String riskName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 维度
     */
    private Double lat;

    public static XianRiskSpotsBasePointVo entity2Vo(XianRiskSpots entity) {
        XianRiskSpotsBasePointVo vo = new XianRiskSpotsBasePointVo();
        vo.setId(entity.getId());
        vo.setRiskName(entity.getRiskName());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianRiskSpotsBasePointVo> entity2Vo(List<XianRiskSpots> entityList) {
        List<XianRiskSpotsBasePointVo> voList = new ArrayList<>();
        for (XianRiskSpots entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianRiskSpotsBasePointVo that = (XianRiskSpotsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(riskName, that.riskName) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, riskName, lon, lat);
    }
}
