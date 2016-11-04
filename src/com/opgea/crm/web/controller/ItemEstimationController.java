/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.entities.qualifier.DurationType;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.service.DepartmentService;
import com.opgea.crm.service.EstimationService;
import com.opgea.crm.service.ItemEstimationService;
import com.opgea.crm.service.ItemService;
import com.opgea.crm.service.ItemTimeDefinitionService;
import com.opgea.crm.service.JobService;
import com.opgea.crm.service.UtilService;
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.EstimationDTO;
import com.opgea.crm.web.dto.ItemDTO;
import com.opgea.crm.web.dto.ItemEstimationDTO;
import com.opgea.crm.web.dto.ItemTimeDefinitionDTO;
import com.opgea.crm.web.dto.JobDTO;
import java.io.Serializable;
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
@ManagedBean(name = "itemEstimationController")
@SessionScoped
public class ItemEstimationController implements Serializable {

    private static final Logger logger = Logger.getLogger(ItemEstimationController.class.getName());
    @Inject
    private ItemService itemService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private JobService jobService;
    @Inject
    private UtilService utilService;
    @Inject
    private EstimationService estimationService;
    @Inject
    private ItemEstimationService itemEstimationService;
    @Inject
    private ItemTimeDefinitionService itemTimeDefinitinoService;
    private ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
    private List<ItemDTO> itemList = new ArrayList<ItemDTO>();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<JobDTO> jobList = new ArrayList<JobDTO>();
    private List<SimpleModel> durationTypeList = new ArrayList<SimpleModel>();
    private List<EstimationDTO> estimationList = new ArrayList<EstimationDTO>();
    private List<ItemEstimationDTO> itemEstimationList = new ArrayList<ItemEstimationDTO>();
    private List<ItemTimeDefinitionDTO> itemTimeDefinitionList = new ArrayList<ItemTimeDefinitionDTO>();

