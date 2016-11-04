/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CountryService {
    
    public void create(SimpleModel country) throws DBOperationException;
    public void update(SimpleModel country) throws DBOperationException;
    public SimpleModel find(Long id);
    public List<SimpleModel> findAll();
    public void remove(Long id);
}
