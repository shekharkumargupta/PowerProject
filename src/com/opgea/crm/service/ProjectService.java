/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ProjectDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ProjectService {

    public ProjectDTO defaultCreate(Long companyId,
            String projectName, Long estimationId, Long clientId) throws DBOperationException;

    public ProjectDTO create(ProjectDTO projectDTO) throws DBOperationException;

    public ProjectDTO update(ProjectDTO projectDTO) throws DBOperationException;

    public ProjectDTO remove(Long id);

    public ProjectDTO find(Long id);

    public ProjectDTO findByEstimationId(Long estimationId);

    public List<ProjectDTO> findAll();

    public List<ProjectDTO> findAllByClientId(Long clientId);
}
