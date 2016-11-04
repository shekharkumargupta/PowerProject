/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.TeamFacadeLocal;
import com.opgea.crm.domain.entities.Team;
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
public class TeamFacade extends AbstractFacade<Team> implements TeamFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamFacade() {
        super(Team.class);
    }

    @Override
    public List<Team> findAllByCompanyId(Object companyId) {
        Query query = getEntityManager().createNamedQuery("Team.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
