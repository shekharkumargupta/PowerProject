/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.City;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface CityFacadeLocal {

    void create(City city) throws DBOperationException;

    void edit(City city) throws DBOperationException;

    void remove(City city);

    City find(Object id);

    List<City> findAll();

    List<City> findRange(int[] range);

    int count();
}
