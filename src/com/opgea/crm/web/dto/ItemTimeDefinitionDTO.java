/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.ItemTimeDefinition;
import com.opgea.crm.domain.entities.qualifier.DurationType;

/**
 *
 * @author Ramesh
 */
public class ItemTimeDefinitionDTO {

    private Long id;
    private Long itemId;
    private String itemName;
    private Long departmentId;
    private String departmentName;
    private Long jobId;
    private String jobName;
    private Long duration;
    private Integer durationType;
    private String durationTypeName;

    public ItemTimeDefinitionDTO() {
    }

    public ItemTimeDefinitionDTO(Long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public String getDurationTypeName() {
        return durationTypeName;
    }

    public void setDurationTypeName(String durationTypeName) {
        this.durationTypeName = durationTypeName;
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

    /**
     * Setting values to as Initial.
     */
    public void reset() {
        this.id = null;
        this.itemId = null;
        this.jobId = null;
        this.departmentId = null;
        this.itemName = null;
        this.departmentName = null;
        this.jobName = null;
        this.duration = null;
        this.durationType = null;
        this.durationTypeName = null;
    }

    /**
     * Copy values from ItemTimeDefinition to ItemTimeDefinitionDTO.
     * @param time 
     */
    public void marshall(ItemTimeDefinition time) {
        this.id = time.getId();
        if (time.getItem() != null) {
            this.itemId = time.getItem().getId();
            this.itemName = time.getItem().getName();
        }
        if(time.getJob() != null){
            this.jobId = time.getJob().getId();
            this.jobName = time.getJob().getName();
        }
        if(time.getJob().getDepartment() != null){
            this.departmentId = time.getJob().getDepartment().getId();
            this.departmentName = time.getJob().getDepartment().getName();
        }
        if (time.getDurationValue() != null && time.getDurationType() != null) {
            this.duration = time.getDurationValue();
            this.durationType = time.getDurationType().ordinal();
            this.durationTypeName = time.getDurationType().name();
        }
    }
    
    /**
     * Copy values from ItemTimeDefinitionDTO to ItemTimeDefinition.
     * @param time
     * @return 
     */
    public ItemTimeDefinition unmarshall(ItemTimeDefinition time){
        time.setId(this.id);
        if(time.getItem() != null && this.itemId != null){
            time.getItem().setId(this.itemId);
            time.getItem().setName(this.itemName);
        }
        if(time.getJob() != null && this.jobId != null){
            time.getJob().setId(this.jobId);
            time.getJob().setName(this.jobName);
        }
        if(time.getJob() != null && time.getJob().getDepartment() != null){
            time.getJob().getDepartment().setId(this.departmentId);
            time.getJob().getDepartment().setName(this.departmentName);
        }
        time.setDurationValue(this.duration);
        time.setDurationType(DurationType.values()[this.durationType]);
        
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemTimeDefinitionDTO other = (ItemTimeDefinitionDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ItemTimeDefinitionDTO{" + "id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", jobId=" + jobId + ", jobName=" + jobName + ", duration=" + duration + ", durationType=" + durationType + '}';
    }
}
