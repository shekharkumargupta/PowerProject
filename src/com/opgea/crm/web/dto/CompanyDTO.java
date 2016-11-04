/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Company;
import java.io.Serializable;

/**
 *
 * @author Ramesh
 */
public class CompanyDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1234245173385516952L;
    /**
     * Company Information.
     */
    private Long id;
    private String name;
    private String website;
    private Long countryId;
    private Long cityId;
    
    /**
     * Company's Contact Information
     */
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String contactNo;
    private String email;

    public CompanyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    /**
     * Sets values as initial.
     */
    public void reset() {
        this.id = null;
        this.firstName = null;
        this.middleInitial = null;
        this.lastName = null;
        this.email = null;
        this.name = null;
        this.website = null;
        this.contactNo = null;
        this.countryId = null;
        this.cityId = null;
    }
    
    /**
     * Copy values from Company to CompanyDTO.
     * @param company 
     */
    public void marshall(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.website = company.getWebsite();
        this.email = company.getEmail();
    }
    
    /**
     * Copy values from CompanyDTO to Company
     * @param company
     * @return company;
     */
    public Company unmarshall(Company company){
        company.setId(this.id);
        company.setName(this.name);
        company.setWebsite(this.website);
        company.setEmail(this.email);
        return company;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" + "companyName=" + name + ", email=" + email + ", website=" + website + ", contactNo=" + contactNo + '}';
    }
}
