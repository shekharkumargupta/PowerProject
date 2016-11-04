/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.domain.entities.Login;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.LoginFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CompanyService;
import com.opgea.crm.web.dto.CompanyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class CompanyServiceImpl implements CompanyService {

    @EJB
    private CompanyFacadeLocal companyFacade;
    @EJB
    private LoginFacadeLocal loginFacade;

    @Override
    public CompanyDTO create(CompanyDTO companyDTO) throws DBOperationException {
        Company company = new Company();
        companyDTO.unmarshall(company);
        //companyFacade.create(company);

        /**
         * Creating First/Default Employee of the Company.
         */
        Employee employee = new Employee();
        employee.setEmail(companyDTO.getEmail());
        employee.setCompany(company);

        /**
         * Creating Login Id for the First/Default Employee.
         */
        Login login = new Login();
        login.setLoginId(companyDTO.getEmail());
        login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
        login.setIsActive(true);
        login.setEmployee(employee);
        try {
            loginFacade.create(login);
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getLocalizedMessage());
        }

        companyDTO.setId(company.getId());
        return companyDTO;
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) throws DBOperationException{
        if (companyDTO.getId() != null && companyDTO.getId() > 0) {
            Company company = companyFacade.find(companyDTO.getId());
            companyDTO.unmarshall(company);
            companyFacade.edit(company);
        }else{
            this.create(companyDTO);
        }
        return companyDTO;
    }

    @Override
    public CompanyDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompanyDTO find(Long id) {
        Company company = companyFacade.find(id);
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.marshall(company);
        return companyDTO;
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<Company> companies = companyFacade.findAll();
        List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
        for (Company company : companies) {
            CompanyDTO dto = new CompanyDTO();
            dto.marshall(company);
            companyDTOs.add(dto);
        }
        return companyDTOs;
    }
}
