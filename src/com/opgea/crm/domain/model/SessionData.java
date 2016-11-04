package com.opgea.crm.domain.model;

import com.opgea.crm.web.dto.EmployeeDTO;

public class SessionData {

    private EmployeeDTO employeeDTO; 
    
    private String loginId;
    private Long employeeId;
    private Long companyId;
    private Integer userTypeId;
    
    private String userTypeName;
    private String employeeName;
    private String companyName;
    
    private String themeName;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "SessionData{" + "employeeDTO=" + employeeDTO + ", loginId=" + loginId + ", employeeId=" + employeeId + ", companyId=" + companyId + ", userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + ", employeeName=" + employeeName + ", companyName=" + companyName + ", themeName=" + themeName + '}';
    }


    
}
