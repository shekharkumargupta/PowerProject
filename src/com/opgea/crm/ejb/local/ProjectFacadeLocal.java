/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Project;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface ProjectFacadeLocal {

    void create(Project project) throws DBOperationException;

    Project defaultCreate(Long companyId, String projectName,
            Long estimationId, Long clientId) throws DBOperationException;

    void edit(Project project) throws DBOperationException;

    void remove(Project project);

    Project find(Object id);

    List<Project> findAll();

    Project findByEstimationId(Long estimationId);

    List<Project> findAllByClientId(Long clientId);

    List<Project> findRange(int[] range);

    int count();
}
