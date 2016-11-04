/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface JobStatusFacadeLocal {

    void create(JobStatus jobStatus) throws DBOperationException;

    void edit(JobStatus jobStatus) throws DBOperationException;

    void remove(JobStatus jobStatus);

    JobStatus find(Object id);

    List<JobStatus> findAll();

    List<JobStatus> findAllByDepartmentId(Long departmentId);

    List<JobStatus> findAllByCompanyId(Long companyId);

    List<JobStatus> findRange(int[] range);

    int count();
}
