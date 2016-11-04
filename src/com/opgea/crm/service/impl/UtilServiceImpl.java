/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.City;
import com.opgea.crm.domain.entities.Country;
import com.opgea.crm.domain.entities.qualifier.ConditionType;
import com.opgea.crm.domain.entities.qualifier.ContactType;
import com.opgea.crm.domain.entities.qualifier.DurationType;
import com.opgea.crm.domain.model.CityModel;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.ejb.local.CityFacadeLocal;
import com.opgea.crm.ejb.local.CountryFacadeLocal;
import com.opgea.crm.service.UtilService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author Ramesh
 */
@Singleton
public class UtilServiceImpl implements UtilService{

    
    @EJB
    private CountryFacadeLocal countryDAO;
    @EJB
    private CityFacadeLocal cityDAO;
        
    @Override
    public List<SimpleModel> getAllCountries() {
        List<Country> countries = countryDAO.findAll();
        List<SimpleModel> countryList = new ArrayList<SimpleModel>();
        for(Country country : countries){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(country.getId()));
            model.setValue(country.getName());
            countryList.add(model);
        }
        return countryList;
    }

    @Override
    public List<SimpleModel> getAllCities() {
        List<City> cities = cityDAO.findAll();
        List<SimpleModel> cityList = new ArrayList<SimpleModel>();
        for(City city : cities){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            cityList.add(model);
        }
        return cityList;
    }

    @Override
    public List<CityModel> getCitiesByCountry(Long countryId) {
        /*
        List<City> cities = cityDAO.findAllByCountry(countryId);
        List<CityModel> cityList = new ArrayList<CityModel>();
        for(City city : cities){
            CityModel model = new CityModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            model.setCountryId(city.getCountry().getId());
            cityList.add(model);
        }
        return cityList;
         * 
         */
        throw new RuntimeException("Method is comment due to changes required. Please have a look. [Shekhar]");
    }


    @Override
    public List<SimpleModel> getAllContactTypes() {
        List<SimpleModel> contactTypeList = new ArrayList<SimpleModel>();
        for(ContactType qualifier : ContactType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            contactTypeList.add(model);
        }
        return contactTypeList;
    }

    @Override
    public List<SimpleModel> getAllConditionTypes() {
        List<SimpleModel> contactTypeList = new ArrayList<SimpleModel>();
        for(ConditionType qualifier : ConditionType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            contactTypeList.add(model);
        }
        return contactTypeList;
    }

    @Override
    public List<SimpleModel> getAllDurationTypes() {
        List<SimpleModel> durationTypeList = new ArrayList<SimpleModel>();
        for(DurationType qualifier : DurationType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            durationTypeList.add(model);
        }
        return durationTypeList;
    }
}
