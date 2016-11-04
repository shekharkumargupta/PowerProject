/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Item;

/**
 *
 * @author Ramesh
 */
public class ItemDTO {
    
    private Long id;
    private String name;
    private Long companyId;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, Long companyId) {
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
     * Setting values to as the Initial.
     */
    public void reset() {
        this.id = null;
        this.name = null;
        this.companyId = null;
    }

    /**
     * Copies value from Item to ItemDTO.
     * @param job 
     */
    public void marshall(Item item) {
        if (item.getCompany() != null) {
            this.companyId = item.getCompany().getId();
        }
        this.id = item.getId();
        this.name = item.getName();
    }

    /**
     * Copies value from ItemDTO to Item.
     * @param job 
     */
    public Item unmarshall(Item item) {
        Company company = item.getCompany();
        if (company!= null && this.companyId != null) {
            company.setId(this.companyId);
            item.setCompany(company);
        }
        item.setId(this.id);
        item.setName(this.name);
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) obj;
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
        return "ItemDTO{" + "id=" + id + ", name=" + name + ", companyId=" + companyId + '}';
    }
}
