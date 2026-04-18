package com.gis.xian.vo;

import com.gis.xian.entity.XianEmergencyShelter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 应急避难所-详细信息
 * @TableName xian_emergency_shelter
 */
@Data
public class XianEmergencyShelterPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 避难所名称
     */
    private String name;

    /**
     * 避难所类型
     */
    private String type;

    /**
     * 避难所地址
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
     * 可容纳最大人数
     */
    private String effectiveNumberOfRefugees;

    public static XianEmergencyShelterPointDetailVo entity2Vo(XianEmergencyShelter entity) {
        XianEmergencyShelterPointDetailVo vo = new XianEmergencyShelterPointDetailVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setType(entity.getType());
        vo.setAddress(entity.getAddress());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setEffectiveNumberOfRefugees(entity.getEffectiveNumberOfRefugees());
        return vo;
    }

    public static List<XianEmergencyShelterPointDetailVo> entity2Vo(List<XianEmergencyShelter> entityList) {
        List<XianEmergencyShelterPointDetailVo> voList = new ArrayList<>();
        for (XianEmergencyShelter entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianEmergencyShelterPointDetailVo that = (XianEmergencyShelterPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) 
                && Objects.equals(type, that.type) && Objects.equals(address, that.address) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) 
                && Objects.equals(effectiveNumberOfRefugees, that.effectiveNumberOfRefugees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, address, lon, lat, effectiveNumberOfRefugees);
    }
}
