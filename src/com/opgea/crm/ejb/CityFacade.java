/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.CityFacadeLocal;
import com.opgea.crm.domain.entities.City;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ramesh
 */
@Stateless
public class CityFacade extends AbstractFacade<City> implements CityFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CityFacade() {
        super(City.class);
    }
    
}
