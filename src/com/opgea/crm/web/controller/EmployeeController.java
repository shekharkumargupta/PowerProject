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
import com.opgea.crm.service.DesignationService;
import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.DesignationDTO;
import com.opgea.crm.web.dto.EmployeeDTO;
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
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name = "employeeController")
@SessionScoped
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private DesignationService designationService;
    private EmployeeDTO employeeDTO = new EmployeeDTO();
    private List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();

    @PostConstruct
    public void init() {
        System.out.println("EmployeeController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        employeeList = employeeService.findAllByCompanyId(sessionData.getCompanyId());
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());
        designationList = designationService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public List<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public List<DesignationDTO> getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List<DesignationDTO> designationList) {
        this.designationList = designationList;
    }

    public String newEntry() {
        employeeDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String employeeId = (String) params.get("employeeId");
        this.employeeDTO = employeeService.find(new Long(employeeId));
        return "view.xhtml";
    }

    public String save() {
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        employeeDTO.setCompanyId(data.getCompanyId());
        try {
            employeeService.update(employeeDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    employeeDTO.getFirstName() + " saved successfully!", null);
            getEmployeeDTO().reset();
        } catch (DBOperationException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String employeeId = (String) params.get("employeeId");
        this.employeeDTO = employeeService.find(new Long(employeeId));
        return "create.xhtml";
    }

    public String delete() {
        this.employeeDTO = employeeService.find(getEmployeeDTO().getId());
        return "list.xhtml";
    }

    public String imageUpload(){
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String employeeId = (String) params.get("employeeId");
        this.employeeDTO = employeeService.find(new Long(employeeId));
        return "uploadImage.xhtml";
    }
    
    public void loadDesignationList(AjaxBehaviorEvent ae) {
        System.out.println("DepartmentId: " + employeeDTO.getDepartmentId());
        designationList = designationService.findAllByDepartmentId(employeeDTO.getDepartmentId());
    }

    public void uploadImage(FileUploadEvent event) {
        //String employeeId = FacesUtil.getHttpServletRequest().getParameter("employeeId");
        //System.out.println("Employee Id: "+employeeId);
        //employeeDTO.setId(Long.parseLong(employeeId));
        employeeDTO.setPicture(event.getFile().getContents());
        employeeService.uploadImage(employeeDTO);
        FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                event.getFile().getFileName() + " is uploaded.", null);
        getEmployeeDTO().reset();
    }
}
