/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.domain.entities.City;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CityService {

    public SimpleModel create(Long countryId, SimpleModel city) throws DBOperationException;

    public SimpleModel update(Long countryId, SimpleModel city) throws DBOperationException;

    public SimpleModel remove(Long id);

    public SimpleModel find(Long id);

    public List<City> findAll();
}
