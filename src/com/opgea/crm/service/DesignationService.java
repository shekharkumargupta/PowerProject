/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.DesignationDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DesignationService {

    public DesignationDTO create(DesignationDTO designationDTO) throws DBOperationException;

    public DesignationDTO update(DesignationDTO designationDTO) throws DBOperationException;

    public DesignationDTO remove(Long id);

    public DesignationDTO find(Long id);

    public List<DesignationDTO> findAll();

    public List<DesignationDTO> findAllByDepartmentId(Long departmentId);

    public List<DesignationDTO> findAllByCompanyId(Long companyId);
}
