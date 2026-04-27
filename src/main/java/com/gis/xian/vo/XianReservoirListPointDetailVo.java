package com.gis.xian.vo;

import com.gis.xian.entity.XianReservoirList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 水库-详细信息
 * @TableName xian_reservoir_list
 */
@Data
public class XianReservoirListPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 水库名称
     */
    private String reservoirName;

    /**
     * 水库位置
     */
    private String location;

    /**
     * 安全评定结果
     */
    private String safetyAssessResult;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    public static XianReservoirListPointDetailVo entity2Vo(XianReservoirList entity) {
        XianReservoirListPointDetailVo vo = new XianReservoirListPointDetailVo();
        vo.setId(entity.getId());
        vo.setReservoirName(entity.getReservoirName());
        vo.setLocation(entity.getLocation());
        vo.setSafetyAssessResult(entity.getSafetyAssessResult());
        vo.setLon(entity.getLon());
        vo.setLat(entity.getLat());
        return vo;
    }

    public static List<XianReservoirListPointDetailVo> entity2Vo(List<XianReservoirList> entityList) {
        List<XianReservoirListPointDetailVo> voList = new ArrayList<>();
        for (XianReservoirList entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianReservoirListPointDetailVo that = (XianReservoirListPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(reservoirName, that.reservoirName) 
                && Objects.equals(location, that.location) && Objects.equals(safetyAssessResult, that.safetyAssessResult) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservoirName, location, safetyAssessResult, lon, lat);
    }
}
