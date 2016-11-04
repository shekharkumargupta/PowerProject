/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Client;

/**
 *
 * @author Ramesh
 */
public class ClientDTO {

    private Long clientId;
    private String clientName;
    private Long categoryId;
    private String categoryName;
    private String website;
    private String street1;
    private String street2;
    private Long countryId;
    private String countryName;
    private Long cityId;
    private String cityName;
    private String zipCode;
    private Long companyId;

    public ClientDTO(Long clientId, String clientName, Long companyId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.companyId = companyId;
    }

    public ClientDTO(){
        
    }
    
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Set values as initial.
     */
    public void reset() {
    }


    /**
     *  Copy values from Client to ClientDTO.
     * @param client 
     */
    public void marshall(Client client) {
        this.setClientId(client.getId());
        this.setClientName(client.getClientName());
        this.setWebsite(client.getWebsite());
        if (client.getCity() != null) {
            this.setCityId(client.getCity().getId());
            this.setCityName(client.getCity().getName());
            this.setCountryId(client.getCity().getCountry().getId());
            this.setCountryName(client.getCity().getCountry().getName());
        }
        if(client.getCompany() != null){
            this.setCompanyId(client.getCompany().getId());
        }
        this.setStreet1(client.getStreet1());
        this.setStreet2(client.getStreet2());
        this.setZipCode(client.getZipCode());
    }
    
    /**
     * Copy values from ClientDTO to Client.
     * @param client 
     */
    public Client unmarshall(Client client){
        client.setId(this.clientId);
        client.setClientName(this.getClientName());
        client.setWebsite(this.getWebsite());
        client.setStreet1(this.getStreet1());
        client.setStreet2(this.getStreet2());
        client.setZipCode(this.getZipCode());
        return client;
    }

    @Override
    public String toString() {
        return "ClientDTO{" + "clientId=" + clientId + ", clientName=" + clientName + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", street1=" + street1 + ", street2=" + street2 + ", countryId=" + countryId + ", countryName=" + countryName + ", cityId=" + cityId + ", cityName=" + cityName + ", zipCode=" + zipCode + '}';
    }
}
