/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ClientFacadeLocal {

    void create(Client client)throws DBOperationException;

    void edit(Client client)throws DBOperationException;

    void remove(Client client);

    Client find(Object id);
    
    List<Client> findByCompanyId(Long companyId);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();
    
}
