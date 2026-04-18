package com.gis.xian.vo;

import com.gis.xian.entity.XianDangerousSource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 危险源-详细信息
 * @TableName xian_dangerous_source
 */
@Data
public class XianDangerousSourcePointDetailVo {
    /**
     * id
     */
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 全国统一社会信用代码
     */
    private String unitCode;

    /**
     * 级别
     */
    private String level;

    /**
     * 企业类型
     */
    private String enterpriseType;

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
     * 单位负责人
     */
    private String unitHead;

    /**
     * 联系电话
     */
    private String telephone;

    public static XianDangerousSourcePointDetailVo entity2Vo(XianDangerousSource entity) {
        XianDangerousSourcePointDetailVo vo = new XianDangerousSourcePointDetailVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setUnitCode(entity.getUnitCode());
        vo.setLevel(entity.getLevel());
        vo.setEnterpriseType(entity.getEnterpriseType());
        vo.setAddress(entity.getAddress());
        vo.setLon(entity.getLon() != null ? entity.getLon().doubleValue() : null);
        vo.setLat(entity.getLat() != null ? entity.getLat().doubleValue() : null);
        vo.setUnitHead(entity.getUnitHead());
        vo.setTelephone(entity.getTelephone());
        return vo;
    }

    public static List<XianDangerousSourcePointDetailVo> entity2Vo(List<XianDangerousSource> entityList) {
        List<XianDangerousSourcePointDetailVo> voList = new ArrayList<>();
        for (XianDangerousSource entity : entityList) {
            voList.add(entity2Vo(entity));
        }
        return voList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XianDangerousSourcePointDetailVo that = (XianDangerousSourcePointDetailVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) 
                && Objects.equals(unitCode, that.unitCode) && Objects.equals(level, that.level) 
                && Objects.equals(enterpriseType, that.enterpriseType) && Objects.equals(address, that.address) 
                && Objects.equals(lon, that.lon) && Objects.equals(lat, that.lat) 
                && Objects.equals(unitHead, that.unitHead) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitCode, level, enterpriseType, address, lon, lat, unitHead, telephone);
    }
}
