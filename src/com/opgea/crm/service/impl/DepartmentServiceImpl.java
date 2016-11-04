/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.DepartmentFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.DepartmentService;
import com.opgea.crm.web.dto.DepartmentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class DepartmentServiceImpl implements DepartmentService {

    @EJB
    private DepartmentFacadeLocal departmentFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) throws DBOperationException {
        Department department = new Department();
        Company company = companyFacade.find(departmentDTO.getCompanyId());
        departmentDTO.unmarshall(department);
        department.setCompany(company);
        try{
        departmentFacade.create(department);
        }catch(EJBException ex){
            throw new DBOperationException(ex.getMessage());
        }
        departmentDTO.setId(department.getId());
        return departmentDTO;
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) throws DBOperationException {
        if (departmentDTO.getId() != null && departmentDTO.getId() > 0) {
            Department department = departmentFacade.find(departmentDTO.getId());
            departmentDTO.unmarshall(department);
            departmentFacade.edit(department);
        } else {
            this.create(departmentDTO);
        }
        return departmentDTO;
    }

    @Override
    public DepartmentDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DepartmentDTO find(Long id) {
        Department department = departmentFacade.find(id);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.marshall(department);
        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentFacade.findAll();
        List<DepartmentDTO> dtoList = new ArrayList<DepartmentDTO>();
        for (Department department : departments) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.marshall(department);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<DepartmentDTO> findAllByCompanyId(Long companyId) {
        List<Department> departments = departmentFacade.findAllByCompanyId(companyId);
        List<DepartmentDTO> dtoList = new ArrayList<DepartmentDTO>();
        for (Department department : departments) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.marshall(department);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
