/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface JobFacadeLocal {

    void create(Job job)throws DBOperationException;

    void edit(Job job)throws DBOperationException;

    void remove(Job job);

    Job find(Object id);

    List<Job> findAll();
    
    List<Job> findAllByDepartmentId(Long departmentId);
    
    List<Job> findAllByCompanyId(Long companyId);

    List<Job> findRange(int[] range);

    int count();
    
}
