package com.gis.xian.vo;

import com.gis.xian.entity.XianSchool;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 学校-详细信息
 * @TableName xian_school
 */
@Data
public class XianSchoolPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校类型
     */
    private String schoolType;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 学生数
     */
    private Integer students;

    /**
     * 是否有重点保护目标
     */
    private String isImportant;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 手机号
     */
    private String telephone;

    public static XianSchoolPointDetailVo entity2Vo(XianSchool entity) {
        XianSchoolPointDetailVo vo = new XianSchoolPointDetailVo();
        vo.setId(entity.getId());
        vo.setSchoolName(entity.getSchoolName());
        vo.setSchoolType(entity.getSchoolType());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        vo.setStudents(entity.getStudents());
        vo.setIsImportant(entity.getIsImportant());
        vo.setUnitHead(entity.getUnitHead());
        vo.setTelephone(entity.getTelephone());
        return vo;
    }

    public static List<XianSchoolPointDetailVo> entity2Vo(List<XianSchool> entityList) {
        List<XianSchoolPointDetailVo> voList = new ArrayList<>();
        for (XianSchool entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianSchoolPointDetailVo that = (XianSchoolPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(schoolName, that.schoolName) 
                && Objects.equals(schoolType, that.schoolType) && Objects.equals(lon, that.lon) 
                && Objects.equals(lat, that.lat) && Objects.equals(students, that.students) 
                && Objects.equals(isImportant, that.isImportant) && Objects.equals(unitHead, that.unitHead) 
                && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolName, schoolType, lon, lat, students, isImportant, unitHead, telephone);
    }
}
