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
     * 队伍名称
     */
    private String teamName;

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

    public static XianFirefighterBasePointVo entity2Vo(XianFirefighter entity) {
        XianFirefighterBasePointVo vo = new XianFirefighterBasePointVo();
        vo.setId(entity.getId());
        vo.setTeamName(entity.getTeamName());
        vo.setName(entity.getTeamName()); // 设置通用name字段
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
        return Objects.equals(id, that.id) && Objects.equals(teamName, that.teamName) && Objects.equals(name, that.name) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, name, lon, lat);
    }
}
