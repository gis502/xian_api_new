package com.gis.xian.vo;

import com.gis.xian.entity.XianFirefighter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消防站-详细信息
 * @TableName xian_firefighter
 */
@Data
public class XianFirefighterPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String teamName;

    /**
     * 队伍类型
     */
    private String teamType;

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
     * 总人数
     */
    private Integer teamNum;

    /**
     * 消防车总数
     */
    private Integer cars;

    /**
     * 消防器材数量
     */
    private Integer devices;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 联系电话
     */
    private String telephone;

    public static XianFirefighterPointDetailVo entity2Vo(XianFirefighter entity) {
        XianFirefighterPointDetailVo vo = new XianFirefighterPointDetailVo();
        vo.setId(entity.getId());
        vo.setTeamName(entity.getTeamName());
        vo.setTeamType(entity.getTeamType());
        vo.setAddress(entity.getAddress());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setTeamNum(entity.getTeamNum());
        vo.setCars(entity.getCars());
        vo.setDevices(entity.getDevices());
        vo.setUnitHead(entity.getUnitHead());
        vo.setTelephone(entity.getTelephone());
        return vo;
    }

    public static List<XianFirefighterPointDetailVo> entity2Vo(List<XianFirefighter> entityList) {
        List<XianFirefighterPointDetailVo> voList = new ArrayList<>();
        for (XianFirefighter entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianFirefighterPointDetailVo that = (XianFirefighterPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(teamName, that.teamName) && Objects.equals(teamType, that.teamType) && Objects.equals(address, that.address) && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) && Objects.equals(teamNum, that.teamNum) && Objects.equals(cars, that.cars) && Objects.equals(devices, that.devices) && Objects.equals(unitHead, that.unitHead) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, teamType, address, lon, lat, teamNum, cars, devices, unitHead, telephone);
    }
}
