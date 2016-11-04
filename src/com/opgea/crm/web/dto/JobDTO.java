/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.Job;

/**
 *
 * @author Ramesh
 */
public class JobDTO {
    
    private Long id;
    private String name;
    private Long departmentId;

    public JobDTO() {
    }

    public JobDTO(Long id, String name, Long departmentId) {
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
     * Copies value from Job to JobDTO.
     * @param job 
     */
    public void marshall(Job job) {
        if (job.getDepartment() != null) {
            this.departmentId = job.getDepartment().getId();
        }
        this.id = job.getId();
        this.name = job.getName();
    }

    /**
     * Copies value from JobDTO to Job.
     * @param job 
     */
    public Job unmarshall(Job job) {
        Department department = job.getDepartment();
        if (department!= null && this.departmentId != null) {
            department.setId(this.departmentId);
            job.setDepartment(department);
        }
        job.setId(this.id);
        job.setName(this.name);
        return job;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JobDTO other = (JobDTO) obj;
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
