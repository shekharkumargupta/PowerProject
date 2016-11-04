/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.JobDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface JobService {

    public JobDTO create(JobDTO jobDTO) throws DBOperationException;

    public JobDTO update(JobDTO jobDTO) throws DBOperationException;

    public JobDTO remove(Long id);

    public JobDTO find(Long id);

    public List<JobDTO> findAll();

    public List<JobDTO> findAllByDepartmentId(Long departmentId);

    public List<JobDTO> findAllByCompanyId(Long companyId);
}
