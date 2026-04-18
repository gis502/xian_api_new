package com.gis.xian.entity;

import lombok.Data;

/**
 * 物资储备点
 * @TableName xian_store_points
 */
@Data
public class XianStorePoints {
    /**
     * id
     */
    private Long id;

    /**
     * 储备库名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 分级
     */
    private String level;

    /**
     * 类型
     */
    private String type;

    /**
     * 建立时间
     */
    private String standTime;

    /**
     * 有效库容
     */
    private String volume;

    /**
     * 维护人员
     */
    private String maintenance;

    /**
     * 救灾帐篷
     */
    private Long tent;

    /**
     * 棉被
     */
    private Long quilt;

    /**
     * 棉衣
     */
    private Long clothes;

    /**
     * 毛巾被
     */
    private Long towelBlanket;

    /**
     * 毛毯
     */
    private Long blanket;

    /**
     * 睡袋
     */
    private Long sleepingBed;

    /**
     * 折叠床
     */
    private Long foldingBed;

    /**
     * 简易厕所
     */
    private Long wc;

    /**
     * 生活类物资折合金额
     */
    private Double shlwzzhje;

    /**
     * 橡皮船
     */
    private Long rubberBoat;

    /**
     * 冲锋舟
     */
    private Long rescueBoat;

    /**
     * 救生船
     */
    private Long saveBoat;

    /**
     * 救生衣
     */
    private Long saveClothes;

    /**
     * 救生圈
     */
    private Long jsq;

    /**
     * 编织袋
     */
    private Long bzd;

    /**
     * 麻袋
     */
    private Long md;

    /**
     * 抽水泵
     */
    private Long waterPump;

    /**
     * 救援类物资折和金额
     */
    private Double jylwzzhje;

    /**
     * 发电机
     */
    private Long generator;

    /**
     * 应急灯
     */
    private Long emergencyLight;

    /**
     * 其他物资折合金额
     */
    private Double qtwzzhje;

    /**
     * 救灾衣被
     */
    private Long saveClo;

    /**
     * 救援工具
     */
    private Long saveTool;

    /**
     * 折合金额
     */
    private String zhje;

    /**
     * 市
     */
    private String city;

    /**
     * 省
     */
    private String province;

    /**
     * 村
     */
    private String village;

    /**
     * 上报日期
     */
    private String reportTime;

    /**
     * 乡
     */
    private String country;

    /**
     * 创建人名称
     */
    private String creatName;

    /**
     * 县
     */
    private String county;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 填表人
     */
    private String fillName;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 写入时间
     */
    private String overwriteTime;

    /**
     * 经度
     */
    private Float lon;

    /**
     * 纬度
     */
    private Float lat;

    /**
     * 
     */
    private Object point;

