/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.service.ClientService;
import com.opgea.crm.service.EstimationService;
import com.opgea.crm.service.JobStatusService;
import com.opgea.crm.service.ProjectService;
import com.opgea.crm.web.dto.ClientDTO;
import com.opgea.crm.web.dto.EstimationDTO;
import com.opgea.crm.web.dto.JobStatusDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name="estimationController")
@RequestScoped
public class EstimationController {
    
    @Inject
    private EstimationService estimationService;
    @Inject
    private ClientService clientService;
    @Inject
    private JobStatusService jobStatusService;
    @Inject
    private ProjectService projectService;
    
    
    
    
    private EstimationDTO estimationDTO = new EstimationDTO();
    private List<ClientDTO> clientList = new ArrayList<ClientDTO>();
    private List<JobStatusDTO> jobStatusList = new ArrayList<JobStatusDTO>();
    private List<EstimationDTO> estimationDTOList = new ArrayList<EstimationDTO>();
    
    
    @PostConstruct
    public void init(){
        System.out.println("EstimationController Initialized...");
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA); 
        clientList = clientService.findAllByCompanyId(data.getCompanyId());
        jobStatusList = jobStatusService.findAllByDepartmentId(data.getEmployeeDTO().getDepartmentId());
        System.out.println("Emp Name: "+data.getEmployeeDTO().getFirstName());
        System.out.println("Department:"+data.getEmployeeDTO().getDepartmentId());
        estimationDTOList = estimationService.findAllByCompanyId(data.getCompanyId());
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }

    public EstimationDTO getEstimationDTO() {
        return estimationDTO;
    }

    public void setEstimationDTO(EstimationDTO estimationDTO) {
        this.estimationDTO = estimationDTO;
    }

    public List<JobStatusDTO> getJobStatusList() {
        return jobStatusList;
    }

    public void setJobStatusList(List<JobStatusDTO> jobStatusList) {
        this.jobStatusList = jobStatusList;
    }

    public List<EstimationDTO> getEstimationDTOList() {
        return estimationDTOList;
    }

    public void setEstimationDTOList(List<EstimationDTO> estimationDTOList) {
        this.estimationDTOList = estimationDTOList;
    }
    
    public String newEntry() {
        estimationDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String estimationId = (String) params.get("estimationId");
        this.estimationDTO = estimationService.find(new Long(estimationId));
        return "view.xhtml";
    }

    public String save() {
        if (estimationDTO.getId() != null && estimationDTO.getId() > 0) {
            estimationService.update(estimationDTO);
        } else {
            System.out.println("EstimationDTO: "+estimationDTO);
            estimationService.create(estimationDTO);
        }
        return "success.xhtml";
    }
    
    public void saveAjax() {
        System.out.println("EstimationDTO: "+estimationDTO);
        if (estimationDTO.getId() != null && estimationDTO.getId() > 0) {
            estimationService.update(estimationDTO);
        } else {
            System.out.println("EstimationDTO: "+estimationDTO);
            estimationService.create(estimationDTO);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                estimationDTO.getName()+ " saved.", null));
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String estimationId = (String) params.get("estimationId");
        this.estimationDTO = estimationService.find(new Long(estimationId));
        return "create.xhtml";
    }

    public String delete() {
        this.estimationDTO = estimationService.find(estimationDTO.getId());
        return "list.xhtml";
    }
    
    public String convertEstimationToProject(EstimationDTO estimationDTO){
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        projectService.defaultCreate(data.getCompanyId(), estimationDTO.getName(),
                estimationDTO.getId(), estimationDTO.getClientId());
        return "list.xhtml";
    }
    
}
