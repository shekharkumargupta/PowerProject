/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.domain.entities.qualifier.DurationType;

/**
 *
 * @author Ramesh
 */
public class ItemEstimationDTO {

    private Long id;
    private Long defaultTimeDefinitionId;
    private Long itemId;
    private String itemName;
    private Long jobId;
    private String jobName;
    private Long departmentId;
    private String departmentName;
    private Integer durationTypeId;
    private String durationTypeName;
    private Long durationValue;
    private Integer quantity;
    private Long totalTime;
    private Long totalTimeInHrs;
    private Long estimationId;
    private String estimationName;
    private Long itemTotalTime;
    private Long grandTotalTime;

    public Long getDefaultTimeDefinitionId() {
        return defaultTimeDefinitionId;
    }

    public void setDefaultTimeDefinitionId(Long defaultTimeDefinitionId) {
        this.defaultTimeDefinitionId = defaultTimeDefinitionId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDurationTypeId() {
        return durationTypeId;
    }

    public void setDurationTypeId(Integer durationTypeId) {
        this.durationTypeId = durationTypeId;
    }

    public Long getDurationValue() {
        return durationValue;
    }

    public void setDurationValue(Long durationValue) {
        this.durationValue = durationValue;
    }

    public Long getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(Long estimationId) {
        this.estimationId = estimationId;
    }

    public String getEstimationName() {
        return estimationName;
    }

    public void setEstimationName(String estimationName) {
        this.estimationName = estimationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        if(this.quantity == null){
            this.quantity = 0;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotalTime() {
        if (this.durationValue != null && this.quantity != null) {
            this.totalTime = this.durationValue * this.quantity;
        }
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getTotalTimeInHrs() {
        return totalTimeInHrs;
    }

    public void setTotalTimeInHrs(Long totalTimeInHrs) {
        this.totalTimeInHrs = totalTimeInHrs;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getItemTotalTime() {
        return itemTotalTime;
    }

    public void setItemTotalTime(Long itemTotalTime) {
        this.itemTotalTime = itemTotalTime;
    }

    public Long getGrandTotalTime() {
        return grandTotalTime;
    }

    public void setGrandTotalTime(Long grandTotalTime) {
        this.grandTotalTime = grandTotalTime;
    }

    public String getDurationTypeName() {
        return durationTypeName;
    }

    public void setDurationTypeName(String durationTypeName) {
        this.durationTypeName = durationTypeName;
    }

    /**
     * To the value as Initial.
     */
    public void reset() {
        this.id = null;
        this.departmentId = null;
        this.departmentName = null;
        this.durationTypeId = null;
        this.durationValue = null;
        this.estimationId = null;
        this.estimationName = null;
        this.itemId = null;
        this.itemName = null;
        this.jobId = null;
        this.jobName = null;
        this.quantity = null;
        this.totalTime = null;
        this.totalTimeInHrs = null;
        this.itemTotalTime = null;
        this.durationTypeName = null;
    }

    /**
     * Copy values from ItemEstimation to ItemEstimationDTO.
     * @param itemEstimation 
     */
    public void marshall(ItemEstimation itemEstimation) {
        if (itemEstimation != null) {
            this.id = itemEstimation.getId();
            this.quantity = itemEstimation.getQuantity();
            this.totalTime = itemEstimation.getTotalTime();
            this.totalTimeInHrs = itemEstimation.getTotalTimeInHrs();
        }
        if (itemEstimation != null && itemEstimation.getDurationType() != null) {
            this.durationTypeId = itemEstimation.getDurationType().ordinal();
            this.durationValue = itemEstimation.getDurationValue();
            this.durationTypeName = itemEstimation.getDurationType().name();
        }
        if (itemEstimation != null && itemEstimation.getEstimation() != null) {
            this.estimationId = itemEstimation.getEstimation().getId();
            this.estimationName = itemEstimation.getEstimation().getName();
        }
        if (itemEstimation != null && itemEstimation.getItem() != null) {
            this.itemId = itemEstimation.getItem().getId();
            this.itemName = itemEstimation.getItem().getName();
        }
        if (itemEstimation != null && itemEstimation.getJob() != null) {
            this.jobId = itemEstimation.getJob().getId();
            this.jobName = itemEstimation.getJob().getName();
        }
    }

    /**
     * Copy values from ItemEstimationDTO to ItemEstimation.
     * @param itemEstimation
     * @return 
     */
    public ItemEstimation unmarshall(ItemEstimation itemEstimation) {
        if (this.id != null) {
            itemEstimation.setId(this.id);
        }
        if (itemEstimation != null) {
            itemEstimation.setDurationType(DurationType.values()[this.durationTypeId]);
            itemEstimation.setDurationValue(this.durationValue);
        }
        itemEstimation.setQuantity(this.quantity);
        itemEstimation.setTotalTime(this.totalTime);
        itemEstimation.setTotalTimeInHrs(this.totalTimeInHrs);
        if (this.jobId != null && itemEstimation.getJob() != null) {
            itemEstimation.getJob().setId(this.jobId);
        }
        if (this.jobName != null && itemEstimation.getJob() != null) {
            itemEstimation.getJob().setName(this.jobName);
        }
        if (this.itemId != null && itemEstimation.getItem() != null) {
            itemEstimation.getItem().setId(this.itemId);
        }
        if (this.itemName != null && itemEstimation.getItem() != null) {
            itemEstimation.getItem().setName(this.itemName);
        }
        if (this.estimationId != null && itemEstimation.getEstimation() != null) {
            itemEstimation.getEstimation().setId(this.estimationId);
        }
        if (this.estimationName != null && itemEstimation.getEstimation() != null) {
            itemEstimation.getEstimation().setName(this.estimationName);
        }

        return itemEstimation;
    }
    
    /**
     * Creates ItemEstimationDTO from the value of ItemTimeDefinitionDTO value.
     */
    public ItemEstimationDTO createEstimationFromDefaultDefinition(ItemTimeDefinitionDTO itemTimeDefinitionDTO){
        this.setId(null);
        this.setItemId(itemTimeDefinitionDTO.getItemId());
        this.setItemName(itemTimeDefinitionDTO.getItemName());
        this.setJobId(itemTimeDefinitionDTO.getJobId());
        this.setJobName(itemTimeDefinitionDTO.getJobName());
        this.setDepartmentId(itemTimeDefinitionDTO.getDepartmentId());
        this.setDurationTypeId(itemTimeDefinitionDTO.getDurationType());
        this.setDurationValue(itemTimeDefinitionDTO.getDuration());
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemEstimationDTO other = (ItemEstimationDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ItemEstimationDTO{" + "id=" + id + ", defaultTimeDefinitionId=" + defaultTimeDefinitionId + ", itemId=" + itemId + ", itemName=" + itemName + ", jobId=" + jobId + ", jobName=" + jobName + ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", durationTypeId=" + durationTypeId + ", durationValue=" + durationValue + ", quantity=" + quantity + ", totalTime=" + totalTime + ", totalTimeInHrs=" + totalTimeInHrs + ", estimationId=" + estimationId + ", estimationName=" + estimationName + '}';
    }
}
