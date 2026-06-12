package com.gis.xian.entity;

import lombok.Data;

/**
 * 危险源点
 * @TableName xian_dangerous_source
 */
@Data
public class XianDangerousSource {
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
     * 详细地址
     */
    private String address;

    /**
     * 是否位于化工园区
     */
    private String isInchemistry;

    /**
     * 开业（成立）时间
     */
    private String standTime;

    /**
     * 企业类型
     */
    private String enterpriseType;

    /**
     * 等级
     */
    private String level;

    /**
     * 安全生产标准化等级
     */
    private String safeProductLevel;

    /**
     * 总容积
     */
    private String sumVolume;

    /**
     * 储罐类型
     */
    private String tankType;

    /**
     * 总容积（其他说明1
     */
    private String sumVolumeOther1;

    /**
     * 总容积（其他说明3
     */
    private String sumVolumeOther3;

    /**
     * 总容积（其他说明2
     */
    private String sumVolumeOther2;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 填表人
     */
    private String fillName;

    /**
     * 空间点坐标
     */
    private String position;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 上报时间
     */
    private String reportTime;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 县
     */
    private String county;

    /**
     * 乡
     */
    private String country;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 村
     */
    private String village;

    /**
     * 行政区划代码
     */
    private String governmentCode;

    /**
     * 街道
     */
    private String street;

    /**
     * 统计负责人
     */
    private String statisticsHead;

    /**
     * 机构编码
     */
    private String structionCode;

    /**
     * 物理主键
     */
    private String physicalKey;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 县编码
     */
    private String countyCode;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 写入时间
     */
    private String writeTime;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 
     */
    private Object geom;

