package com.gis.xian.vo;

import com.gis.xian.entity.XianRiskSpots;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class XianRiskSpotsPointDetailVo {
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
     * 位置
     */
    private String position;

    /**
     * 居民户数
     */
    private Integer residentCounts;

    /**
     * 威胁财产
     */
    private Integer riskProperty;

    /**
     * 常住人口
     */
    private Integer permanentPopulation;

    /**
     * 住房
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
     * 概率
     */
    private String probability;

    public static XianRiskSpotsPointDetailVo entity2Vo(XianRiskSpots entity) {
        XianRiskSpotsPointDetailVo vo = new XianRiskSpotsPointDetailVo();
        vo.setId(entity.getId());
        vo.setRiskName(entity.getRiskName());
        vo.setUnitCode(entity.getUnitCode());
        vo.setPosition(entity.getPosition());
        vo.setResidentCounts(entity.getResidentCounts());
        vo.setRiskProperty(entity.getRiskProperty());
        vo.setPermanentPopulation(entity.getPermanentPopulation());
        vo.setHousing(entity.getHousing());
        vo.setInspectorName(entity.getInspectorName());
        vo.setInspectorTele(entity.getInspectorTele());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianRiskSpotsPointDetailVo> entity2Vo(List<XianRiskSpots> entityList) {
        List<XianRiskSpotsPointDetailVo> voList = new ArrayList<>();
        for (XianRiskSpots entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianRiskSpotsPointDetailVo that = (XianRiskSpotsPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(riskName, that.riskName) && Objects.equals(unitCode, that.unitCode) && Objects.equals(position, that.position) && Objects.equals(residentCounts, that.residentCounts) && Objects.equals(riskProperty, that.riskProperty) && Objects.equals(permanentPopulation, that.permanentPopulation) && Objects.equals(housing, that.housing) && Objects.equals(inspectorName, that.inspectorName) && Objects.equals(inspectorTele, that.inspectorTele) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, riskName, unitCode, position, residentCounts, riskProperty, permanentPopulation, housing, inspectorName, inspectorTele, lon, lat);
    }
}
