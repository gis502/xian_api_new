package com.gis.xian.entity;

import lombok.Data;

/**
 * 医院医疗机构
 * @TableName xian_hospitals
 */
@Data
public class XianHospitals {
    /**
     * id
     */
    private Long id;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 医疗卫生机构类别代码
     */
    private String typeCode;

    /**
     * 医疗机构类型（大类）
     */
    private String type;

    /**
     * 医疗机构类型（中类）
     */
    private String middleType;

    /**
     * 医疗机构类型（专业）
     */
    private String perferssionType;

    /**
     * 医院等级
     */
    private String level;

    /**
     * 医疗机构性质
     */
    private String institutionNature;

    /**
     * 总面积
     */
    private Float area;

    /**
     * 建筑面积
     */
    private Float structionArea;

    /**
     * 万元以上设备数
     */
    private Integer devices;

    /**
     * 总职工数
     */
    private Integer workers;

    /**
     * 卫生技术人员数
     */
    private Integer techWorker;

    /**
     * 护士数
     */
    private Integer nurse;

    /**
     * 工勤人数
     */
    private Integer dedicateWorker;

    /**
     * 年度总诊疗人数
     */
    private Integer sumPeople;

    /**
     * 年度入院人数
     */
    private Integer inPeople;

    /**
     * 年度出院人数
     */
    private Integer outPeople;

    /**
     * 总床位
     */
    private Integer beds;

    /**
     * 负压病床
     */
    private Integer negativeBeds;

    /**
     * ICU病床
     */
    private Integer icuBeds;

    /**
     * 院前急救专业人员数
     */
    private Integer savePeople;

    /**
     * 指挥车数
     */
    private Integer controllerCar;

    /**
     * 转运车数
     */
    private Integer transferCars;

    /**
     * 监护车数
     */
    private Integer inspectorCars;

    /**
     * 负压车数
     */
    private Integer negativeCars;

    /**
     * 采血车数
     */
    private Integer bloodCars;

    /**
     * 送血车数
     */
    private Integer sendBloodCars;

    /**
     * 安保人员数
     */
    private Integer safePeople;

    /**
     * 应急供电能力
     */
    private String emergencyPower;

    /**
     * 供水方式
     */
    private String waterSupply;

    /**
     * 供暖方式
     */
    private String heating;

    /**
     * 应急通信保障方式
     */
    private String connectionType;

    /**
     * 曾遭受的自然灾害类型
     */
    private String hadDisasterType;

    /**
     * 已有自然灾害应急预案类型
     */
    private String emergencyPlan;

    /**
     * 行政区划代码
     */
    private String governmentCode;

    /**
     * 村
     */
    private String village;

    /**
     * 市
     */
    private String city;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 空间点
     */
    private String position;

    /**
     * 统计负责人
     */
    private String statisticsHead;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 省
     */
    private String province;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 上报时间
     */
    private String reportTime;

    /**
     * 县
     */
    private String county;

    /**
     * 乡
     */
    private String country;

    /**
     * 街
     */
    private String street;

    /**
     * 填表人
     */
    private String fillName;

    /**
     * 代码类型(统一社会信用代码/机构编码)
     */
    private String institutionCodeType;

    /**
     * 统一社会信用代码/机构编码
     */
    private String institutionCode;

