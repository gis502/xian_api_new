package com.gis.xian.vo;

import com.gis.xian.entity.XianStorePoints;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 物资储备点-详细信息
 * @TableName xian_store_points
 */
@Data
public class XianStorePointsPointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 储备库名称
     */
    private String name;

    /**
     * 分级
     */
    private String level;

    /**
     * 类型
     */
    private String type;

    /**
     * 详细位置
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
     * 有效库容
     */
    private String volume;

    /**
     * 救援帐篷
     */
    private Long tent;

    /**
     * 发电机
     */
    private Long generator;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 联系方式
     */
    private String telephone;

    public static XianStorePointsPointDetailVo entity2Vo(XianStorePoints entity) {
        XianStorePointsPointDetailVo vo = new XianStorePointsPointDetailVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setLevel(entity.getLevel());
        vo.setType(entity.getType());
        vo.setAddress(entity.getAddress());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setVolume(entity.getVolume());
        vo.setTent(entity.getTent());
        vo.setGenerator(entity.getGenerator());
        vo.setUnitHead(entity.getUnitHead());
        vo.setTelephone(entity.getTelephone());
        return vo;
    }

    public static List<XianStorePointsPointDetailVo> entity2Vo(List<XianStorePoints> entityList) {
        List<XianStorePointsPointDetailVo> voList = new ArrayList<>();
        for (XianStorePoints entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianStorePointsPointDetailVo that = (XianStorePointsPointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) 
                && Objects.equals(level, that.level) && Objects.equals(type, that.type) 
                && Objects.equals(address, that.address) && Objects.equals(lon, that.lon) 
                && Objects.equals(lat, that.lat) && Objects.equals(volume, that.volume) 
                && Objects.equals(tent, that.tent) && Objects.equals(generator, that.generator) 
                && Objects.equals(unitHead, that.unitHead) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, type, address, lon, lat, volume, tent, generator, unitHead, telephone);
    }
}
