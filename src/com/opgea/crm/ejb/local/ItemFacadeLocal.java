/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ItemFacadeLocal {

    void create(Item item) throws DBOperationException;

    void edit(Item item) throws DBOperationException;

    void remove(Item item);

    Item find(Object id);

    List<Item> findAll();

    List<Item> findAllByCompanyId(Long companyId);

    List<Item> findRange(int[] range);

    int count();
}
