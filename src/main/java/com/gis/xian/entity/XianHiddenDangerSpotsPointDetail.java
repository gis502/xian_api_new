package com.gis.xian.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class XianHiddenDangerSpotsPointDetail {
    /**
     * 序号
     */
    private Long id;

    /**
     * 野外编号
     */
    private String fieldCode;

    /**
     * 灾害点名称
     */
    private String disasterName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHiddenDangerSpotsPointDetail that = (XianHiddenDangerSpotsPointDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(fieldCode, that.fieldCode) && Objects.equals(disasterName, that.disasterName) && Objects.equals(position, that.position) && Objects.equals(disasterType, that.disasterType) && Objects.equals(scaleGrade, that.scaleGrade) && Objects.equals(riskGrade, that.riskGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fieldCode, disasterName, position, disasterType, scaleGrade, riskGrade);
    }
}
