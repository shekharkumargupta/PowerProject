/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Designation;
import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.domain.entities.EmployeeDetails;
import com.opgea.crm.domain.entities.Login;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.DesignationFacadeLocal;
import com.opgea.crm.ejb.local.EmployeeDetailsFacadeLocal;
import com.opgea.crm.ejb.local.EmployeeFacadeLocal;
import com.opgea.crm.ejb.local.LoginFacadeLocal;
import com.opgea.crm.ejb.local.TeamFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.web.dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class EmployeeServiceImpl implements EmployeeService {

    @EJB
    private EmployeeFacadeLocal employeeFacade;
    @EJB
    private LoginFacadeLocal loginFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;
    @EJB
    private DesignationFacadeLocal designationFacade;
    @EJB
    private EmployeeDetailsFacadeLocal employeeDetailsFacade;
    @EJB
    private TeamFacadeLocal teamFacade;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) throws DBOperationException {
        System.out.println("EmployeeDTO : " + employeeDTO);

        Employee employee = new Employee();
        employeeDTO.unmarshall(employee);
        Login login = new Login();
        login.setLoginId(employeeDTO.getEmail());
        login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
        login.setIsActive(true);
        login.setEmployee(employee);
        loginFacade.create(login);
        Company company = companyFacade.find(employeeDTO.getCompanyId());
        employee.setCompany(company);
        Designation designation = designationFacade.find(employeeDTO.getDesignationId());
        employee.setDesignation(designation);
        employeeFacade.edit(employee);
        employeeDTO.setId(employee.getId());

        return employeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) throws DBOperationException {
        if (employeeDTO.getId() != null && employeeDTO.getId() > 0) {
            Employee employee = employeeFacade.find(employeeDTO.getId());
            employeeDTO.unmarshall(employee);
            Designation designation = designationFacade.find(employeeDTO.getDesignationId());
            employee.setDesignation(designation);
            employeeFacade.edit(employee);
        } else {
            try {
                this.create(employeeDTO);
            } catch (EJBException ex) {
                throw new DBOperationException(ex.getMessage());
            }
        }
        return employeeDTO;
    }

    @Override
    public EmployeeDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmployeeDTO find(Long id) {
        Employee employee = employeeFacade.find(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.marshall(employee);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeFacade.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.marshall(employee);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Override
    public List<EmployeeDTO> findAllByCompanyId(Long companyId) {
        List<Employee> employees = employeeFacade.findAllByCompanyId(companyId);
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.marshall(employee);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Override
    public List<EmployeeDTO> findAllWithTeamId(Long companyId, Long teamId) {
        List<Employee> employees = employeeFacade.findAllByCompanyId(companyId);
        List<Employee> teamMembers = null;
        if (teamId != null) {
            teamMembers = teamFacade.find(teamId).getEmployees();
        }
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            if (teamId != null && teamMembers != null) {
                employeeDTO.setExistingMember(teamMembers.contains(employee));
            }
            employeeDTO.marshall(employee);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO uploadImage(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() != null && employeeDTO.getId() > 0) {
            Employee employee = employeeFacade.find(employeeDTO.getId());
            if (employee.getEmployeeDetails() != null) {
                EmployeeDetails details = employee.getEmployeeDetails();
                details.setPicture(employeeDTO.getPicture());
                employee.setEmployeeDetails(details);
                employeeDetailsFacade.edit(details);
                employeeFacade.edit(employee);
            } else {
                EmployeeDetails details = new EmployeeDetails();
                details.setPicture(employeeDTO.getPicture());
                employee.setEmployeeDetails(details);
                employeeDetailsFacade.create(details);
                employeeFacade.edit(employee);
            }
        }
        return employeeDTO;
    }
}
