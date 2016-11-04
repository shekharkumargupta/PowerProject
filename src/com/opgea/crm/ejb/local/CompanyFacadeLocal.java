/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface CompanyFacadeLocal {

    void create(Company company) throws DBOperationException;

    void edit(Company company) throws DBOperationException;

    void remove(Company company);

    Company find(Object id);

    List<Company> findAll();

    List<Company> findRange(int[] range);

    int count();
}
