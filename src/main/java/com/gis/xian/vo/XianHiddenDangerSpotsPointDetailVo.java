package com.gis.xian.vo;

import com.gis.xian.entity.XianHiddenDangerSpots;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class XianHiddenDangerSpotsPointDetailVo {
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

    public static XianHiddenDangerSpotsPointDetailVo entity2Vo(XianHiddenDangerSpots entity) {
        XianHiddenDangerSpotsPointDetailVo vo = new XianHiddenDangerSpotsPointDetailVo();
        vo.setId(entity.getId());
        vo.setFieldCode(entity.getFieldCode());
        vo.setDisasterName(entity.getDisasterName());
        vo.setPosition(entity.getPosition());
        vo.setDisasterType(entity.getDisasterType());
        vo.setScaleGrade(entity.getScaleGrade());
        vo.setRiskGrade(entity.getRiskGrade());
        return vo;
    }

    public static List<XianHiddenDangerSpotsPointDetailVo> entity2Vo(List<XianHiddenDangerSpots> entityList) {
        List<XianHiddenDangerSpotsPointDetailVo> voList = new ArrayList<>();
        for (XianHiddenDangerSpots entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHiddenDangerSpotsPointDetailVo that = (XianHiddenDangerSpotsPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(fieldCode, that.fieldCode) && Objects.equals(disasterName, that.disasterName) && Objects.equals(position, that.position) && Objects.equals(disasterType, that.disasterType) && Objects.equals(scaleGrade, that.scaleGrade) && Objects.equals(riskGrade, that.riskGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fieldCode, disasterName, position, disasterType, scaleGrade, riskGrade);
    }
}
