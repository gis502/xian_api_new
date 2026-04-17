package com.gis.xian.vo;

import com.gis.xian.entity.XianHospitals;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 医院-详细信息
 * @TableName xian_hospitals
 */
@Data
public class XianHospitalsPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 医院等级
     */
    private String level;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 年度总诊疗人数
     */
    private Integer sumPeople;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 联系电话
     */
    private String telephone;

    public static XianHospitalsPointDetailVo entity2Vo(XianHospitals entity) {
        XianHospitalsPointDetailVo vo = new XianHospitalsPointDetailVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setLevel(entity.getLevel());
        vo.setAddress(entity.getAddress());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setSumPeople(entity.getSumPeople());
        vo.setUnitHead(entity.getUnitHead());
        vo.setTelephone(entity.getTelephone());
        return vo;
    }

    public static List<XianHospitalsPointDetailVo> entity2Vo(List<XianHospitals> entityList) {
        List<XianHospitalsPointDetailVo> voList = new ArrayList<>();
        for (XianHospitals entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHospitalsPointDetailVo that = (XianHospitalsPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) 
                && Objects.equals(level, that.level) && Objects.equals(address, that.address) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) 
                && Objects.equals(sumPeople, that.sumPeople) && Objects.equals(unitHead, that.unitHead) 
                && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, address, lon, lat, sumPeople, unitHead, telephone);
    }
}
