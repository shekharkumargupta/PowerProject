/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.JobStatusFacadeLocal;
import com.opgea.crm.domain.entities.JobStatus;
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
public class JobStatusFacade extends AbstractFacade<JobStatus> implements JobStatusFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobStatusFacade() {
        super(JobStatus.class);
    }

    @Override
    public List<JobStatus> findAllByDepartmentId(Long departmentId) {
        Query query = getEntityManager().createNamedQuery("JobStatus.findAllByDepartmentId")
                .setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    @Override
    public List<JobStatus> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("JobStatus.findAllByCompanyId")
                .setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
