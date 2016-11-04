/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.DepartmentDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DepartmentService {

    public DepartmentDTO create(DepartmentDTO departmentDTO) throws DBOperationException;

    public DepartmentDTO update(DepartmentDTO departmentDTO) throws DBOperationException;

    public DepartmentDTO remove(Long id);

    public DepartmentDTO find(Long id);

    public List<DepartmentDTO> findAll();

    public List<DepartmentDTO> findAllByCompanyId(Long companyId);
}