    /**
     * 逻辑删除标识，0未删除，1已删除
     */
    private Integer isDelete;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        XianDangerousSource other = (XianDangerousSource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUnitCode() == null ? other.getUnitCode() == null : this.getUnitCode().equals(other.getUnitCode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIsInchemistry() == null ? other.getIsInchemistry() == null : this.getIsInchemistry().equals(other.getIsInchemistry()))
            && (this.getStandTime() == null ? other.getStandTime() == null : this.getStandTime().equals(other.getStandTime()))
            && (this.getEnterpriseType() == null ? other.getEnterpriseType() == null : this.getEnterpriseType().equals(other.getEnterpriseType()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getSafeProductLevel() == null ? other.getSafeProductLevel() == null : this.getSafeProductLevel().equals(other.getSafeProductLevel()))
            && (this.getSumVolume() == null ? other.getSumVolume() == null : this.getSumVolume().equals(other.getSumVolume()))
            && (this.getTankType() == null ? other.getTankType() == null : this.getTankType().equals(other.getTankType()))
            && (this.getSumVolumeOther1() == null ? other.getSumVolumeOther1() == null : this.getSumVolumeOther1().equals(other.getSumVolumeOther1()))
            && (this.getSumVolumeOther3() == null ? other.getSumVolumeOther3() == null : this.getSumVolumeOther3().equals(other.getSumVolumeOther3()))
            && (this.getSumVolumeOther2() == null ? other.getSumVolumeOther2() == null : this.getSumVolumeOther2().equals(other.getSumVolumeOther2()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getFillName() == null ? other.getFillName() == null : this.getFillName().equals(other.getFillName()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getUnitHead() == null ? other.getUnitHead() == null : this.getUnitHead().equals(other.getUnitHead()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getGovernmentCode() == null ? other.getGovernmentCode() == null : this.getGovernmentCode().equals(other.getGovernmentCode()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getStatisticsHead() == null ? other.getStatisticsHead() == null : this.getStatisticsHead().equals(other.getStatisticsHead()))
            && (this.getStructionCode() == null ? other.getStructionCode() == null : this.getStructionCode().equals(other.getStructionCode()))
            && (this.getPhysicalKey() == null ? other.getPhysicalKey() == null : this.getPhysicalKey().equals(other.getPhysicalKey()))
            && (this.getProvinceCode() == null ? other.getProvinceCode() == null : this.getProvinceCode().equals(other.getProvinceCode()))
            && (this.getCityCode() == null ? other.getCityCode() == null : this.getCityCode().equals(other.getCityCode()))
            && (this.getCountyCode() == null ? other.getCountyCode() == null : this.getCountyCode().equals(other.getCountyCode()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getWriteTime() == null ? other.getWriteTime() == null : this.getWriteTime().equals(other.getWriteTime()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getGeom() == null ? other.getGeom() == null : this.getGeom().equals(other.getGeom()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUnitCode() == null) ? 0 : getUnitCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIsInchemistry() == null) ? 0 : getIsInchemistry().hashCode());
        result = prime * result + ((getStandTime() == null) ? 0 : getStandTime().hashCode());
        result = prime * result + ((getEnterpriseType() == null) ? 0 : getEnterpriseType().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getSafeProductLevel() == null) ? 0 : getSafeProductLevel().hashCode());
        result = prime * result + ((getSumVolume() == null) ? 0 : getSumVolume().hashCode());
        result = prime * result + ((getTankType() == null) ? 0 : getTankType().hashCode());
        result = prime * result + ((getSumVolumeOther1() == null) ? 0 : getSumVolumeOther1().hashCode());
        result = prime * result + ((getSumVolumeOther3() == null) ? 0 : getSumVolumeOther3().hashCode());
        result = prime * result + ((getSumVolumeOther2() == null) ? 0 : getSumVolumeOther2().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getFillName() == null) ? 0 : getFillName().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getUnitHead() == null) ? 0 : getUnitHead().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getGovernmentCode() == null) ? 0 : getGovernmentCode().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getStatisticsHead() == null) ? 0 : getStatisticsHead().hashCode());
        result = prime * result + ((getStructionCode() == null) ? 0 : getStructionCode().hashCode());
        result = prime * result + ((getPhysicalKey() == null) ? 0 : getPhysicalKey().hashCode());
        result = prime * result + ((getProvinceCode() == null) ? 0 : getProvinceCode().hashCode());
        result = prime * result + ((getCityCode() == null) ? 0 : getCityCode().hashCode());
        result = prime * result + ((getCountyCode() == null) ? 0 : getCountyCode().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getWriteTime() == null) ? 0 : getWriteTime().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getGeom() == null) ? 0 : getGeom().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", unitCode=").append(unitCode);
        sb.append(", address=").append(address);
        sb.append(", isInchemistry=").append(isInchemistry);
        sb.append(", standTime=").append(standTime);
        sb.append(", enterpriseType=").append(enterpriseType);
        sb.append(", level=").append(level);
        sb.append(", safeProductLevel=").append(safeProductLevel);
        sb.append(", sumVolume=").append(sumVolume);
        sb.append(", tankType=").append(tankType);
        sb.append(", sumVolumeOther1=").append(sumVolumeOther1);
        sb.append(", sumVolumeOther3=").append(sumVolumeOther3);
        sb.append(", sumVolumeOther2=").append(sumVolumeOther2);
        sb.append(", createTime=").append(createTime);
        sb.append(", fillName=").append(fillName);
        sb.append(", position=").append(position);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", telephone=").append(telephone);
        sb.append(", county=").append(county);
        sb.append(", country=").append(country);
        sb.append(", unitHead=").append(unitHead);
        sb.append(", createName=").append(createName);
        sb.append(", village=").append(village);
        sb.append(", governmentCode=").append(governmentCode);
        sb.append(", street=").append(street);
        sb.append(", statisticsHead=").append(statisticsHead);
        sb.append(", structionCode=").append(structionCode);
        sb.append(", physicalKey=").append(physicalKey);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", writeTime=").append(writeTime);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", geom=").append(geom);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}