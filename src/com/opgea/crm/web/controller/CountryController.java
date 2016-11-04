/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CountryService;
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
@ManagedBean(name = "countryController")
@RequestScoped
public class CountryController {

    @Inject
    private CountryService countryService;
    private SimpleModel countryDTO = new SimpleModel();
    private List<SimpleModel> countryList = new ArrayList<SimpleModel>();

    @PostConstruct
    public void init() {
        //countryService = new CountryServiceImpl();
        countryList = countryService.findAll();
    }

    public SimpleModel getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(SimpleModel countryDTO) {
        this.countryDTO = countryDTO;
    }

    public List<SimpleModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<SimpleModel> countryList) {
        this.countryList = countryList;
    }

    public String newEntry() {
        countryDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String countryId = (String) params.get("countryId");
        this.countryDTO = countryService.find(new Long(countryId));
        return "view.xhtml";
    }

    public String save() {
        try {
            countryService.update(countryDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    countryDTO.getValue() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CountryController.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String countryId = (String) params.get("countryId");
        this.countryDTO = countryService.find(new Long(countryId));
        return "create.xhtml";
    }

    public String delete() {
        this.countryDTO = countryService.find(countryDTO.getId());
        return "list.xhtml";
    }
}
