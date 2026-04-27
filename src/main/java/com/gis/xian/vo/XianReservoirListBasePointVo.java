package com.gis.xian.vo;

import com.gis.xian.entity.XianReservoirList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 水库-基本点信息
 * @TableName xian_reservoir_list
 */
@Data
public class XianReservoirListBasePointVo {
    /**
     * id
     */
    private Long id;

    /**
     * 水库名称
     */
    private String reservoirName;

    /**
     * 通用名称字段（用于统一访问）
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

    public static XianReservoirListBasePointVo entity2Vo(XianReservoirList entity) {
        XianReservoirListBasePointVo vo = new XianReservoirListBasePointVo();
        vo.setId(entity.getId());
        vo.setReservoirName(entity.getReservoirName());
        vo.setName(entity.getReservoirName()); // 设置通用name字段
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianReservoirListBasePointVo> entity2Vo(List<XianReservoirList> entityList) {
        List<XianReservoirListBasePointVo> voList = new ArrayList<>();
        for (XianReservoirList entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianReservoirListBasePointVo that = (XianReservoirListBasePointVo) o;
        return Objects.equals(id, that.id) && Objects.equals(reservoirName, that.reservoirName) && Objects.equals(name, that.name)
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservoirName, name, lon, lat);
    }
}
