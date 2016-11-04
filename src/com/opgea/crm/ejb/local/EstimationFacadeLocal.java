/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface EstimationFacadeLocal {

    void create(Estimation estimation) throws DBOperationException;

    void edit(Estimation estimation) throws DBOperationException;

    void remove(Estimation estimation);

    Estimation find(Object id);

    List<Estimation> findAll();

    List<Estimation> findAllByCompanyId(Long companyId);

    List<Estimation> findAllByClientId(Long clientId);

    List<Estimation> findRange(int[] range);

    int count();
}
