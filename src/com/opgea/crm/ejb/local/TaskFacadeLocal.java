/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Task;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface TaskFacadeLocal {

    void create(Task task);

    void edit(Task task);

    void remove(Task task);

    Task find(Object id);

    List<Task> findAll();
    
    List<Task> findAllByProjectId(Long projectId);
    
    List<Task> findAllByItemEstimationId(Long itemEstimationId);

    List<Task> findRange(int[] range);

    int count();
    
}
