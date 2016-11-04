/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.DesignationFacadeLocal;
import com.opgea.crm.domain.entities.Designation;
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
public class DesignationFacade extends AbstractFacade<Designation> implements DesignationFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DesignationFacade() {
        super(Designation.class);
    }

    @Override
    public List<Designation> findAllByDepartmentId(Long departmentId) {
        Query query = getEntityManager().createNamedQuery("Designation.findAllByDepartmentId")
                .setParameter("departmentId", departmentId);
        System.out.println("Query: "+query.toString());
        return query.getResultList();
    }

    @Override
    public List<Designation> findAllByCompanyId(Long companyId) {
        Query query = getEntityManager().createNamedQuery("Designation.findAllByCompapnyId")
                .setParameter("companyId", companyId);
        System.out.println("Query: "+query.toString());
        return query.getResultList();
    }
    
}
