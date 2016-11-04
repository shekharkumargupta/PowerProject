/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.LoginFacadeLocal;
import com.opgea.crm.domain.entities.Login;
import com.opgea.crm.exceptions.DBOperationException;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ramesh
 */
@Stateless
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {

    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }

    @Override
    public Login findByLoginId(String loginId) {
        Query query = getEntityManager().createNamedQuery("Login.findByLoginId");
        query.setParameter("loginId", loginId);
        Login login = null;
        try {
            login = (Login) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Could not found User Id!");
            System.out.println(ex.getLocalizedMessage());
        }
        return login;
    }
}
