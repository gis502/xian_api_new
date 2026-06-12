package com.gis.xian.entity;

import lombok.Data;

/**
 * 政府消防队伍
 * @TableName xian_firefighter
 */
@Data
public class XianFirefighter {
    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String teamName;

    /**
     * 队伍编号
     */
    private String teamId;

    /**
     * 队伍类型
     */
    private String teamType;

    /**
     * 消防站类型
     */
    private String fireType;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 建立时间
     */
    private String standTime;

    /**
     * 总面积
     */
    private Float area;

    /**
     * 建筑面积
     */
    private Float structionArea;

    /**
     * 总人数
     */
    private Integer teamNum;

    /**
     * 指挥人数
     */
    private Integer leaderNum;

    /**
     * 技术人数
     */
    private Integer techNum;

    /**
     * 消防员人数
     */
    private Integer firerNum;

    /**
     * 消防员平均年龄
     */
    private Integer averageAge;

    /**
     * 消防车总数
     */
    private Integer cars;

    /**
     * 水罐消防车数
     */
    private Integer waterCars;

    /**
     * 泡沫消防车数
     */
    private Integer foamCars;

    /**
     * 举高消防车数
     */
    private Integer highCars;

    /**
     * 专勤消防车数
     */
    private Integer dedicateCars;

    /**
     * 器材总数
     */
    private Integer devices;

    /**
     * 侦检器材数
     */
    private Integer detectionDevice;

    /**
     * 救援器材数
     */
    private Integer saveDevice;

    /**
     * 破拆器材数
     */
    private Integer destructionDevice;

    /**
     * 堵漏器材数
     */
    private Integer fillDevice;

    /**
     * 转移器材数
     */
    private Integer transferDevice;

    /**
     * 洗消器材数
     */
    private Integer washDevice;

    /**
     * 照明器材数
     */
    private Integer lightDevice;

    /**
     * 灭火器材数
     */
    private Integer fireDevice;

    /**
     * 上一年出警次数
     */
    private Integer goOut;

    /**
     * 上一年出警人次
     */
    private Integer outPeople;

    /**
     * 上一年出警车次
     */
    private Integer outCar;

    /**
     * 上报时间
     */
    private String reportTime;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 行政区划代码
     */
    private String governmentCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 县
     */
    private String county;

    /**
     * 乡
     */
    private String country;

    /**
     * 村
     */
    private String village;

    /**
     * 空间点坐标
     */
    private String position;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 市
     */
    private String city;

    /**
     * 统计负责人
     */
    private String statisticHead;

    /**
     * 街道
     */
    private String street;

    /**
     * 填表人
     */
    private String fillName;

    /**
     * 省
     */
    private String province;

    /**
     * 物理主键
     */
    private String fxpcDataidSjgl;

    /**
     * 省编码
     */
    private Integer provinceCode;

    /**
     * 市编码
     */
    private Integer cityCode;

    /**
     * 县编码
     */
    private Integer countyCode;

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
    private Float lon;

