/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.DepartmentService;
import com.opgea.crm.service.JobStatusService;
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.JobStatusDTO;
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
@ManagedBean(name="jobStatusController")
@RequestScoped
public class JobStatusController {
    
    @Inject
    private DepartmentService departmentService;
    @Inject
    private JobStatusService jobStatusService;
    private JobStatusDTO jobStatusDTO = new JobStatusDTO();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<JobStatusDTO> jobStatusList = new ArrayList<JobStatusDTO>();

    @PostConstruct
    public void init() {
        System.out.println("JobStatusController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());
        jobStatusList = jobStatusService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public JobStatusDTO getJobStatusDTO() {
        return jobStatusDTO;
    }

    public void setJobStatusDTO(JobStatusDTO jobStatusDTO) {
        this.jobStatusDTO = jobStatusDTO;
    }

    public List<JobStatusDTO> getJobStatusList() {
        return jobStatusList;
    }

    public void setJobStatusList(List<JobStatusDTO> jobStatusList) {
        this.jobStatusList = jobStatusList;
    }

    public String newEntry() {
        jobStatusDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String jobStatusId = (String) params.get("jobStatusId");
        this.jobStatusDTO = jobStatusService.find(new Long(jobStatusId));
        return "view.xhtml";
    }

    public String save() {
        try {
            jobStatusService.update(jobStatusDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    jobStatusDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(JobStatusController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String jobStatusId = (String) params.get("jobStatusId");
        this.jobStatusDTO = jobStatusService.find(new Long(jobStatusId));
        return "create.xhtml";
    }

    public String delete() {
        this.jobStatusDTO = jobStatusService.find(jobStatusDTO.getId());
        return "list.xhtml";
    }
}
