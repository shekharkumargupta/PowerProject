/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User1
 */
public class TestApp {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PmsPU");
    private EntityManager em;

    public List<City> find() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        String query = " SELECT c FROM City c ";
        return em.createQuery(query, City.class).getResultList();
    }

    public static void main(String args[]) {

        TestApp ta = new TestApp();
        List<City> cities = ta.find();
        for (City city : cities) {
            System.out.println(city);
        }
        System.out.println("SHEKHAR");
    }
}
