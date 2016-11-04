/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.domain.entities.Project;
import com.opgea.crm.domain.entities.Team;
import com.opgea.crm.ejb.JobStatusFacade;
import com.opgea.crm.ejb.local.ClientFacadeLocal;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.EstimationFacadeLocal;
import com.opgea.crm.ejb.local.JobStatusFacadeLocal;
import com.opgea.crm.ejb.local.ProjectFacadeLocal;
import com.opgea.crm.ejb.local.TeamFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ProjectService;
import com.opgea.crm.web.dto.ProjectDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class ProjectServiceImpl implements ProjectService {

    @EJB
    private ProjectFacadeLocal projectFacade;
    @EJB
    private EstimationFacadeLocal estimationFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;
    @EJB
    private ClientFacadeLocal clientFacade;
    @EJB
    private TeamFacadeLocal teamFacade;
    @EJB
    private JobStatusFacadeLocal jobStatusFacade;

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) throws DBOperationException {
        try {
            Project project = new Project();
            projectDTO.unmarshall(project);
            projectFacade.create(project);
            if (projectDTO.getClientId() != null) {
                Client client = clientFacade.find(projectDTO.getClientId());
                project.setClient(client);
            }
            if (projectDTO.getEstimationId() != null) {
                Estimation estimation = estimationFacade.find(projectDTO.getEstimationId());
                project.setEstimation(estimation);
            }
            if (projectDTO.getTeamId() != null) {
                Team team = teamFacade.find(projectDTO.getTeamId());
                project.setTeam(team);
            }
            if (projectDTO.getJobStatusId() != null) {
                JobStatus status = jobStatusFacade.find(projectDTO.getJobStatusId());
                project.setJobStatus(status);
            }
            projectFacade.edit(project);
            projectDTO.setId(project.getId());
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getMessage());
        }
        return projectDTO;
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) throws DBOperationException {
        if (projectDTO.getId() != null && projectDTO.getId() > 0) {
            Project project = projectFacade.find(projectDTO.getId());
            projectDTO.unmarshall(project);

            if (projectDTO.getClientId() != null) {
                Client client = clientFacade.find(projectDTO.getClientId());
                project.setClient(client);
            }
            if (projectDTO.getTeamId() != null) {
                Team team = teamFacade.find(projectDTO.getTeamId());
                project.setTeam(team);
            }
            if (projectDTO.getJobStatusId() != null) {
                JobStatus status = jobStatusFacade.find(projectDTO.getJobStatusId());
                project.setJobStatus(status);
            }
            projectFacade.edit(project);
            projectDTO.setId(project.getId());
        } else {
            this.create(projectDTO);
        }
        return projectDTO;
    }

    @Override
    public ProjectDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProjectDTO find(Long id) {
        Project project = projectFacade.find(id);
        System.out.println(project);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.marshall(project);
        return projectDTO;
    }

    @Override
    public ProjectDTO findByEstimationId(Long estimationId) {
        Project project = projectFacade.findByEstimationId(estimationId);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.marshall(project);
        return projectDTO;
    }

    @Override
    public List<ProjectDTO> findAll() {
        List<Project> projectList = projectFacade.findAll();
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        for (Project project : projectList) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.marshall(project);
            projectDTOList.add(projectDTO);
            System.out.println("ProjectDTO: " + projectDTO);
        }
        return projectDTOList;
    }

    @Override
    public List<ProjectDTO> findAllByClientId(Long clientId) {
        List<Project> projectList = projectFacade.findAllByClientId(clientId);
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        for (Project project : projectList) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.marshall(project);
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    @Override
    public ProjectDTO defaultCreate(Long companyId, String projectName,
            Long estimationId, Long clientId) throws DBOperationException {
        Project project = projectFacade.defaultCreate(companyId, projectName, estimationId, clientId);
        System.out.println("Created Project: " + project);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.marshall(project);
        projectDTO.setId(project.getId());
        return projectDTO;
    }
}