    /**
     * 物理主键
     */
    private String physicalKey;

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
        XianHospitals other = (XianHospitals) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTypeCode() == null ? other.getTypeCode() == null : this.getTypeCode().equals(other.getTypeCode()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMiddleType() == null ? other.getMiddleType() == null : this.getMiddleType().equals(other.getMiddleType()))
            && (this.getPerferssionType() == null ? other.getPerferssionType() == null : this.getPerferssionType().equals(other.getPerferssionType()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getInstitutionNature() == null ? other.getInstitutionNature() == null : this.getInstitutionNature().equals(other.getInstitutionNature()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getStructionArea() == null ? other.getStructionArea() == null : this.getStructionArea().equals(other.getStructionArea()))
            && (this.getDevices() == null ? other.getDevices() == null : this.getDevices().equals(other.getDevices()))
            && (this.getWorkers() == null ? other.getWorkers() == null : this.getWorkers().equals(other.getWorkers()))
            && (this.getTechWorker() == null ? other.getTechWorker() == null : this.getTechWorker().equals(other.getTechWorker()))
            && (this.getNurse() == null ? other.getNurse() == null : this.getNurse().equals(other.getNurse()))
            && (this.getDedicateWorker() == null ? other.getDedicateWorker() == null : this.getDedicateWorker().equals(other.getDedicateWorker()))
            && (this.getSumPeople() == null ? other.getSumPeople() == null : this.getSumPeople().equals(other.getSumPeople()))
            && (this.getInPeople() == null ? other.getInPeople() == null : this.getInPeople().equals(other.getInPeople()))
            && (this.getOutPeople() == null ? other.getOutPeople() == null : this.getOutPeople().equals(other.getOutPeople()))
            && (this.getBeds() == null ? other.getBeds() == null : this.getBeds().equals(other.getBeds()))
            && (this.getNegativeBeds() == null ? other.getNegativeBeds() == null : this.getNegativeBeds().equals(other.getNegativeBeds()))
            && (this.getIcuBeds() == null ? other.getIcuBeds() == null : this.getIcuBeds().equals(other.getIcuBeds()))
            && (this.getSavePeople() == null ? other.getSavePeople() == null : this.getSavePeople().equals(other.getSavePeople()))
            && (this.getControllerCar() == null ? other.getControllerCar() == null : this.getControllerCar().equals(other.getControllerCar()))
            && (this.getTransferCars() == null ? other.getTransferCars() == null : this.getTransferCars().equals(other.getTransferCars()))
            && (this.getInspectorCars() == null ? other.getInspectorCars() == null : this.getInspectorCars().equals(other.getInspectorCars()))
            && (this.getNegativeCars() == null ? other.getNegativeCars() == null : this.getNegativeCars().equals(other.getNegativeCars()))
            && (this.getBloodCars() == null ? other.getBloodCars() == null : this.getBloodCars().equals(other.getBloodCars()))
            && (this.getSendBloodCars() == null ? other.getSendBloodCars() == null : this.getSendBloodCars().equals(other.getSendBloodCars()))
            && (this.getSafePeople() == null ? other.getSafePeople() == null : this.getSafePeople().equals(other.getSafePeople()))
            && (this.getEmergencyPower() == null ? other.getEmergencyPower() == null : this.getEmergencyPower().equals(other.getEmergencyPower()))
            && (this.getWaterSupply() == null ? other.getWaterSupply() == null : this.getWaterSupply().equals(other.getWaterSupply()))
            && (this.getHeating() == null ? other.getHeating() == null : this.getHeating().equals(other.getHeating()))
            && (this.getConnectionType() == null ? other.getConnectionType() == null : this.getConnectionType().equals(other.getConnectionType()))
            && (this.getHadDisasterType() == null ? other.getHadDisasterType() == null : this.getHadDisasterType().equals(other.getHadDisasterType()))
            && (this.getEmergencyPlan() == null ? other.getEmergencyPlan() == null : this.getEmergencyPlan().equals(other.getEmergencyPlan()))
            && (this.getGovernmentCode() == null ? other.getGovernmentCode() == null : this.getGovernmentCode().equals(other.getGovernmentCode()))
            && (this.getVillage() == null ? other.getVillage() == null : this.getVillage().equals(other.getVillage()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getUnitHead() == null ? other.getUnitHead() == null : this.getUnitHead().equals(other.getUnitHead()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getStatisticsHead() == null ? other.getStatisticsHead() == null : this.getStatisticsHead().equals(other.getStatisticsHead()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getFillName() == null ? other.getFillName() == null : this.getFillName().equals(other.getFillName()))
            && (this.getInstitutionCodeType() == null ? other.getInstitutionCodeType() == null : this.getInstitutionCodeType().equals(other.getInstitutionCodeType()))
            && (this.getInstitutionCode() == null ? other.getInstitutionCode() == null : this.getInstitutionCode().equals(other.getInstitutionCode()))
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
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTypeCode() == null) ? 0 : getTypeCode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMiddleType() == null) ? 0 : getMiddleType().hashCode());
        result = prime * result + ((getPerferssionType() == null) ? 0 : getPerferssionType().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getInstitutionNature() == null) ? 0 : getInstitutionNature().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getStructionArea() == null) ? 0 : getStructionArea().hashCode());
        result = prime * result + ((getDevices() == null) ? 0 : getDevices().hashCode());
        result = prime * result + ((getWorkers() == null) ? 0 : getWorkers().hashCode());
        result = prime * result + ((getTechWorker() == null) ? 0 : getTechWorker().hashCode());
        result = prime * result + ((getNurse() == null) ? 0 : getNurse().hashCode());
        result = prime * result + ((getDedicateWorker() == null) ? 0 : getDedicateWorker().hashCode());
        result = prime * result + ((getSumPeople() == null) ? 0 : getSumPeople().hashCode());
        result = prime * result + ((getInPeople() == null) ? 0 : getInPeople().hashCode());
        result = prime * result + ((getOutPeople() == null) ? 0 : getOutPeople().hashCode());
        result = prime * result + ((getBeds() == null) ? 0 : getBeds().hashCode());
        result = prime * result + ((getNegativeBeds() == null) ? 0 : getNegativeBeds().hashCode());
        result = prime * result + ((getIcuBeds() == null) ? 0 : getIcuBeds().hashCode());
        result = prime * result + ((getSavePeople() == null) ? 0 : getSavePeople().hashCode());
        result = prime * result + ((getControllerCar() == null) ? 0 : getControllerCar().hashCode());
        result = prime * result + ((getTransferCars() == null) ? 0 : getTransferCars().hashCode());
        result = prime * result + ((getInspectorCars() == null) ? 0 : getInspectorCars().hashCode());
        result = prime * result + ((getNegativeCars() == null) ? 0 : getNegativeCars().hashCode());
        result = prime * result + ((getBloodCars() == null) ? 0 : getBloodCars().hashCode());
        result = prime * result + ((getSendBloodCars() == null) ? 0 : getSendBloodCars().hashCode());
        result = prime * result + ((getSafePeople() == null) ? 0 : getSafePeople().hashCode());
        result = prime * result + ((getEmergencyPower() == null) ? 0 : getEmergencyPower().hashCode());
        result = prime * result + ((getWaterSupply() == null) ? 0 : getWaterSupply().hashCode());
        result = prime * result + ((getHeating() == null) ? 0 : getHeating().hashCode());
        result = prime * result + ((getConnectionType() == null) ? 0 : getConnectionType().hashCode());
        result = prime * result + ((getHadDisasterType() == null) ? 0 : getHadDisasterType().hashCode());
        result = prime * result + ((getEmergencyPlan() == null) ? 0 : getEmergencyPlan().hashCode());
        result = prime * result + ((getGovernmentCode() == null) ? 0 : getGovernmentCode().hashCode());
        result = prime * result + ((getVillage() == null) ? 0 : getVillage().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getUnitHead() == null) ? 0 : getUnitHead().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getStatisticsHead() == null) ? 0 : getStatisticsHead().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getFillName() == null) ? 0 : getFillName().hashCode());
        result = prime * result + ((getInstitutionCodeType() == null) ? 0 : getInstitutionCodeType().hashCode());
        result = prime * result + ((getInstitutionCode() == null) ? 0 : getInstitutionCode().hashCode());
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
        sb.append(", address=").append(address);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", type=").append(type);
        sb.append(", middleType=").append(middleType);
        sb.append(", perferssionType=").append(perferssionType);
        sb.append(", level=").append(level);
        sb.append(", institutionNature=").append(institutionNature);
        sb.append(", area=").append(area);
        sb.append(", structionArea=").append(structionArea);
        sb.append(", devices=").append(devices);
        sb.append(", workers=").append(workers);
        sb.append(", techWorker=").append(techWorker);
        sb.append(", nurse=").append(nurse);
        sb.append(", dedicateWorker=").append(dedicateWorker);
        sb.append(", sumPeople=").append(sumPeople);
        sb.append(", inPeople=").append(inPeople);
        sb.append(", outPeople=").append(outPeople);
        sb.append(", beds=").append(beds);
        sb.append(", negativeBeds=").append(negativeBeds);
        sb.append(", icuBeds=").append(icuBeds);
        sb.append(", savePeople=").append(savePeople);
        sb.append(", controllerCar=").append(controllerCar);
        sb.append(", transferCars=").append(transferCars);
        sb.append(", inspectorCars=").append(inspectorCars);
        sb.append(", negativeCars=").append(negativeCars);
        sb.append(", bloodCars=").append(bloodCars);
        sb.append(", sendBloodCars=").append(sendBloodCars);
        sb.append(", safePeople=").append(safePeople);
        sb.append(", emergencyPower=").append(emergencyPower);
        sb.append(", waterSupply=").append(waterSupply);
        sb.append(", heating=").append(heating);
        sb.append(", connectionType=").append(connectionType);
        sb.append(", hadDisasterType=").append(hadDisasterType);
        sb.append(", emergencyPlan=").append(emergencyPlan);
        sb.append(", governmentCode=").append(governmentCode);
        sb.append(", village=").append(village);
        sb.append(", city=").append(city);
        sb.append(", createName=").append(createName);
        sb.append(", unitHead=").append(unitHead);
        sb.append(", position=").append(position);
        sb.append(", statisticsHead=").append(statisticsHead);
        sb.append(", telephone=").append(telephone);
        sb.append(", province=").append(province);
        sb.append(", createTime=").append(createTime);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", county=").append(county);
        sb.append(", country=").append(country);
        sb.append(", street=").append(street);
        sb.append(", fillName=").append(fillName);
        sb.append(", institutionCodeType=").append(institutionCodeType);
        sb.append(", institutionCode=").append(institutionCode);
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