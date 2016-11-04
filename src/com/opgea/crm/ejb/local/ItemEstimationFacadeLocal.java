/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ItemEstimationFacadeLocal {

    void create(ItemEstimation itemEstimation) throws DBOperationException;

    void createBatch(Collection<ItemEstimation> itemEstimations) throws DBOperationException;

    void edit(ItemEstimation itemEstimation) throws DBOperationException;

    void remove(ItemEstimation itemEstimation);

    ItemEstimation find(Object id);

    /**
     * Find an Entity on the basis of Estimation, Job and Item that
     * helps to know whether the entity already exists or note 
     * before inserting a new entity of that Particular Estimation
     * with the same Job and Item.
     * value.
     * @param estimationId
     * @param jobId
     * @param itemId
     * @return 
     */
    ItemEstimation findExisting(Long estimationId, Long jobId, Long itemId);

    List<ItemEstimation> findAll();

    List<ItemEstimation> findAllByEstimation(Long estimationId);

    List<ItemEstimation> findAllByCompanyId(Long companyId);

    List<ItemEstimation> findAllByEstimationAndItem(Long estimationId, Long itemId);

    List<ItemEstimation> findRange(int[] range);
    
    List<ItemEstimation> findAllByEstimationGroupBy(Long estimationId);

    int count();
}
