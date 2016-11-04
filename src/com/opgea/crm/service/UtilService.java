/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.domain.model.CityModel;
import com.opgea.crm.domain.model.SimpleModel;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface UtilService {
    

    public List<SimpleModel> getAllCountries();
    public List<SimpleModel> getAllCities();
    public List<CityModel> getCitiesByCountry(Long countryId);
    
    public List<SimpleModel> getAllContactTypes();
    public List<SimpleModel> getAllConditionTypes();
    public List<SimpleModel> getAllDurationTypes();
}
