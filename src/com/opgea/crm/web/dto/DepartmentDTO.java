/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Department;

/**
 *
 * @author Ramesh
 */
public class DepartmentDTO {

    private Long id;
    private String name;
    private Long companyId;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name, Long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
     * Set the values as Initial state.
     */
    public void reset() {
        this.id = null;
        this.name = null;
        this.companyId = null;
    }

    /**
     * Copy value from Department to DepartmentDTO.
     * @param department 
     */
    public void marshall(Department department) {
        if (department.getCompany() != null) {
            this.setCompanyId(department.getCompany().getId());
        }
        this.setId(department.getId());
        this.setName(department.getName());
    }

    /**
     * Copy value from DepartmentDTO to Department.
     * @param department
     * @return 
     */
    public Department unmarshall(Department department) {
        if (department.getCompany() != null && companyId != null) {
            department.getCompany().setId(this.companyId);
        }
        department.setId(this.id);
        department.setName(this.name);
        return department;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DepartmentDTO other = (DepartmentDTO) obj;
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
        return "DepartmentDTO{" + "id=" + id + ", name=" + name + ", companyId=" + companyId + '}';
    }
}
