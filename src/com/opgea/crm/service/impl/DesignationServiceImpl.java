/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.Designation;
import com.opgea.crm.ejb.local.DepartmentFacadeLocal;
import com.opgea.crm.ejb.local.DesignationFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.DesignationService;
import com.opgea.crm.web.dto.DesignationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class DesignationServiceImpl implements DesignationService {

    @EJB
    private DesignationFacadeLocal designationFacade;
    @EJB
    private DepartmentFacadeLocal departmentFacade;

    @Override
    public DesignationDTO create(DesignationDTO designationDTO) throws DBOperationException {
        Department department = departmentFacade.find(designationDTO.getDepartmentId());
        Designation designation = new Designation();
        designationDTO.unmarshall(designation);
        designation.setDepartment(department);
        try {
            designationFacade.create(designation);
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getMessage());
        }
        designationDTO.setId(designation.getId());
        return designationDTO;
    }

    @Override
    public DesignationDTO update(DesignationDTO designationDTO) throws DBOperationException {
        if (designationDTO.getId() != null && designationDTO.getId() > 0) {
            Department department = departmentFacade.find(designationDTO.getDepartmentId());
            Designation designation = designationFacade.find(designationDTO.getId());
            designationDTO.unmarshall(designation);
            designation.setDepartment(department);
            designationFacade.edit(designation);
            designationDTO.setId(designation.getId());
        } else {
            this.create(designationDTO);
        }
        return designationDTO;
    }

    @Override
    public DesignationDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DesignationDTO find(Long id) {
        Designation designation = designationFacade.find(id);
        DesignationDTO dto = new DesignationDTO();
        dto.marshall(designation);
        return dto;
    }

    @Override
    public List<DesignationDTO> findAll() {
        List<Designation> designations = designationFacade.findAll();
        List<DesignationDTO> designationDTOs = new ArrayList<DesignationDTO>();
        for (Designation designation : designations) {
            DesignationDTO dto = new DesignationDTO();
            dto.marshall(designation);
            designationDTOs.add(dto);
        }
        return designationDTOs;
    }

    @Override
    public List<DesignationDTO> findAllByDepartmentId(Long departmentId) {
        List<Designation> designations = designationFacade.findAllByDepartmentId(departmentId);
        List<DesignationDTO> designationDTOs = new ArrayList<DesignationDTO>();
        for (Designation designation : designations) {
            DesignationDTO dto = new DesignationDTO();
            dto.marshall(designation);
            designationDTOs.add(dto);
        }
        return designationDTOs;
    }

    @Override
    public List<DesignationDTO> findAllByCompanyId(Long companyId) {
        List<Designation> designations = designationFacade.findAllByCompanyId(companyId);
        List<DesignationDTO> designationDTOs = new ArrayList<DesignationDTO>();
        for (Designation designation : designations) {
            DesignationDTO dto = new DesignationDTO();
            dto.marshall(designation);
            designationDTOs.add(dto);
        }
        return designationDTOs;
    }
}
