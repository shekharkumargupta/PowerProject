/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.DepartmentService;
import com.opgea.crm.service.ItemService;
import com.opgea.crm.service.ItemTimeDefinitionService;
import com.opgea.crm.service.JobService;
import com.opgea.crm.service.UtilService;
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.ItemDTO;
import com.opgea.crm.web.dto.ItemTimeDefinitionDTO;
import com.opgea.crm.web.dto.JobDTO;
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
@ManagedBean(name = "timeController")
@SessionScoped
public class ItemTimeDefinitionController {

    @Inject
    private ItemTimeDefinitionService timeService;
    @Inject
    private ItemService itemService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private JobService jobService;
    @Inject
    private UtilService utilService;
    
    private ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
    private List<ItemDTO> itemList = new ArrayList<ItemDTO>();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<JobDTO> jobList = new ArrayList<JobDTO>();
    private List<ItemTimeDefinitionDTO> timeList = new ArrayList<ItemTimeDefinitionDTO>();
    private List<SimpleModel> durationTypeList = new ArrayList<SimpleModel>();

    @PostConstruct
    public void init() {
        System.out.println("ItemTimeDefinitionController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        itemList = itemService.findAllByCompanyId(sessionData.getCompanyId());
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());
        jobList = jobService.findAllByCompanyId(sessionData.getCompanyId());
        durationTypeList = utilService.getAllDurationTypes();
        //timeList = timeService.findAllByCompanyId(sessionData.getCompanyId());
        
        System.out.println("Item Size: "+itemList.size());
        System.out.println("Department Size: "+departmentList.size());
        System.out.println("JobList: "+jobList.size());
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }

    public List<JobDTO> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobDTO> jobList) {
        this.jobList = jobList;
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public ItemTimeDefinitionDTO getTimeDTO() {
        return timeDTO;
    }

    public void setTimeDTO(ItemTimeDefinitionDTO timeDTO) {
        this.timeDTO = timeDTO;
    }

    public List<ItemTimeDefinitionDTO> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<ItemTimeDefinitionDTO> timeList) {
        this.timeList = timeList;
    }

    public List<SimpleModel> getDurationTypeList() {
        return durationTypeList;
    }

    public void setDurationTypeList(List<SimpleModel> durationTypeList) {
        this.durationTypeList = durationTypeList;
    }

    public String newEntry() {
        timeDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String timeId = (String) params.get("timeId");
        this.timeDTO = timeService.find(new Long(timeId));
        return "view.xhtml";
    }

    public String save() {
        try {
            timeService.update(timeDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                timeDTO.getJobName()+
                " time updated for "+
                timeDTO.getItemName()+
                " as "+timeDTO.getDuration()+
                " "+timeDTO.getDurationTypeName() , null));
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }
    
    public void saveAjax(ItemTimeDefinitionDTO timeDTO){
        try {
            timeService.update(timeDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                timeDTO.getJobName()+
                " time updated for "+
                timeDTO.getItemName()+
                " as "+timeDTO.getDuration()+
                " "+timeDTO.getDurationTypeName() , null));
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }        
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String timeId = (String) params.get("timeId");
        this.timeDTO = timeService.find(new Long(timeId));
        return "create.xhtml";
    }

    public String delete() {
        this.timeDTO = timeService.find(timeDTO.getId());
        return "list.xhtml";
    }
    
    public void reloadTimeList(AjaxBehaviorEvent ae){
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        this.timeList = timeService.findAllByCompanyAndItemId(
                sessionData.getCompanyId(), this.timeDTO.getItemId());
    }
}
