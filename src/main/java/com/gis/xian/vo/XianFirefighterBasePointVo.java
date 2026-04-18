package com.gis.xian.vo;

import com.gis.xian.entity.XianFirefighter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消防站-基本点信息
 * @TableName xian_firefighter
 */
@Data
public class XianFirefighterBasePointVo {
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

    public static XianFirefighterBasePointVo entity2Vo(XianFirefighter entity) {
        XianFirefighterBasePointVo vo = new XianFirefighterBasePointVo();
        vo.setId(entity.getId());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianFirefighterBasePointVo> entity2Vo(List<XianFirefighter> entityList) {
        List<XianFirefighterBasePointVo> voList = new ArrayList<>();
        for (XianFirefighter entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianFirefighterBasePointVo that = (XianFirefighterBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat);
    }
}
