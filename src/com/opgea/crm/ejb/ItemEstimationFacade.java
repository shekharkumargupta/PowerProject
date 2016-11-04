/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.ejb.local.ItemEstimationFacadeLocal;
import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.domain.entities.Job;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ramesh
 */
@Stateless
public class ItemEstimationFacade extends AbstractFacade<ItemEstimation> implements ItemEstimationFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemEstimationFacade() {
        super(ItemEstimation.class);
    }

    @Override
    public List<ItemEstimation> findAllByEstimation(Long estimationId) {
        //Query query = getEntityManager().createNamedQuery("ItemEstimation.findByEstimation");
        Query query = getEntityManager()
                .createQuery("SELECT i FROM ItemEstimation i WHERE i.estimation.id = :estimationId ORDER BY i.item.name");
        query.setParameter("estimationId", estimationId);
        System.out.print(query.toString());
        return query.getResultList();
    }

    @Override
    public List<ItemEstimation> findAllByEstimationAndItem(Long estimationId, Long itemId) {
        Query query = getEntityManager().createNamedQuery("ItemEstimation.findByEstimationAndItem");
        query.setParameter("estimationId", estimationId);
        query.setParameter("itemId", itemId);
        System.out.println(this.getClass().getName()+" >> Query: "+query.toString());
        return query.getResultList();
    }

    @Override
    public List<ItemEstimation> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("ItemEstimation.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createBatch(Collection<ItemEstimation> itemEstimations) throws EJBException{
        
        for(ItemEstimation itemEstimation: itemEstimations){
            Item item = itemEstimation.getItem();
            Estimation estimation = itemEstimation.getEstimation();
            Job job = itemEstimation.getJob();
            getEntityManager().persist(itemEstimation);
            itemEstimation.setItem(item);
            itemEstimation.setEstimation(estimation);
            itemEstimation.setJob(job);
            getEntityManager().merge(itemEstimation);
        }
    }

    @Override
    public ItemEstimation findExisting(Long estimationId, Long jobId, Long itemId) {
        Query query = getEntityManager().createNamedQuery("ItemEstimation.findExisting");
        query.setParameter("estimationId", estimationId);
        query.setParameter("jobId", jobId);
        query.setParameter("itemId", itemId);
        List<ItemEstimation> itemEstimationList = query.getResultList();
        if(itemEstimationList != null && itemEstimationList.size() > 0){
            return itemEstimationList.get(0);
        }else{
            return null;
        }
                
    }

    @Override
    public List<ItemEstimation> findAllByEstimationGroupBy(Long estimationId) {
        //Query query = getEntityManager().createQuery("SELECT distinct i FROM ItemEstimation i WHERE i.estimation.id = "+estimationId+" GROUP BY i.item");
        Query query = getEntityManager().
                createNativeQuery("SELECT ID, ITEM_ID, QUANTITY, SUM(TOTALTIME) AS TOTALTIME FROM ITEMESTIMATION WHERE ESTIMATION_ID = "+estimationId+"  GROUP BY ITEM_ID", ItemEstimation.class);
        //query.setParameter("estimationId", estimationId);
        System.out.println(getClass().getName()+": findAllByEstimationGroupBy >> "+query);
        return query.getResultList();
    }
}
