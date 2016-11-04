/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.JobStatus;

/**
 *
 * @author Ramesh
 */
public class JobStatusDTO {
    
    private Long id;
    private String name;
    private Long departmentId;

    public JobStatusDTO() {
    }

    public JobStatusDTO(Long id, String name, Long departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }
    
    
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

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

    /**
     * Setting values to as the Initial.
     */
    public void reset() {
        this.id = null;
        this.name = null;
        this.departmentId = null;
    }

    /**
     * Copies value from JobStatus to JobStatusDTO.
     * @param jobStatus 
     */
    public void marshall(JobStatus jobStatus) {
        if (jobStatus.getDepartment() != null) {
            this.departmentId = jobStatus.getDepartment().getId();
        }
        this.id = jobStatus.getId();
        this.name = jobStatus.getName();
    }

    /**
     * Copies value from JobDTO to Job.
     * @param jobStatus 
     */
    public JobStatus unmarshall(JobStatus jobStatus) {
        Department department = jobStatus.getDepartment();
        if (department!= null && this.departmentId != null) {
            department.setId(this.departmentId);
            jobStatus.setDepartment(department);
        }
        jobStatus.setId(this.id);
        jobStatus.setName(this.name);
        return jobStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JobStatusDTO other = (JobStatusDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "JobDTO{" + "id=" + id + ", name=" + name + ", departmentId=" + departmentId + '}';
    }
}
