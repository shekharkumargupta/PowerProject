/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.JobStatusDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface JobStatusService {

    public JobStatusDTO create(JobStatusDTO jobStatusDTO) throws DBOperationException;

    public JobStatusDTO update(JobStatusDTO jobStatusDTO) throws DBOperationException;

    public JobStatusDTO remove(Long id);

    public JobStatusDTO find(Long id);

    public List<JobStatusDTO> findAll();

    public List<JobStatusDTO> findAllByDepartmentId(Long departmentId);

    public List<JobStatusDTO> findAllByCompanyId(Long companyId);
}
