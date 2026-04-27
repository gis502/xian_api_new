package com.gis.xian.vo;

import com.gis.xian.entity.XianEmergencyShelter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 应急避难所-基本点信息
 * @TableName xian_emergency_shelter
 */
@Data
public class XianEmergencyShelterBasePointVo {
    /**
     * id
     */
    private Long id;
    /**
     * 避难所名字
     */
    private String name;


    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianEmergencyShelterBasePointVo entity2Vo(XianEmergencyShelter entity) {
        XianEmergencyShelterBasePointVo vo = new XianEmergencyShelterBasePointVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianEmergencyShelterBasePointVo> entity2Vo(List<XianEmergencyShelter> entityList) {
        List<XianEmergencyShelterBasePointVo> voList = new ArrayList<>();
        for (XianEmergencyShelter entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianEmergencyShelterBasePointVo that = (XianEmergencyShelterBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lon, lat);
    }
}
