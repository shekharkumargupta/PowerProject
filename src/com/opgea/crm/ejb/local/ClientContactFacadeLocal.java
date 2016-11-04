/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.ClientContact;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ClientContactFacadeLocal {

    void create(ClientContact clientContact) throws DBOperationException;

    void edit(ClientContact clientContact) throws DBOperationException;

    void remove(ClientContact clientContact);

    ClientContact find(Object id);

    List<ClientContact> findByClientId(Long id);

    List<ClientContact> findByCompanyId(Long companyId);

    List<ClientContact> findAll();

    List<ClientContact> findRange(int[] range);

    int count();
}
