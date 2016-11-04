/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.ItemFacadeLocal;
import com.opgea.crm.domain.entities.Item;
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
public class ItemFacade extends AbstractFacade<Item> implements ItemFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    @Override
    public List<Item> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("Item.findAllByCompanyId")
                .setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
