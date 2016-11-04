/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.CountryFacadeLocal;
import com.opgea.crm.domain.entities.Country;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ramesh
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> implements CountryFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryFacade() {
        super(Country.class);
    }
    
}