    /**
     * 纬度
     */
    private Float lat;

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
        XianFirefighter other = (XianFirefighter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTeamName() == null ? other.getTeamName() == null : this.getTeamName().equals(other.getTeamName()))
            && (this.getTeamId() == null ? other.getTeamId() == null : this.getTeamId().equals(other.getTeamId()))
            && (this.getTeamType() == null ? other.getTeamType() == null : this.getTeamType().equals(other.getTeamType()))
            && (this.getFireType() == null ? other.getFireType() == null : this.getFireType().equals(other.getFireType()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getStandTime() == null ? other.getStandTime() == null : this.getStandTime().equals(other.getStandTime()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getStructionArea() == null ? other.getStructionArea() == null : this.getStructionArea().equals(other.getStructionArea()))
            && (this.getTeamNum() == null ? other.getTeamNum() == null : this.getTeamNum().equals(other.getTeamNum()))
            && (this.getLeaderNum() == null ? other.getLeaderNum() == null : this.getLeaderNum().equals(other.getLeaderNum()))
            && (this.getTechNum() == null ? other.getTechNum() == null : this.getTechNum().equals(other.getTechNum()))
            && (this.getFirerNum() == null ? other.getFirerNum() == null : this.getFirerNum().equals(other.getFirerNum()))
            && (this.getAverageAge() == null ? other.getAverageAge() == null : this.getAverageAge().equals(other.getAverageAge()))
            && (this.getCars() == null ? other.getCars() == null : this.getCars().equals(other.getCars()))
            && (this.getWaterCars() == null ? other.getWaterCars() == null : this.getWaterCars().equals(other.getWaterCars()))
            && (this.getFoamCars() == null ? other.getFoamCars() == null : this.getFoamCars().equals(other.getFoamCars()))
            && (this.getHighCars() == null ? other.getHighCars() == null : this.getHighCars().equals(other.getHighCars()))
            && (this.getDedicateCars() == null ? other.getDedicateCars() == null : this.getDedicateCars().equals(other.getDedicateCars()))
            && (this.getDevices() == null ? other.getDevices() == null : this.getDevices().equals(other.getDevices()))
            && (this.getDetectionDevice() == null ? other.getDetectionDevice() == null : this.getDetectionDevice().equals(other.getDetectionDevice()))
            && (this.getSaveDevice() == null ? other.getSaveDevice() == null : this.getSaveDevice().equals(other.getSaveDevice()))
            && (this.getDestructionDevice() == null ? other.getDestructionDevice() == null : this.getDestructionDevice().equals(other.getDestructionDevice()))
            && (this.getFillDevice() == null ? other.getFillDevice() == null : this.getFillDevice().equals(other.getFillDevice()))
            && (this.getTransferDevice() == null ? other.getTransferDevice() == null : this.getTransferDevice().equals(other.getTransferDevice()))
            && (this.getWashDevice() == null ? other.getWashDevice() == null : this.getWashDevice().equals(other.getWashDevice()))
            && (this.getLightDevice() == null ? other.getLightDevice() == null : this.getLightDevice().equals(other.getLightDevice()))
            && (this.getFireDevice() == null ? other.getFireDevice() == null : this.getFireDevice().equals(other.getFireDevice()))
            && (this.getGoOut() == null ? other.getGoOut() == null : this.getGoOut().equals(other.getGoOut()))
            && (this.getOutPeople() == null ? other.getOutPeople() == null : this.getOutPeople().equals(other.getOutPeople()))
            && (this.getOutCar() == null ? other.getOutCar() == null : this.getOutCar().equals(other.getOutCar()))
            && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
            && (this.getUnitHead() == null ? other.getUnitHead() == null : this.getUnitHead().equals(other.getUnitHead()))
            && (this.getGovernmentCode() == null ? other.getGovernmentCode() == null : this.getGovernmentCode().equals(other.getGovernmentCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getStatisticHead() == null ? other.getStatisticHead() == null : this.getStatisticHead().equals(other.getStatisticHead()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getFillName() == null ? other.getFillName() == null : this.getFillName().equals(other.getFillName()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getFxpcDataidSjgl() == null ? other.getFxpcDataidSjgl() == null : this.getFxpcDataidSjgl().equals(other.getFxpcDataidSjgl()))
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
        result = prime * result + ((getTeamName() == null) ? 0 : getTeamName().hashCode());
        result = prime * result + ((getTeamId() == null) ? 0 : getTeamId().hashCode());
        result = prime * result + ((getTeamType() == null) ? 0 : getTeamType().hashCode());
        result = prime * result + ((getFireType() == null) ? 0 : getFireType().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getStandTime() == null) ? 0 : getStandTime().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getStructionArea() == null) ? 0 : getStructionArea().hashCode());
        result = prime * result + ((getTeamNum() == null) ? 0 : getTeamNum().hashCode());
        result = prime * result + ((getLeaderNum() == null) ? 0 : getLeaderNum().hashCode());
        result = prime * result + ((getTechNum() == null) ? 0 : getTechNum().hashCode());
        result = prime * result + ((getFirerNum() == null) ? 0 : getFirerNum().hashCode());
        result = prime * result + ((getAverageAge() == null) ? 0 : getAverageAge().hashCode());
        result = prime * result + ((getCars() == null) ? 0 : getCars().hashCode());
        result = prime * result + ((getWaterCars() == null) ? 0 : getWaterCars().hashCode());
        result = prime * result + ((getFoamCars() == null) ? 0 : getFoamCars().hashCode());
        result = prime * result + ((getHighCars() == null) ? 0 : getHighCars().hashCode());
        result = prime * result + ((getDedicateCars() == null) ? 0 : getDedicateCars().hashCode());
        result = prime * result + ((getDevices() == null) ? 0 : getDevices().hashCode());
        result = prime * result + ((getDetectionDevice() == null) ? 0 : getDetectionDevice().hashCode());
        result = prime * result + ((getSaveDevice() == null) ? 0 : getSaveDevice().hashCode());
        result = prime * result + ((getDestructionDevice() == null) ? 0 : getDestructionDevice().hashCode());
        result = prime * result + ((getFillDevice() == null) ? 0 : getFillDevice().hashCode());
        result = prime * result + ((getTransferDevice() == null) ? 0 : getTransferDevice().hashCode());
        result = prime * result + ((getWashDevice() == null) ? 0 : getWashDevice().hashCode());
        result = prime * result + ((getLightDevice() == null) ? 0 : getLightDevice().hashCode());
        result = prime * result + ((getFireDevice() == null) ? 0 : getFireDevice().hashCode());
        result = prime * result + ((getGoOut() == null) ? 0 : getGoOut().hashCode());
        result = prime * result + ((getOutPeople() == null) ? 0 : getOutPeople().hashCode());
        result = prime * result + ((getOutCar() == null) ? 0 : getOutCar().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getUnitHead() == null) ? 0 : getUnitHead().hashCode());
        result = prime * result + ((getGovernmentCode() == null) ? 0 : getGovernmentCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getStatisticHead() == null) ? 0 : getStatisticHead().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getFillName() == null) ? 0 : getFillName().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getFxpcDataidSjgl() == null) ? 0 : getFxpcDataidSjgl().hashCode());
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
        sb.append(", teamName=").append(teamName);
        sb.append(", teamId=").append(teamId);
        sb.append(", teamType=").append(teamType);
        sb.append(", fireType=").append(fireType);
        sb.append(", address=").append(address);
        sb.append(", standTime=").append(standTime);
        sb.append(", area=").append(area);
        sb.append(", structionArea=").append(structionArea);
        sb.append(", teamNum=").append(teamNum);
        sb.append(", leaderNum=").append(leaderNum);
        sb.append(", techNum=").append(techNum);
        sb.append(", firerNum=").append(firerNum);
        sb.append(", averageAge=").append(averageAge);
        sb.append(", cars=").append(cars);
        sb.append(", waterCars=").append(waterCars);
        sb.append(", foamCars=").append(foamCars);
        sb.append(", highCars=").append(highCars);
        sb.append(", dedicateCars=").append(dedicateCars);
        sb.append(", devices=").append(devices);
        sb.append(", detectionDevice=").append(detectionDevice);
        sb.append(", saveDevice=").append(saveDevice);
        sb.append(", destructionDevice=").append(destructionDevice);
        sb.append(", fillDevice=").append(fillDevice);
        sb.append(", transferDevice=").append(transferDevice);
        sb.append(", washDevice=").append(washDevice);
        sb.append(", lightDevice=").append(lightDevice);
        sb.append(", fireDevice=").append(fireDevice);
        sb.append(", goOut=").append(goOut);
        sb.append(", outPeople=").append(outPeople);
        sb.append(", outCar=").append(outCar);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", unitHead=").append(unitHead);
        sb.append(", governmentCode=").append(governmentCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", county=").append(county);
        sb.append(", country=").append(country);
        sb.append(", village=").append(village);
        sb.append(", position=").append(position);
        sb.append(", telephone=").append(telephone);
        sb.append(", createName=").append(createName);
        sb.append(", city=").append(city);
        sb.append(", statisticHead=").append(statisticHead);
        sb.append(", street=").append(street);
        sb.append(", fillName=").append(fillName);
        sb.append(", province=").append(province);
        sb.append(", fxpcDataidSjgl=").append(fxpcDataidSjgl);
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