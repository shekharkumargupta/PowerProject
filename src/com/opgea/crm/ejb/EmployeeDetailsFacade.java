/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.EmployeeDetailsFacadeLocal;
import com.opgea.crm.domain.entities.EmployeeDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ramesh
 */
@Stateless
public class EmployeeDetailsFacade extends AbstractFacade<EmployeeDetails> implements EmployeeDetailsFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeDetailsFacade() {
        super(EmployeeDetails.class);
    }
    
}
