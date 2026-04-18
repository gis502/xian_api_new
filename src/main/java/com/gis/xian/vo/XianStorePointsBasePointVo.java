package com.gis.xian.vo;

import com.gis.xian.entity.XianStorePoints;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 物资储备点-基本点信息
 * @TableName xian_store_points
 */
@Data
public class XianStorePointsBasePointVo {
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

    public static XianStorePointsBasePointVo entity2Vo(XianStorePoints entity) {
        XianStorePointsBasePointVo vo = new XianStorePointsBasePointVo();
        vo.setId(entity.getId());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        return vo;
    }

    public static List<XianStorePointsBasePointVo> entity2Vo(List<XianStorePoints> entityList) {
        List<XianStorePointsBasePointVo> voList = new ArrayList<>();
        for (XianStorePoints entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianStorePointsBasePointVo that = (XianStorePointsBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat);
    }
}
