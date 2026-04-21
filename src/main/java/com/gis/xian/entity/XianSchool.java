package com.gis.xian.entity;

import java.time.LocalTime;
import lombok.Data;

/**
 * 西安学校表
 * @TableName xian_school
 */
@Data
public class XianSchool {
    /**
     * 
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校地址
     */
    private String schoolAddress;

    /**
     * 学校编码
     */
    private String schoolCode;

    /**
     * 学校类型
     */
    private String schoolType;

    /**
     * 所属部门
     */
    private String schoolCreater;

    /**
     * 学校面积
     */
    private Double area;

    /**
     * 建筑物面积
     */
    private Double constructionArea;

    /**
     * 设施
     */
    private Integer devices;

    /**
     * 
     */
    private String isImportant;

    /**
     * 工作人员
     */
    private Integer staff;

    /**
     * 学生
     */
    private Integer students;

    /**
     * 留校生
     */
    private Integer boarder;

    /**
     * 留学生
     */
    private Integer foreignStudents;

    /**
     * 教室数量
     */
    private Integer classrooms;

    /**
     * 避难所面积
     */
    private Double shelterArea;

    /**
     * 是否有医院
     */
    private String isHaveHospital;

    /**
     * 医生数量
     */
    private Integer doctorNum;

    /**
     * 安全员数量
     */
    private Integer securityStaffNum;

    /**
     * 应急电力
     */
    private String emergencyElectric;

    /**
     * 供水
     */
    private String waterMethod;

    /**
     * 供暖
     */
    private String heatingMethod;

    /**
     * 应急通信
     */
    private String emergencyConnectionMethod;

    /**
     * 灾害记录
     */
    private String isDisasterType;

    /**
     * 灾害预案
     */
    private String haveEmergencyPlanType;

    /**
     * 设施编码
     */
    private String institutionCode;

    /**
     * 创造时间
     */
    private String createTime;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 区县编码
     */
    private String code;

    /**
     * 单位负责人
     */
    private String unitHead;

    /**
     * 街道/乡镇
     */
    private String country;

    /**
     * 负责人
     */
    private String fillName;

    /**
     * 承建单位
     */
    private String createName;

    /**
     * 省
     */
    private String province;

    /**
     * 记录人
     */
    private String statisticsHead;

    /**
     * 报告时间
     */
    private String reportTime;

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
     * 区县编码
     */
    private Integer countyCode;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 写入时间
     */
    private LocalTime writeTime;

