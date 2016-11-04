/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.TaskDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface TaskService {
    
    public TaskDTO create(TaskDTO TaskDTO) throws DBOperationException;

    public TaskDTO update(TaskDTO TaskDTO) throws DBOperationException;

    public TaskDTO remove(TaskDTO TaskDTO);

    public TaskDTO find(Object id);

    public List<TaskDTO> findAll();
    
    public List<TaskDTO> findRange(int[] range);
    
    public List<TaskDTO> findAllByProjectId(Long projectId);
    
    public List<TaskDTO> findAllByItemEstimationId(Long projectId);

    int count();
}
