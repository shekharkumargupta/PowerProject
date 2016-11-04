/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.entities.embeddable.TaskId;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ItemEstimationService;
import com.opgea.crm.service.JobStatusService;
import com.opgea.crm.service.ProjectService;
import com.opgea.crm.service.TaskService;
import com.opgea.crm.service.TeamService;
import com.opgea.crm.service.UtilService;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.ItemEstimationDTO;
import com.opgea.crm.web.dto.JobStatusDTO;
import com.opgea.crm.web.dto.ProjectDTO;
import com.opgea.crm.web.dto.TaskDTO;
import com.opgea.crm.web.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name = "taskController")
@SessionScoped
public class TaskController {

    private static final Logger logger = Logger.getLogger(TaskController.class.getName());
    @Inject
    private ProjectService projectService;
    @Inject
    private ItemEstimationService itemEstimationService;
    @Inject
    private UtilService utilService;
    @Inject
    private TeamService teamService;
    @Inject
    private TaskService taskService;
    @Inject
    private JobStatusService jobStatusService;
    private TaskDTO taskDTO = new TaskDTO();
    private List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
    private List<ItemEstimationDTO> itemEstimationList = new ArrayList<ItemEstimationDTO>();
    private List<ItemEstimationDTO> itemEstimationDetailList = new ArrayList<ItemEstimationDTO>();
    private List<SimpleModel> durationTypeList = new ArrayList<SimpleModel>();
    private List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
    private List<EmployeeDTO> memberDTOList = new ArrayList<EmployeeDTO>();
    private List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
    private List<JobStatusDTO> jobStatusList = new ArrayList<JobStatusDTO>();
    private Long selectedTeamId;
    private Long selectedItemEstimationId;

    @PostConstruct
    public void init() {
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        projectDTOList = projectService.findAll();
        teamDTOList = teamService.findAllByCompanyId(sessionData.getCompanyId());
        /**
         * Populating DurationList but no Initial selection option required.
         */
        durationTypeList = utilService.getAllDurationTypes();
        taskDTOList = taskService.findAll();
        jobStatusList = jobStatusService.findAllByCompanyId(sessionData.getCompanyId());
        logger.info("TaskController Initialized.");
    }

    public List<SimpleModel> getDurationTypeList() {
        return durationTypeList;
    }

    public void setDurationTypeList(List<SimpleModel> durationTypeList) {
        this.durationTypeList = durationTypeList;
    }

    public List<ProjectDTO> getProjectDTOList() {
        return projectDTOList;
    }

    public void setProjectDTOList(List<ProjectDTO> projectDTOList) {
        this.projectDTOList = projectDTOList;
    }

    public List<ItemEstimationDTO> getItemEstimationList() {
        return itemEstimationList;
    }

    public void setItemEstimationList(List<ItemEstimationDTO> itemEstimationList) {
        this.itemEstimationList = itemEstimationList;
    }

    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    public List<EmployeeDTO> getMemberDTOList() {
        return memberDTOList;
    }

    public void setMemberDTOList(List<EmployeeDTO> memberDTOList) {
        this.memberDTOList = memberDTOList;
    }

    public List<TeamDTO> getTeamDTOList() {
        return teamDTOList;
    }

    public void setTeamDTOList(List<TeamDTO> teamDTOList) {
        this.teamDTOList = teamDTOList;
    }

    public Long getSelectedTeamId() {
        return selectedTeamId;
    }

    public void setSelectedTeamId(Long selectedTeamId) {
        this.selectedTeamId = selectedTeamId;
    }

    public List<TaskDTO> getTaskDTOList() {
        return taskDTOList;
    }

    public void setTaskDTOList(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }

    public List<JobStatusDTO> getJobStatusList() {
        return jobStatusList;
    }

    public void setJobStatusList(List<JobStatusDTO> jobStatusList) {
        this.jobStatusList = jobStatusList;
    }

    public List<ItemEstimationDTO> getItemEstimationDetailList() {
        return itemEstimationDetailList;
    }

    public void setItemEstimationDetailList(List<ItemEstimationDTO> itemEstimationDetailList) {
        this.itemEstimationDetailList = itemEstimationDetailList;
    }

    public Long getSelectedItemEstimationId() {
        return selectedItemEstimationId;
    }

    public void setSelectedItemEstimationId(Long selectedItemEstimationId) {
        this.selectedItemEstimationId = selectedItemEstimationId;
    }

    public String newEntry() {
        taskDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String projectId = (String) params.get("projectId");
        String taskIdString = (String) params.get("taskId");
        TaskId taskId = new TaskId(Long.parseLong(projectId), taskIdString);
        this.taskDTO = taskService.find(taskId);
        return "view.xhtml";
    }

    public String save() {
        System.out.println(taskDTO);
        ItemEstimationDTO itemEstimationDTO = itemEstimationService.find(selectedItemEstimationId);
        taskDTO.setAssignedHrs(itemEstimationDTO.getDurationValue());
        try {
            taskService.update(taskDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    taskDTO.getName() + " saved successfully!", null);
            //taskDTO.reset();
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public void saveAjax(AjaxBehaviorEvent ae) {
        try {
            taskService.update(taskDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    taskDTO.getName() + " saved successfully!", null);
            reloadItemEstimation(ae);
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
        String taskIdString = (String) params.get("taskId");
        TaskId taskId = new TaskId(Long.parseLong(projectId), taskIdString);
        this.taskDTO = taskService.find(taskId);
        return "create.xhtml";
    }

    public void delete(AjaxBehaviorEvent ae) {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String taskId = (String) params.get("taskId");
        long projectId = Long.parseLong(params.get("projectId"));
        TaskId taskIdObj = new TaskId(projectId, taskId);
        taskDTO.setTaskId(taskIdObj);
        this.taskDTO = taskService.remove(taskDTO);
        reloadItemEstimation(ae);
    }

    public void reloadItemEstimation(AjaxBehaviorEvent ae) {
        System.out.println("Task DTO: " + taskDTO);
        ProjectDTO dto = projectService.find(taskDTO.getTaskId().getProjectId());
        itemEstimationList = itemEstimationService.findAllByEstimationGroupBy(dto.getEstimationId());
        taskDTOList = taskService.findAllByProjectId(dto.getId());
        System.out.println("Task List: " + taskDTOList.size());
    }

    public void reloadAllEstimatedTimeListByItem(AjaxBehaviorEvent ae) {

        //ProjectDTO dto = projectService.find(taskDTO.getTaskId().getProjectId());
        ItemEstimationDTO itemEstimationDTO = itemEstimationService.find(selectedItemEstimationId);
        this.itemEstimationDetailList =
                itemEstimationService.findAllByEstimationAndItem(
                itemEstimationDTO.getEstimationId(),
                itemEstimationDTO.getItemId());


        System.out.println("Estimation Id: " + itemEstimationDTO.getEstimationId());
        System.out.println("Selected Item: " + itemEstimationDTO.getItemId());
        System.out.println("List Size: " + itemEstimationDetailList.size());
    }

    public void showTeamMembers() {
        System.out.println("Selected Team Id: " + selectedTeamId);
        memberDTOList = teamService.findAllMembers(selectedTeamId);
    }
}
