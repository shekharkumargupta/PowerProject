/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Designation;
import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.domain.entities.Team;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public class EmployeeDTO {

    private Long id;
    private Long departmentId;
    private Long designationId;
    private Long companyId;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private Date dateOfBirth;
    private String department;
    private String designation;
    private String email;
    private String contactNo;
    private String empCode;
    private List<TeamDTO> teams = new ArrayList<TeamDTO>();
    private byte[] picture;
    private boolean existingMember;

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
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
        if (middleInitial == null) {
            return "";
        } else {
            return middleInitial;
        }
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDTO> teams) {
        this.teams = teams;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public boolean isExistingMember() {
        return existingMember;
    }

    public void setExistingMember(boolean existingMember) {
        this.existingMember = existingMember;
    }

    /**
     * Sets all the values as Initial;
     */
    public void reset() {
        this.id = null;
        this.firstName = null;
        this.middleInitial = null;
        this.lastName = null;
        this.contactNo = null;
        this.dateOfBirth = null;
        this.departmentId = null;
        this.department = null;
        this.departmentId = null;
        this.designation = null;
        this.companyId = null;
        this.email = null;
        this.empCode = null;
        this.companyId = null;
        this.teams = null;
        this.picture = null;
    }

    /**
     * Copy value from Employee to EmployeeDTO.
     * @param employee 
     */
    public void marshall(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.middleInitial = employee.getMiddleInitial();
        this.lastName = employee.getLastName();
        this.contactNo = employee.getContactNo();
        this.dateOfBirth = employee.getDateOfBirth();
        if (employee.getDesignation() != null) {
            this.designationId = employee.getDesignation().getId();
            this.designation = employee.getDesignation().getName();
            this.departmentId = employee.getDesignation().getDepartment().getId();
            this.department = employee.getDesignation().getDepartment().getName();
        }
        this.email = employee.getEmail();
        this.empCode = employee.getEmpCode();
        if (employee.getCompany() != null) {
            this.companyId = employee.getCompany().getId();
        }
        if (employee.getCompany() != null) {
            this.companyId = employee.getCompany().getId();
        }
        if (employee.getTeams() != null) {
            List<Team> teamList = employee.getTeams();
            for (Team team : teamList) {
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setCompanyId(team.getCompany().getId());
                teamDTO.setCompanyName(team.getCompany().getName());
                teamDTO.setId(team.getId());
                if (team.getLeader() != null) {
                    teamDTO.setTeamLeaderName(team.getLeader().getFirstName()
                            + " " + team.getLeader().getMiddleInitial()
                            + " " + team.getLeader().getLastName().trim());
                }
                teams.add(teamDTO);
            }
        }
        if(employee.getEmployeeDetails() != null &&
                employee.getEmployeeDetails().getPicture() != null){
            this.picture = employee.getEmployeeDetails().getPicture();
        }
    }

    /**
     * Copy value from EmployeeDTO to Employee.
     * @param employee
     * @return 
     */
    public Employee unmarshall(Employee employee) {
        Designation desig = employee.getDesignation();
        Company company = employee.getCompany();
        if (desig != null && designationId != null) {
            desig.setId(this.designationId);
            desig.setName(this.designation);
        }
        if (company != null && this.companyId != null) {
            employee.getCompany().setId(this.companyId);
        }
        employee.setId(this.getId());
        employee.setFirstName(this.getFirstName());
        employee.setMiddleInitial(this.getMiddleInitial());
        employee.setLastName(this.getLastName());
        employee.setContactNo(this.getContactNo());
        employee.setDateOfBirth(this.getDateOfBirth());
        employee.setDesignation(desig);
        employee.setEmail(this.email);
        employee.setEmpCode(this.empCode);
        if(this.getPicture() != null && this.picture.length > 0){
            employee.getEmployeeDetails().setPicture(this.picture);
        }
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" + "id=" + id + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", designationId=" + designationId + ", designation=" + designation + ", email=" + email + ", contactNo=" + contactNo + ", empCode=" + empCode + ", companyId=" + companyId + '}';
    }
}