    /**
     * 
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
        XianStorePoints other = (XianStorePoints) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStandTime() == null ? other.getStandTime() == null : this.getStandTime().equals(other.getStandTime()))
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getMaintenance() == null ? other.getMaintenance() == null : this.getMaintenance().equals(other.getMaintenance()))
            && (this.getTent() == null ? other.getTent() == null : this.getTent().equals(other.getTent()))
            && (this.getQuilt() == null ? other.getQuilt() == null : this.getQuilt().equals(other.getQuilt()))
            && (this.getClothes() == null ? other.getClothes() == null : this.getClothes().equals(other.getClothes()))
            && (this.getTowelBlanket() == null ? other.getTowelBlanket() == null : this.getTowelBlanket().equals(other.getTowelBlanket()))
            && (this.getBlanket() == null ? other.getBlanket() == null : this.getBlanket().equals(other.getBlanket()))
            && (this.getSleepingBed() == null ? other.getSleepingBed() == null : this.getSleepingBed().equals(other.getSleepingBed()))
            && (this.getFoldingBed() == null ? other.getFoldingBed() == null : this.getFoldingBed().equals(other.getFoldingBed()))
            && (this.getWc() == null ? other.getWc() == null : this.getWc().equals(other.getWc()))
            && (this.getShlwzzhje() == null ? other.getShlwzzhje() == null : this.getShlwzzhje().equals(other.getShlwzzhje()))
            && (this.getRubberBoat() == null ? other.getRubberBoat() == null : this.getRubberBoat().equals(other.getRubberBoat()))
            && (this.getRescueBoat() == null ? other.getRescueBoat() == null : this.getRescueBoat().equals(other.getRescueBoat()))
            && (this.getSaveBoat() == null ? other.getSaveBoat() == null : this.getSaveBoat().equals(other.getSaveBoat()))
            && (this.getSaveClothes() == null ? other.getSaveClothes() == null : this.getSaveClothes().equals(other.getSaveClothes()))
            && (this.getJsq() == null ? other.getJsq() == null : this.getJsq().equals(other.getJsq()))
            && (this.getBzd() == null ? other.getBzd() == null : this.getBzd().equals(other.getBzd()))
            && (this.getMd() == null ? other.getMd() == null : this.getMd().equals(other.getMd()))
            && (this.getWaterPump() == null ? other.getWaterPump() == null : this.getWaterPump().equals(other.getWaterPump()))
            && (this.getJylwzzhje() == null ? other.getJylwzzhje() == null : this.getJylwzzhje().equals(other.getJylwzzhje()))
            && (this.getGenerator() == null ? other.getGenerator() == null : this.getGenerator().equals(other.getGenerator()))
            && (this.getEmergencyLight() == null ? other.getEmergencyLight() == null : this.getEmergencyLight().equals(other.getEmergencyLight()))
            && (this.getQtwzzhje() == null ? other.getQtwzzhje() == null : this.getQtwzzhje().equals(other.getQtwzzhje()))
            && (this.getSaveClo() == null ? other.getSaveClo() == null : this.getSaveClo().equals(other.getSaveClo()))
            && (this.getSaveTool() == null ? other.getSaveTool() == null : this.getSaveTool().equals(other.getSaveTool()))
            && (this.getZhje() == null ? other.getZhje() == null : this.getZhje().equals(other.getZhje()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getCreatName() == null ? other.getCreatName() == null : this.getCreatName().equals(other.getCreatName()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getUnitHead() == null ? other.getUnitHead() == null : this.getUnitHead().equals(other.getUnitHead()))
            && (this.getFillName() == null ? other.getFillName() == null : this.getFillName().equals(other.getFillName()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getOverwriteTime() == null ? other.getOverwriteTime() == null : this.getOverwriteTime().equals(other.getOverwriteTime()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStandTime() == null) ? 0 : getStandTime().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getMaintenance() == null) ? 0 : getMaintenance().hashCode());
        result = prime * result + ((getTent() == null) ? 0 : getTent().hashCode());
        result = prime * result + ((getQuilt() == null) ? 0 : getQuilt().hashCode());
        result = prime * result + ((getClothes() == null) ? 0 : getClothes().hashCode());
        result = prime * result + ((getTowelBlanket() == null) ? 0 : getTowelBlanket().hashCode());
        result = prime * result + ((getBlanket() == null) ? 0 : getBlanket().hashCode());
        result = prime * result + ((getSleepingBed() == null) ? 0 : getSleepingBed().hashCode());
        result = prime * result + ((getFoldingBed() == null) ? 0 : getFoldingBed().hashCode());
        result = prime * result + ((getWc() == null) ? 0 : getWc().hashCode());
        result = prime * result + ((getShlwzzhje() == null) ? 0 : getShlwzzhje().hashCode());
        result = prime * result + ((getRubberBoat() == null) ? 0 : getRubberBoat().hashCode());
        result = prime * result + ((getRescueBoat() == null) ? 0 : getRescueBoat().hashCode());
        result = prime * result + ((getSaveBoat() == null) ? 0 : getSaveBoat().hashCode());
        result = prime * result + ((getSaveClothes() == null) ? 0 : getSaveClothes().hashCode());
        result = prime * result + ((getJsq() == null) ? 0 : getJsq().hashCode());
        result = prime * result + ((getBzd() == null) ? 0 : getBzd().hashCode());
        result = prime * result + ((getMd() == null) ? 0 : getMd().hashCode());
        result = prime * result + ((getWaterPump() == null) ? 0 : getWaterPump().hashCode());
        result = prime * result + ((getJylwzzhje() == null) ? 0 : getJylwzzhje().hashCode());
        result = prime * result + ((getGenerator() == null) ? 0 : getGenerator().hashCode());
        result = prime * result + ((getEmergencyLight() == null) ? 0 : getEmergencyLight().hashCode());
        result = prime * result + ((getQtwzzhje() == null) ? 0 : getQtwzzhje().hashCode());
        result = prime * result + ((getSaveClo() == null) ? 0 : getSaveClo().hashCode());
        result = prime * result + ((getSaveTool() == null) ? 0 : getSaveTool().hashCode());
        result = prime * result + ((getZhje() == null) ? 0 : getZhje().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getCreatName() == null) ? 0 : getCreatName().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getUnitHead() == null) ? 0 : getUnitHead().hashCode());
        result = prime * result + ((getFillName() == null) ? 0 : getFillName().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOverwriteTime() == null) ? 0 : getOverwriteTime().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
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
        sb.append(", address=").append(address);
        sb.append(", department=").append(department);
        sb.append(", level=").append(level);
        sb.append(", type=").append(type);
        sb.append(", standTime=").append(standTime);
        sb.append(", volume=").append(volume);
        sb.append(", maintenance=").append(maintenance);
        sb.append(", tent=").append(tent);
        sb.append(", quilt=").append(quilt);
        sb.append(", clothes=").append(clothes);
        sb.append(", towelBlanket=").append(towelBlanket);
        sb.append(", blanket=").append(blanket);
        sb.append(", sleepingBed=").append(sleepingBed);
        sb.append(", foldingBed=").append(foldingBed);
        sb.append(", wc=").append(wc);
        sb.append(", shlwzzhje=").append(shlwzzhje);
        sb.append(", rubberBoat=").append(rubberBoat);
        sb.append(", rescueBoat=").append(rescueBoat);
        sb.append(", saveBoat=").append(saveBoat);
        sb.append(", saveClothes=").append(saveClothes);
        sb.append(", jsq=").append(jsq);
        sb.append(", bzd=").append(bzd);
        sb.append(", md=").append(md);
        sb.append(", waterPump=").append(waterPump);
        sb.append(", jylwzzhje=").append(jylwzzhje);
        sb.append(", generator=").append(generator);
        sb.append(", emergencyLight=").append(emergencyLight);
        sb.append(", qtwzzhje=").append(qtwzzhje);
        sb.append(", saveClo=").append(saveClo);
        sb.append(", saveTool=").append(saveTool);
        sb.append(", zhje=").append(zhje);
        sb.append(", city=").append(city);
        sb.append(", province=").append(province);
        sb.append(", village=").append(village);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", country=").append(country);
        sb.append(", creatName=").append(creatName);
        sb.append(", county=").append(county);
        sb.append(", unitHead=").append(unitHead);
        sb.append(", fillName=").append(fillName);
        sb.append(", telephone=").append(telephone);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", overwriteTime=").append(overwriteTime);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", point=").append(point);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}