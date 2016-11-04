/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.common.util.DateUtil;
import com.opgea.crm.domain.entities.ClientContact;
import com.opgea.crm.domain.entities.qualifier.ContactType;

/**
 *
 * @author Ramesh
 */
public class ClientContactDTO {
    
    private Long clientId;
    private Long contactId;
    private Integer contactType;
    private String contactTypeName;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String designation;
    private String email;
    private String phone1;
    private String phone2;
    private String dateOfBirth;
    private String dateOfAnniversary;
    
    
    public ClientContactDTO() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getDateOfAnniversary() {
        return dateOfAnniversary;
    }

    public void setDateOfAnniversary(String dateOfAnniversary) {
        this.dateOfAnniversary = dateOfAnniversary;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * Set values as initials.
     */
    public void reset(){
        
    }
    
    /**
     * Copy values from Client to ClientDTO.
     * @param clientContact 
     */
    public void marshall(ClientContact clientContact){
        this.setContactId(clientContact.getId());
        this.setFirstName(clientContact.getFirstName());
        this.setMiddleInitial(clientContact.getMiddleInitial());
        this.setLastName(clientContact.getLastName());
        if(clientContact.getContactType() != null){
            this.setContactType(clientContact.getContactType().ordinal());
            this.setContactTypeName(clientContact.getContactType().name());
        }
        this.setDesignation(clientContact.getDesignation());
        this.setEmail(clientContact.getEmail());
        this.setPhone1(clientContact.getPhone1());
        this.setPhone2(clientContact.getPhone2());
        this.setClientId(clientContact.getClient().getId());
        this.setContactId(clientContact.getId());
        if(clientContact.getDateOfBirth() != null){
            this.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(clientContact.getDateOfBirth(), "-"));
        }
        if(clientContact.getDateOfAnniversary() != null){
            this.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(clientContact.getDateOfAnniversary(), "-"));
        }
    }
    
    /**
     * Copy values from ClientDTO to Client.
     * @param clientContact 
     */
    public ClientContact unmarshall(ClientContact clientContact){
        clientContact.setId(clientContact.getId());
        clientContact.setFirstName(this.getFirstName());
        clientContact.setMiddleInitial(this.getMiddleInitial());
        clientContact.setLastName(this.getLastName());
        clientContact.setContactType(ContactType.values()[this.getContactType()]);
        clientContact.setDesignation(this.getDesignation());
        clientContact.setEmail(this.getEmail());
        clientContact.setPhone1(this.getPhone1());
        clientContact.setPhone2(this.getPhone2());
        if(this.getDateOfBirth() != null){
            clientContact.setDateOfBirth(DateUtil.getDateFromYYYYMMDD(this.getDateOfBirth(), "-"));
        }
        if(this.getDateOfAnniversary() != null){
            clientContact.setDateOfAnniversary(DateUtil.getDateFromYYYYMMDD(this.getDateOfAnniversary(), "-"));
        }
        return clientContact;
    }

    @Override
    public String toString() {
        return "ClientContactDTO{" + "contactId=" + contactId + ", designation=" + designation + ", email=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + '}';
    }
    
    
    
}
