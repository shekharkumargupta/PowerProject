/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ClientService;
import com.opgea.crm.service.EstimationService;
import com.opgea.crm.service.ProjectService;
import com.opgea.crm.service.TeamService;
import com.opgea.crm.web.dto.ClientDTO;
import com.opgea.crm.web.dto.EstimationDTO;
import com.opgea.crm.web.dto.ProjectDTO;
import com.opgea.crm.web.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name = "projectController")
@RequestScoped
public class ProjectController {

    @Inject
    private ProjectService projectService;
    @Inject
    private EstimationService estimationService;
    @Inject
    private ClientService clientService;
    @Inject
    private TeamService teamService;
    
    private ProjectDTO projectDTO = new ProjectDTO();
    private List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
    private List<EstimationDTO> estimationDTOList = new ArrayList<EstimationDTO>();
    private List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
    private List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
    
    @PostConstruct
    public void init() {
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        clientDTOList = clientService.findAllByCompanyId(sessionData.getCompanyId());
        //estimationDTOList = estimationService.findAllByClientId(projectDTO.getClientId());
        //projectDTOList = projectService.findAllByClientId(projectDTO.getClientId());
        projectDTOList = projectService.findAll();
        teamDTOList = teamService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public List<ClientDTO> getClientDTOList() {
        return clientDTOList;
    }

    public void setClientDTOList(List<ClientDTO> clientDTOList) {
        this.clientDTOList = clientDTOList;
    }

    public List<EstimationDTO> getEstimationDTOList() {
        return estimationDTOList;
    }

    public void setEstimationDTOList(List<EstimationDTO> estimationDTOList) {
        this.estimationDTOList = estimationDTOList;
    }

    public List<ProjectDTO> getProjectDTOList() {
        return projectDTOList;
    }

    public void setProjectDTOList(List<ProjectDTO> projectDTOList) {
        this.projectDTOList = projectDTOList;
    }

    public List<TeamDTO> getTeamDTOList() {
        return teamDTOList;
    }

    public void setTeamDTOList(List<TeamDTO> teamDTOList) {
        this.teamDTOList = teamDTOList;
    }

    public String newEntry() {
        projectDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String projectId = (String) params.get("projectId");
        this.projectDTO = projectService.find(new Long(projectId));
        return "view.xhtml";
    }

    public String save() {
        System.out.println(projectDTO);
        try {
            projectService.update(projectDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    projectDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public void saveAjax() {
        try {
            projectService.update(projectDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    projectDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String projectId = (String) params.get("projectId");
        System.out.println("ProjectId: " + projectId);
        this.projectDTO = projectService.find(new Long(projectId));
        return "create.xhtml";
    }

    public String delete() {
        this.projectDTO = projectService.find(projectDTO.getId());
        return "list.xhtml";
    }
}
