package com.opgea.crm.web.controller;

import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CompanyService;
import com.opgea.crm.web.dto.CompanyDTO;
import java.io.Serializable;
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

@ManagedBean(name = "companyController")
@RequestScoped
public class CompanyController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private CompanyService companyService;
    private CompanyDTO companyDTO = new CompanyDTO();
    private List<CompanyDTO> companyList = new ArrayList<CompanyDTO>();

    public CompanyController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("CompanyController Initialized...");
        companyList = companyService.findAll();
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public List<CompanyDTO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyDTO> companyList) {
        this.companyList = companyList;
    }

    public String newEntry() {
        companyDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String companyId = (String) params.get("companyId");
        this.companyDTO = companyService.find(new Long(companyId));
        return "view.xhtml";
    }

    public String save() throws DBOperationException {
        try {
            companyService.update(companyDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    companyDTO.getName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "signUp.xhtml";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String companyId = (String) params.get("companyId");
        this.companyDTO = companyService.find(new Long(companyId));
        return "create.xhtml";
    }

    public String delete() {
        this.companyDTO = companyService.find(getCompanyDTO().getId());
        return "list.xhtml";
    }
}
