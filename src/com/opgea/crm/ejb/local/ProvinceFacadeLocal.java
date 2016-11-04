/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Province;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ProvinceFacadeLocal {

    void create(Province province) throws DBOperationException;

    void edit(Province province) throws DBOperationException;

    void remove(Province province);

    Province find(Object id);

    List<Province> findAll();

    List<Province> findRange(int[] range);

    int count();
}
