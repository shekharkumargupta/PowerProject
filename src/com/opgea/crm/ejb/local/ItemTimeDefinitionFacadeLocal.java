/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.ItemTimeDefinition;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ItemTimeDefinitionFacadeLocal {

    void create(ItemTimeDefinition itemTimeDefinition)throws DBOperationException;

    void edit(ItemTimeDefinition itemTimeDefinition)throws DBOperationException;

    void remove(ItemTimeDefinition itemTimeDefinition);

    ItemTimeDefinition find(Object id);
    
    ItemTimeDefinition findById(Long id);

    List<ItemTimeDefinition> findAll();
    
    List<ItemTimeDefinition> findAllByItemId(Long itemId);
    
    List<ItemTimeDefinition> findAllByCompanyId(Long companyId);
    
    List<ItemTimeDefinition> findAllByCompanyAndItemId(Long companyId, Long itemId);

    List<ItemTimeDefinition> findRange(int[] range);

    int count();
    
}
