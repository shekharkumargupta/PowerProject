/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.ejb.local.TaskFacadeLocal;
import com.opgea.crm.domain.entities.Task;
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
public class TaskFacade extends AbstractFacade<Task> implements TaskFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }

    @Override
    public List<Task> findAllByProjectId(Long projectId) {
        Query query = getEntityManager().createNamedQuery("Task.findAllByProjectId");
        query.setParameter("projectId", projectId);
        return query.getResultList();
    }

    @Override
    public List<Task> findAllByItemEstimationId(Long itemEstimationId) {
        Query query = getEntityManager().createNamedQuery("Task.findByItemEstimationId");
        query.setParameter("itemEstimationId", itemEstimationId);
        return query.getResultList();
    }
    
}
