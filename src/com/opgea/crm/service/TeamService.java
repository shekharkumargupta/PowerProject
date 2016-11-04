/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.TeamDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface TeamService {

    public TeamDTO create(TeamDTO teamDTO) throws DBOperationException;

    public TeamDTO update(TeamDTO teamDTO) throws DBOperationException;

    public TeamDTO remove(TeamDTO teamDTO);

    public TeamDTO find(Object id);

    public List<TeamDTO> findAll();
    
    public List<TeamDTO> findAllByCompanyId(Object companyId);

    public List<TeamDTO> findRange(int[] range);
    
    public List<EmployeeDTO> findAllMembers(Long teamId);
    
    public void addMember(Long teamId, Long employeeId)throws DBOperationException;
    
    public void removeMember(Long teamId, Long employeeId) throws DBOperationException;

    int count();
}
