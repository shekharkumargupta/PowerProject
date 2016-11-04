/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.ejb.local.ProjectFacadeLocal;
import com.opgea.crm.domain.entities.Project;
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
public class ProjectFacade extends AbstractFacade<Project> implements ProjectFacadeLocal {
    @PersistenceContext(unitName = "PmsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }

    @Override
    public Project findByEstimationId(Long estimationId) {
        Query query = getEntityManager().createNamedQuery("Project.findByEstimationId");
        query.setParameter("estimationId", estimationId);
        return (Project) query.getResultList().get(0);
    }

    @Override
    public List<Project> findAllByClientId(Long clientId) {
        Query query = getEntityManager().createNamedQuery("Project.findAllByClientId");
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    @Override
    public Project defaultCreate(Long companyId, String projectName, Long estimationId, Long clientId) {
        Project project = new Project();
        Estimation estimation = getEntityManager().find(Estimation.class, estimationId);
        Company company = getEntityManager().find(Company.class, companyId);
        Client client = getEntityManager().find(Client.class, clientId);
        project.setName(projectName);
        project.setEstimation(estimation);
        project.setCompany(company);
        project.setClient(client);
        getEntityManager().persist(project);
        
        estimation.setProject(project);
        getEntityManager().merge(estimation);
        return project;
    }
}
