package com.gis.xian.vo;

import com.gis.xian.entity.XianHospitals;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 医院-基本点信息
 * @TableName xian_hospitals
 */
@Data
public class XianHospitalsBasePointVo {
    /**
     * id
     */
    private Long id;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianHospitalsBasePointVo entity2Vo(XianHospitals entity) {
        XianHospitalsBasePointVo vo = new XianHospitalsBasePointVo();
        vo.setId(entity.getId());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianHospitalsBasePointVo> entity2Vo(List<XianHospitals> entityList) {
        List<XianHospitalsBasePointVo> voList = new ArrayList<>();
        for (XianHospitals entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianHospitalsBasePointVo that = (XianHospitalsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat);
    }
}
