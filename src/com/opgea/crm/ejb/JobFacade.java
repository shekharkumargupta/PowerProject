/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.JobFacadeLocal;
import com.opgea.crm.domain.entities.Job;
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
public class JobFacade extends AbstractFacade<Job> implements JobFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }

    @Override
    public List<Job> findAllByDepartmentId(Long departmentId) {
        Query query = getEntityManager().createNamedQuery("Job.findAllByDepartmentId")
                .setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    @Override
    public List<Job> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("Job.findAllByCompanyId")
                .setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
