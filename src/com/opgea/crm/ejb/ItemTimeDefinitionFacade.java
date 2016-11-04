/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.ejb.local.ItemTimeDefinitionFacadeLocal;
import com.opgea.crm.domain.entities.ItemTimeDefinition;
import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.domain.entities.qualifier.DurationType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ramesh
 */
@Stateless
public class ItemTimeDefinitionFacade extends AbstractFacade<ItemTimeDefinition> implements ItemTimeDefinitionFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemTimeDefinitionFacade() {
        super(ItemTimeDefinition.class);
    }

    @Override
    public List<ItemTimeDefinition> findAllByItemId(Long itemId) {
        Query query = getEntityManager().createNamedQuery("ItemTimeDefinition.findAllByItemId")
                .setParameter("itemId", itemId);
        return query.getResultList();
    }

    @Override
    public List<ItemTimeDefinition> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("ItemTimeDefinition.findAllByCompanyId")
                .setParameter("companyId", companyId);
        return query.getResultList();
    }
    
    @Override
    public ItemTimeDefinition findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ItemTimeDefinition> findAllByCompanyAndItemId(Long companyId, Long itemIdToSearch) {
        
        /*
        Query query = getEntityManager()
                .createQuery("SELECT t FROM Item i JOIN i.itemTimeDefinition t", ItemTimeDefinition.class);
        */
        
        String queryString = "SELECT t.`ID`, j.`ID`, j.`NAME`, i.`ID`, i.`NAME`, t.`DURATIONVALUE`, t.`DURATIONTYPE` FROM JOB j"
                + " LEFT JOIN ITEM i on i.`ID` = "+itemIdToSearch
                + " LEFT JOIN ITEMTIMEDEFINITION t"
                + " on j.`ID` = t.`JOB_ID` AND i.`ID` = t.`ITEM_ID` GROUP BY j.`ID`";
        Query query = getEntityManager().createNativeQuery(queryString);
        System.out.println("queryString: "+queryString);
        
        List<Object[]> objectList = query.getResultList();
        List<ItemTimeDefinition> timeList = new ArrayList<ItemTimeDefinition>();
        ItemTimeDefinition time = null;
        
        for(int i = 0; i < objectList.size(); i++){
            Long timeId = (Long) objectList.get(i)[0];
            Long jobId = (Long) objectList.get(i)[1];
            String jobName = (String) objectList.get(i)[2];
            //String itemId = (String) objectList.get(i)[3];
            //String itemName = (String) objectList.get(i)[4];
            Long durationValue = (Long) objectList.get(i)[5];
            Integer durationType = (Integer) objectList.get(i)[6];
            
            Job job = getEntityManager().find(Job.class, new Long(jobId));
            Item item = getEntityManager().find(Item.class, itemIdToSearch);
            if(timeId != null){
                time = this.find(timeId);
            }else{
                time = new ItemTimeDefinition();
            }
            time.setJob(job);
            time.setItem(item);
            if (durationValue != null) {
                time.setDurationValue(new Long(durationValue));
                time.setDurationType(DurationType.values()[durationType]);
            }
            System.out.println("Time: "+time);
            timeList.add(time);
        }
        return timeList;
    }

    
    
}
