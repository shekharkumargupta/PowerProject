/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Country;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.ejb.local.CountryFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CountryService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class CountryServiceImpl implements CountryService {

    @EJB
    private CountryFacadeLocal countryFacade;

    @Override
    public void create(SimpleModel country) throws DBOperationException{
        Country countryObject = new Country();
        countryObject.setId(country.getId());
        countryObject.setName(country.getValue());
        try{
        countryFacade.create(countryObject);
        }catch(EJBException ex){
            throw new DBOperationException(ex.getLocalizedMessage());
        }
    }

    @Override
    public void update(SimpleModel country) throws DBOperationException {
        if (country.getId() != null && country.getId() > 0) {
            Country countryObject = new Country();
            countryObject.setId(country.getId());
            countryObject.setName(country.getValue());
            countryFacade.edit(countryObject);
        }else{
            this.create(country);
        }
    }

    @Override
    public SimpleModel find(Long id) {
        Country country = countryFacade.find(id);
        SimpleModel model = new SimpleModel();
        model.setId(country.getId());
        model.setValue(country.getName());
        return model;
    }

    @Override
    public List<SimpleModel> findAll() {
        List<Country> countries = countryFacade.findAll();
        List<SimpleModel> models = new ArrayList<SimpleModel>();
        for (Country country : countries) {
            SimpleModel model = new SimpleModel();
            model.setId(country.getId());
            model.setValue(country.getName());
            models.add(model);
        }
        return models;
    }

    @Override
    public void remove(Long id) {
        Country country = countryFacade.find(id);
        countryFacade.remove(country);
    }
}