    @PostConstruct
    public void init() {
        logger.info("ItemEstimationController Initializing...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);

        /**
         * Populating ItemList with Initial Option 'SELECT'.
         */
        itemList.add(new ItemDTO(ApplicationConstants.SELECTION_SELECT,
                ApplicationConstants.getOptionName(ApplicationConstants.SELECTION_SELECT), null));
        itemList.addAll(itemService.findAllByCompanyId(sessionData.getCompanyId()));

        /**
         * Populating DepartmentList with Initial Option 'SELECT'.
         */
        departmentList.add(new DepartmentDTO(ApplicationConstants.SELECTION_SELECT,
                ApplicationConstants.getOptionName(ApplicationConstants.SELECTION_SELECT), null));
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());

        /**
         * Populating JobList.
         */
        jobList = jobService.findAllByCompanyId(sessionData.getCompanyId());

        /**
         * Populating DurationList but no Initial selection option required.
         */
        durationTypeList = utilService.getAllDurationTypes();

        /**
         * Populating DurationList but no Initial selection option required.
         */
        estimationList = estimationService.findAllByCompanyId(sessionData.getCompanyId());

        //itemTimeDefinitionList = itemTimeDefinitinoService.findAllByCompanyId(sessionData.getCompanyId());
        //System.out.println("ItemEstimationController >> init >> itemTimeDefinitionList: "+itemTimeDefinitionList.size());
        /**
         * Populating Item Estimation List that is already done.
         */
        itemEstimationList = itemEstimationService.findAllByCompanyId(sessionData.getCompanyId());
        logger.info("ItemEstimationController Initialized.");
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

    public ItemEstimationDTO getItemEstimationDTO() {
        return itemEstimationDTO;
    }

    public void setItemEstimationDTO(ItemEstimationDTO itemEstimationDTO) {
        this.itemEstimationDTO = itemEstimationDTO;
    }

    public List<ItemEstimationDTO> getItemEstimationList() {
        return itemEstimationList;
    }

    public void setItemEstimationList(List<ItemEstimationDTO> itemEstimationList) {
        this.itemEstimationList = itemEstimationList;
    }

    public List<SimpleModel> getDurationTypeList() {
        return durationTypeList;
    }

    public void setDurationTypeList(List<SimpleModel> durationTypeList) {
        this.durationTypeList = durationTypeList;
    }

    public List<EstimationDTO> getEstimationList() {
        return estimationList;
    }

    public void setEstimationList(List<EstimationDTO> estimationList) {
        this.estimationList = estimationList;
    }

    public List<ItemTimeDefinitionDTO> getItemTimeDefinitionList() {
        return itemTimeDefinitionList;
    }

    public void setItemTimeDefinitionList(List<ItemTimeDefinitionDTO> itemTimeDefinitionList) {
        this.itemTimeDefinitionList = itemTimeDefinitionList;
    }

    public String newEntry() {
        itemEstimationDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String timeId = (String) params.get("timeId");
        this.itemEstimationDTO = itemEstimationService.find(new Long(timeId));
        return "view.xhtml";
    }

    public void batchSave() {
        logger.log(Level.INFO, getClass().getName().concat(" >> ").concat("batchSave"));
        System.out.println(itemEstimationDTO);
        itemEstimationService.createBatch(itemEstimationDTO);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                itemEstimationDTO.getItemName() + "\'s defined for estimation "
                + itemEstimationDTO.getEstimationName(), null));
    }

    public void save() {
        int index = itemTimeDefinitionList.indexOf(new ItemTimeDefinitionDTO(itemEstimationDTO.getDefaultTimeDefinitionId()));
        ItemTimeDefinitionDTO itemTimeDefinitionDTO = itemTimeDefinitionList.get(index);
        System.out.println(itemEstimationDTO);
        logger.log(Level.INFO, "ItemTimeDefinitionDTO: ".concat(itemTimeDefinitionDTO.toString()));
        itemEstimationDTO.createEstimationFromDefaultDefinition(itemTimeDefinitionDTO);
        logger.log(Level.INFO, "ItemEstimationDTO: ".concat(itemEstimationDTO.toString()));

        itemEstimationService.create(itemEstimationDTO);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                itemEstimationDTO.getItemName() + "\'s "
                + itemEstimationDTO.getJobName()
                + " times "
                + itemEstimationDTO.getDurationValue()
                + " " + DurationType.values()[itemEstimationDTO.getDurationTypeId()]
                + " defined.", null));
    }

    public void update(ItemEstimationDTO itemEstimationDTO) {
        System.out.println("ItemEstimationDTO >> update: " + itemEstimationDTO);
        itemEstimationService.update(itemEstimationDTO);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                itemEstimationDTO.getItemName() + "\'s "
                + itemEstimationDTO.getJobName()
                + " times "
                + itemEstimationDTO.getDurationValue()
                + " " + DurationType.values()[itemEstimationDTO.getDurationTypeId()]
                + " defined.", null));
    }

    public String edit() {
        System.out.println("ItemEstiationController >> edit");
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String itemEstimationId = "59";
        System.out.println("ItemEstimationId: " + itemEstimationId);
        //String itemEstimationId = (String) params.get("itemEstimationId");
        
        this.itemEstimationDTO = itemEstimationService.find(59L);
        return "create.xhtml";
    }

    public String delete() {
        this.itemEstimationDTO = itemEstimationService.find(itemEstimationDTO.getId());
        return "list.xhtml";
    }

    public void refresh() {
        System.out.println("ItemEstimationController >> refresh");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        System.out.println(itemEstimationDTO);
        this.itemTimeDefinitionList = 
                itemTimeDefinitinoService.findAllByCompanyAndItemId(
                sessionData.getCompanyId(), this.itemEstimationDTO.getItemId());
        reloadAllEstimatedTimeListByItem();
    }

    public void reloadAllEstimatedTimeListByItem() {
        System.out.println("ItemEstimationId: " + this.itemEstimationDTO.getEstimationId()
                + " | ItemId: " + this.itemEstimationDTO.getItemId());
        this.itemEstimationList =
                itemEstimationService.findAllByEstimationAndItem(
                this.itemEstimationDTO.getEstimationId(),
                this.itemEstimationDTO.getItemId());
    }

    public void reloadAllEstimatedTimeList() {
        System.out.println("ItemEstimationId: " + this.itemEstimationDTO.getEstimationId());
        this.itemEstimationList =
                itemEstimationService.findAllByEstimation(this.itemEstimationDTO.getEstimationId());
    }
}
