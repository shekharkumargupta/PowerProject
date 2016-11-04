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
import com.opgea.crm.web.dto.DepartmentDTO;
import com.opgea.crm.web.dto.DesignationDTO;
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
@ManagedBean(name = "designationController")
@RequestScoped
public class DesignationController {

    @Inject
    private DepartmentService departmentService;
    @Inject
    private DesignationService designationService;
    private DesignationDTO designationDTO = new DesignationDTO();
    private List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
    private List<DesignationDTO> designationList = new ArrayList<DesignationDTO>();

    @PostConstruct
    public void init() {
        System.out.println("DesignationController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        departmentList = departmentService.findAllByCompanyId(sessionData.getCompanyId());
        designationList = designationService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public List<DepartmentDTO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDTO> departmentList) {
        this.departmentList = departmentList;
    }

    public DesignationDTO getDesignationDTO() {
        return designationDTO;
    }

    public void setDesignationDTO(DesignationDTO designationDTO) {
        this.designationDTO = designationDTO;
    }

    public List<DesignationDTO> getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List<DesignationDTO> designationList) {
        this.designationList = designationList;
    }

    public String newEntry() {
        designationDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String designationId = (String) params.get("designationId");
        this.designationDTO = designationService.find(new Long(designationId));
        return "view.xhtml";
    }

    public String save() {
        try {
            designationService.update(designationDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    designationDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String designationId = (String) params.get("designationId");
        this.designationDTO = designationService.find(new Long(designationId));
        return "create.xhtml";
    }

    public String delete() {
        this.designationDTO = designationService.find(designationDTO.getId());
        return "list.xhtml";
    }
}
