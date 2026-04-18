package com.gis.xian.vo;

import com.gis.xian.entity.XianDangerousSource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 危险源-基本点信息
 * @TableName xian_dangerous_source
 */
@Data
public class XianDangerousSourceBasePointVo {
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

    public static XianDangerousSourceBasePointVo entity2Vo(XianDangerousSource entity) {
        XianDangerousSourceBasePointVo vo = new XianDangerousSourceBasePointVo();
        vo.setId(entity.getId());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianDangerousSourceBasePointVo> entity2Vo(List<XianDangerousSource> entityList) {
        List<XianDangerousSourceBasePointVo> voList = new ArrayList<>();
        for (XianDangerousSource entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianDangerousSourceBasePointVo that = (XianDangerousSourceBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat);
    }
}
