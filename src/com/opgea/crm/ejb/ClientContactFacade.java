/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.ClientContactFacadeLocal;
import com.opgea.crm.domain.entities.ClientContact;
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
public class ClientContactFacade extends AbstractFacade<ClientContact> implements ClientContactFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientContactFacade() {
        super(ClientContact.class);
    }

    @Override
    public List<ClientContact> findByClientId(Long clientId) {
        Query query = getEntityManager().createNamedQuery("ClientContact.findAllByClientId")
                .setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public List<ClientContact> findByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("ClientContact.findAllByCompanyId")
                .setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
