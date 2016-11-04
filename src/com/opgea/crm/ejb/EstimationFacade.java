/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.EstimationFacadeLocal;
import com.opgea.crm.domain.entities.Estimation;
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
public class EstimationFacade extends AbstractFacade<Estimation> implements EstimationFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstimationFacade() {
        super(Estimation.class);
    }

    @Override
    public List<Estimation> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("Estimation.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }

    @Override
    public List<Estimation> findAllByClientId(Long clientId) {
        Query query = getEntityManager().createNamedQuery("Estimation.findAllByClientId");
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
    
    
    
}
