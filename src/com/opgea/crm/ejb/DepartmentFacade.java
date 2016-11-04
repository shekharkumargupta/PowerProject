/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.DepartmentFacadeLocal;
import com.opgea.crm.domain.entities.Department;
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
public class DepartmentFacade extends AbstractFacade<Department> implements DepartmentFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }

    @Override
    public List<Department> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("Department.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }
    
}