    /**
     * 位置
     */
    private Object geom;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

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
        XianSchool other = (XianSchool) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getSchoolAddress() == null ? other.getSchoolAddress() == null : this.getSchoolAddress().equals(other.getSchoolAddress()))
            && (this.getSchoolCode() == null ? other.getSchoolCode() == null : this.getSchoolCode().equals(other.getSchoolCode()))
            && (this.getSchoolType() == null ? other.getSchoolType() == null : this.getSchoolType().equals(other.getSchoolType()))
            && (this.getSchoolCreater() == null ? other.getSchoolCreater() == null : this.getSchoolCreater().equals(other.getSchoolCreater()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getConstructionArea() == null ? other.getConstructionArea() == null : this.getConstructionArea().equals(other.getConstructionArea()))
            && (this.getDevices() == null ? other.getDevices() == null : this.getDevices().equals(other.getDevices()))
            && (this.getIsImportant() == null ? other.getIsImportant() == null : this.getIsImportant().equals(other.getIsImportant()))
            && (this.getStaff() == null ? other.getStaff() == null : this.getStaff().equals(other.getStaff()))
            && (this.getStudents() == null ? other.getStudents() == null : this.getStudents().equals(other.getStudents()))
            && (this.getBoarder() == null ? other.getBoarder() == null : this.getBoarder().equals(other.getBoarder()))
            && (this.getForeignStudents() == null ? other.getForeignStudents() == null : this.getForeignStudents().equals(other.getForeignStudents()))
            && (this.getClassrooms() == null ? other.getClassrooms() == null : this.getClassrooms().equals(other.getClassrooms()))
            && (this.getShelterArea() == null ? other.getShelterArea() == null : this.getShelterArea().equals(other.getShelterArea()))
            && (this.getIsHaveHospital() == null ? other.getIsHaveHospital() == null : this.getIsHaveHospital().equals(other.getIsHaveHospital()))
            && (this.getDoctorNum() == null ? other.getDoctorNum() == null : this.getDoctorNum().equals(other.getDoctorNum()))
            && (this.getSecurityStaffNum() == null ? other.getSecurityStaffNum() == null : this.getSecurityStaffNum().equals(other.getSecurityStaffNum()))
            && (this.getEmergencyElectric() == null ? other.getEmergencyElectric() == null : this.getEmergencyElectric().equals(other.getEmergencyElectric()))
            && (this.getWaterMethod() == null ? other.getWaterMethod() == null : this.getWaterMethod().equals(other.getWaterMethod()))
            && (this.getHeatingMethod() == null ? other.getHeatingMethod() == null : this.getHeatingMethod().equals(other.getHeatingMethod()))
            && (this.getEmergencyConnectionMethod() == null ? other.getEmergencyConnectionMethod() == null : this.getEmergencyConnectionMethod().equals(other.getEmergencyConnectionMethod()))
            && (this.getIsDisasterType() == null ? other.getIsDisasterType() == null : this.getIsDisasterType().equals(other.getIsDisasterType()))
            && (this.getHaveEmergencyPlanType() == null ? other.getHaveEmergencyPlanType() == null : this.getHaveEmergencyPlanType().equals(other.getHaveEmergencyPlanType()))
            && (this.getInstitutionCode() == null ? other.getInstitutionCode() == null : this.getInstitutionCode().equals(other.getInstitutionCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getUnitHead() == null ? other.getUnitHead() == null : this.getUnitHead().equals(other.getUnitHead()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getFillName() == null ? other.getFillName() == null : this.getFillName().equals(other.getFillName()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getStatisticsHead() == null ? other.getStatisticsHead() == null : this.getStatisticsHead().equals(other.getStatisticsHead()))
            && (this.getReportTime() == null ? other.getReportTime() == null : this.getReportTime().equals(other.getReportTime()))
            && (this.getPhysicalKey() == null ? other.getPhysicalKey() == null : this.getPhysicalKey().equals(other.getPhysicalKey()))
            && (this.getProvinceCode() == null ? other.getProvinceCode() == null : this.getProvinceCode().equals(other.getProvinceCode()))
            && (this.getCityCode() == null ? other.getCityCode() == null : this.getCityCode().equals(other.getCityCode()))
            && (this.getCountyCode() == null ? other.getCountyCode() == null : this.getCountyCode().equals(other.getCountyCode()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getWriteTime() == null ? other.getWriteTime() == null : this.getWriteTime().equals(other.getWriteTime()))
            && (this.getGeom() == null ? other.getGeom() == null : this.getGeom().equals(other.getGeom()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getSchoolAddress() == null) ? 0 : getSchoolAddress().hashCode());
        result = prime * result + ((getSchoolCode() == null) ? 0 : getSchoolCode().hashCode());
        result = prime * result + ((getSchoolType() == null) ? 0 : getSchoolType().hashCode());
        result = prime * result + ((getSchoolCreater() == null) ? 0 : getSchoolCreater().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getConstructionArea() == null) ? 0 : getConstructionArea().hashCode());
        result = prime * result + ((getDevices() == null) ? 0 : getDevices().hashCode());
        result = prime * result + ((getIsImportant() == null) ? 0 : getIsImportant().hashCode());
        result = prime * result + ((getStaff() == null) ? 0 : getStaff().hashCode());
        result = prime * result + ((getStudents() == null) ? 0 : getStudents().hashCode());
        result = prime * result + ((getBoarder() == null) ? 0 : getBoarder().hashCode());
        result = prime * result + ((getForeignStudents() == null) ? 0 : getForeignStudents().hashCode());
        result = prime * result + ((getClassrooms() == null) ? 0 : getClassrooms().hashCode());
        result = prime * result + ((getShelterArea() == null) ? 0 : getShelterArea().hashCode());
        result = prime * result + ((getIsHaveHospital() == null) ? 0 : getIsHaveHospital().hashCode());
        result = prime * result + ((getDoctorNum() == null) ? 0 : getDoctorNum().hashCode());
        result = prime * result + ((getSecurityStaffNum() == null) ? 0 : getSecurityStaffNum().hashCode());
        result = prime * result + ((getEmergencyElectric() == null) ? 0 : getEmergencyElectric().hashCode());
        result = prime * result + ((getWaterMethod() == null) ? 0 : getWaterMethod().hashCode());
        result = prime * result + ((getHeatingMethod() == null) ? 0 : getHeatingMethod().hashCode());
        result = prime * result + ((getEmergencyConnectionMethod() == null) ? 0 : getEmergencyConnectionMethod().hashCode());
        result = prime * result + ((getIsDisasterType() == null) ? 0 : getIsDisasterType().hashCode());
        result = prime * result + ((getHaveEmergencyPlanType() == null) ? 0 : getHaveEmergencyPlanType().hashCode());
        result = prime * result + ((getInstitutionCode() == null) ? 0 : getInstitutionCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getUnitHead() == null) ? 0 : getUnitHead().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getFillName() == null) ? 0 : getFillName().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getStatisticsHead() == null) ? 0 : getStatisticsHead().hashCode());
        result = prime * result + ((getReportTime() == null) ? 0 : getReportTime().hashCode());
        result = prime * result + ((getPhysicalKey() == null) ? 0 : getPhysicalKey().hashCode());
        result = prime * result + ((getProvinceCode() == null) ? 0 : getProvinceCode().hashCode());
        result = prime * result + ((getCityCode() == null) ? 0 : getCityCode().hashCode());
        result = prime * result + ((getCountyCode() == null) ? 0 : getCountyCode().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getWriteTime() == null) ? 0 : getWriteTime().hashCode());
        result = prime * result + ((getGeom() == null) ? 0 : getGeom().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
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
        sb.append(", schoolName=").append(schoolName);
        sb.append(", schoolAddress=").append(schoolAddress);
        sb.append(", schoolCode=").append(schoolCode);
        sb.append(", schoolType=").append(schoolType);
        sb.append(", schoolCreater=").append(schoolCreater);
        sb.append(", area=").append(area);
        sb.append(", constructionArea=").append(constructionArea);
        sb.append(", devices=").append(devices);
        sb.append(", isImportant=").append(isImportant);
        sb.append(", staff=").append(staff);
        sb.append(", students=").append(students);
        sb.append(", boarder=").append(boarder);
        sb.append(", foreignStudents=").append(foreignStudents);
        sb.append(", classrooms=").append(classrooms);
        sb.append(", shelterArea=").append(shelterArea);
        sb.append(", isHaveHospital=").append(isHaveHospital);
        sb.append(", doctorNum=").append(doctorNum);
        sb.append(", securityStaffNum=").append(securityStaffNum);
        sb.append(", emergencyElectric=").append(emergencyElectric);
        sb.append(", waterMethod=").append(waterMethod);
        sb.append(", heatingMethod=").append(heatingMethod);
        sb.append(", emergencyConnectionMethod=").append(emergencyConnectionMethod);
        sb.append(", isDisasterType=").append(isDisasterType);
        sb.append(", haveEmergencyPlanType=").append(haveEmergencyPlanType);
        sb.append(", institutionCode=").append(institutionCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", telephone=").append(telephone);
        sb.append(", code=").append(code);
        sb.append(", unitHead=").append(unitHead);
        sb.append(", country=").append(country);
        sb.append(", fillName=").append(fillName);
        sb.append(", createName=").append(createName);
        sb.append(", province=").append(province);
        sb.append(", statisticsHead=").append(statisticsHead);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", physicalKey=").append(physicalKey);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", countyCode=").append(countyCode);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", writeTime=").append(writeTime);
        sb.append(", geom=").append(geom);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}