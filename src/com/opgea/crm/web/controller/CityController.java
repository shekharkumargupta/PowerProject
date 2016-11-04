package com.opgea.crm.web.controller;

import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.entities.City;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CityService;
import com.opgea.crm.service.CountryService;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "cityController")
@RequestScoped
public class CityController {

    @Inject
    private CountryService countryService;
    @Inject
    private CityService cityService;
    private Long countryId;
    private SimpleModel cityDTO = new SimpleModel();
    private List<SimpleModel> countryList = new ArrayList<SimpleModel>();
    private List<City> cityList = new ArrayList<City>();

    @PostConstruct
    public void init() {
        countryList = countryService.findAll();
        cityList = cityService.findAll();
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public SimpleModel getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(SimpleModel cityDTO) {
        this.cityDTO = cityDTO;
    }

    public List<SimpleModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<SimpleModel> countryList) {
        this.countryList = countryList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public String newEntry() {
        cityDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String cityId = (String) params.get("cityId");
        this.cityDTO = cityService.find(new Long(cityId));
        return "view.xhtml";
    }

    public String save() {
        try {
            cityService.update(countryId, cityDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    cityDTO.getValue() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String cityId = (String) params.get("cityId");
        this.cityDTO = cityService.find(new Long(cityId));
        return "create.xhtml";
    }

    public String delete() {
        this.cityDTO = cityService.find(cityDTO.getId());
        return "list.xhtml";
    }
}
