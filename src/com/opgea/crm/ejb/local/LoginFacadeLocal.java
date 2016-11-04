/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Login;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface LoginFacadeLocal {

    void create(Login login) throws DBOperationException;

    void edit(Login login)throws DBOperationException;

    void remove(Login login);

    Login find(Object id);
    
    Login findByLoginId(String loginId);

    List<Login> findAll();

    List<Login> findRange(int[] range);

    int count();
    
}
