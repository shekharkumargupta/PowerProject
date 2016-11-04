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
import com.opgea.crm.service.JobService;
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.JobDTO;
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
@ManagedBean(name = "jobController")
@RequestScoped
public class JobController {

    @Inject
    private DepartmentService departmentService;
    @Inject
    private JobService jobService;
    private JobDTO jobDTO = new JobDTO();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<JobDTO> jobList = new ArrayList<JobDTO>();

    @PostConstruct
    public void init() {
        System.out.println("JobController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());
        jobList = jobService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public JobDTO getJobDTO() {
        return jobDTO;
    }

    public void setJobDTO(JobDTO jobDTO) {
        this.jobDTO = jobDTO;
    }

    public List<JobDTO> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobDTO> jobList) {
        this.jobList = jobList;
    }

    public String newEntry() {
        jobDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String jobId = (String) params.get("jobId");
        this.jobDTO = jobService.find(new Long(jobId));
        return "view.xhtml";
    }

    public String save() {
        try {
            jobService.update(jobDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    jobDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(JobController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String jobId = (String) params.get("jobId");
        this.jobDTO = jobService.find(new Long(jobId));
        return "create.xhtml";
    }

    public String delete() {
        this.jobDTO = jobService.find(jobDTO.getId());
        return "list.xhtml";
    }
}
