/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Country;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface CountryFacadeLocal {

    void create(Country country)throws DBOperationException;

    void edit(Country country)throws DBOperationException;

    void remove(Country country);

    Country find(Object id);

    List<Country> findAll();

    List<Country> findRange(int[] range);

    int count();
    
}
