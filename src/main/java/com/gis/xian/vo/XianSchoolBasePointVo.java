package com.gis.xian.vo;

import com.gis.xian.entity.XianSchool;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 学校-基本点信息
 * @TableName xian_school
 */
@Data
public class XianSchoolBasePointVo {
    /**
     * id
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianSchoolBasePointVo entity2Vo(XianSchool entity) {
        XianSchoolBasePointVo vo = new XianSchoolBasePointVo();
        vo.setId(entity.getId());
        vo.setSchoolName(entity.getSchoolName());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianSchoolBasePointVo> entity2Vo(List<XianSchool> entityList) {
        List<XianSchoolBasePointVo> voList = new ArrayList<>();
        for (XianSchool entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianSchoolBasePointVo that = (XianSchoolBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(schoolName, that.schoolName) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolName, lon, lat);
    }
}
