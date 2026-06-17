package com.gis.xian.entity;

import java.util.Date;

/**
 * 推理结果表
 * @TableName xian_inference_result
 */
public class XianInferenceResult {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 发生时间
     */
    private Date occurredTime;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 推理结果（JSONB）
     */
    private Object result;

    /**
     * 条件（JSONB）
     */
    private Object condition;

    /**
     * 文件路径（JSONB）
     */
    private Object filePath;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除（0: 未删除, 1: 已删除）
     */
    private Integer isDelete;

    // 构造方法
    public XianInferenceResult() {
    }

    public XianInferenceResult(Long id, String name, String eventType, Date occurredTime, 
                               String operationType, Object result, Object condition, 
                               Object filePath, Date createTime, Integer isDelete) {
        this.id = id;
        this.name = name;
        this.eventType = eventType;
        this.occurredTime = occurredTime;
        this.operationType = operationType;
        this.result = result;
        this.condition = condition;
        this.filePath = filePath;
        this.createTime = createTime;
        this.isDelete = isDelete;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getOccurredTime() {
        return occurredTime;
    }

    public void setOccurredTime(Date occurredTime) {
        this.occurredTime = occurredTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }

    public Object getFilePath() {
        return filePath;
    }

    public void setFilePath(Object filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

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
        XianInferenceResult other = (XianInferenceResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
                && (this.getOccurredTime() == null ? other.getOccurredTime() == null : this.getOccurredTime().equals(other.getOccurredTime()))
                && (this.getOperationType() == null ? other.getOperationType() == null : this.getOperationType().equals(other.getOperationType()))
                && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
                && (this.getCondition() == null ? other.getCondition() == null : this.getCondition().equals(other.getCondition()))
                && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getOccurredTime() == null) ? 0 : getOccurredTime().hashCode());
        result = prime * result + ((getOperationType() == null) ? 0 : getOperationType().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getCondition() == null) ? 0 : getCondition().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", eventType=").append(eventType);
        sb.append(", occurredTime=").append(occurredTime);
        sb.append(", operationType=").append(operationType);
        sb.append(", result=").append(result);
        sb.append(", condition=").append(condition);
        sb.append(", filePath=").append(filePath);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}
