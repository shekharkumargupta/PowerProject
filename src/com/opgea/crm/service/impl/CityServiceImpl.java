package com.opgea.crm.service.impl;

import java.util.List;

import com.opgea.crm.domain.entities.City;
import com.opgea.crm.domain.entities.Country;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.ejb.local.CityFacadeLocal;
import com.opgea.crm.ejb.local.CountryFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CityService;
import javax.ejb.EJB;

public class CityServiceImpl implements CityService {

    @EJB
    private CountryFacadeLocal countryFacade;
    @EJB
    private CityFacadeLocal cityFacade;

    public CityServiceImpl() {
    }

    @Override
    public SimpleModel create(Long counryId, SimpleModel cityModel) throws DBOperationException{
        Country country = countryFacade.find(counryId);
        City city = new City();
        city.setName(cityModel.getValue());
        cityFacade.create(city);
        city.setCountry(country);
        cityFacade.edit(city);
        cityModel.setId(city.getId());
        return cityModel;
    }

    @Override
    public SimpleModel update(Long countryId, SimpleModel cityModel) throws DBOperationException{
        if (cityModel.getId() != null && cityModel.getId() > 0) {
            City city = cityFacade.find(cityModel.getId());
            city.setName(cityModel.getValue());
            cityFacade.edit(city);
            cityModel.setId(city.getId());
            return cityModel;
        } else {
            return this.create(countryId, cityModel);
        }
    }

    @Override
    public SimpleModel remove(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SimpleModel find(Long id) {
        City city = cityFacade.find(id);
        SimpleModel model = new SimpleModel();
        model.setId(city.getId());
        model.setValue(city.getName());
        return model;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = cityFacade.findAll();
        /*
        List<SimpleModel> countryModel = new ArrayList<SimpleModel>();
        for (City city : cities) {
        SimpleModel model = new SimpleModel();
        model.setId(city.getId());
        model.setValue(city.getName());
        countryModel.add(model);
        }
        return countryModel;
         * 
         */
        return cities;
    }
}
