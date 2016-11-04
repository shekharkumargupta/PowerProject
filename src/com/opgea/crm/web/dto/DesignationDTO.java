/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.Designation;

/**
 *
 * @author Ramesh
 */
public class DesignationDTO {

    private Long id;
    private String name;
    private Long departmentId;

    public DesignationDTO() {
    }

    public DesignationDTO(Long id, String name, Long departmentId) {
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
     * Copies value from Designation to DesignationDTO.
     * @param designation 
     */
    public void marshall(Designation designation) {
        if (designation.getDepartment() != null) {
            this.departmentId = designation.getDepartment().getId();
        }
        this.id = designation.getId();
        this.name = designation.getName();
    }

    /**
     * Copies value from DesignationDTO to Designation.
     * @param designation 
     */
    public Designation unmarshall(Designation designation) {
        Department department = designation.getDepartment();
        if (department!= null && this.departmentId != null) {
            department.setId(this.departmentId);
            designation.setDepartment(department);
        }
        designation.setId(this.id);
        designation.setName(this.name);
        return designation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DesignationDTO other = (DesignationDTO) obj;
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
        return "DesignationDTO{" + "id=" + id + ", name=" + name + ", departmentId=" + departmentId + '}';
    }
}
