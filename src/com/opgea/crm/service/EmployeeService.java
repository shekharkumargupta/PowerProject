/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface EmployeeService {

    public EmployeeDTO create(EmployeeDTO employeeDTO) throws DBOperationException;

    public EmployeeDTO update(EmployeeDTO employeeDTO) throws DBOperationException;

    public EmployeeDTO remove(Long id);

    public EmployeeDTO find(Long id);

    public List<EmployeeDTO> findAll();

    public List<EmployeeDTO> findAllByCompanyId(Long companyId);
    
    public List<EmployeeDTO> findAllWithTeamId(Long companyId, Long teamId);
    
    public EmployeeDTO uploadImage(EmployeeDTO employeeDTO);
    
    
}
