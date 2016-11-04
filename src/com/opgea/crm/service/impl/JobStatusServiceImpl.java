/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.ejb.local.DepartmentFacadeLocal;
import com.opgea.crm.ejb.local.JobStatusFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.JobStatusService;
import com.opgea.crm.web.dto.JobStatusDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class JobStatusServiceImpl implements JobStatusService {

    @EJB
    private JobStatusFacadeLocal jobStatusFacade;
    @EJB
    private DepartmentFacadeLocal departmentFacade;

    @Override
    public JobStatusDTO create(JobStatusDTO jobStatusDTO) throws DBOperationException {
        try{
        JobStatus jobStatus = new JobStatus();
        Department department = departmentFacade.find(jobStatusDTO.getDepartmentId());
        jobStatusDTO.unmarshall(jobStatus);
        jobStatusFacade.create(jobStatus);
        jobStatus.setDepartment(department);
        jobStatusFacade.edit(jobStatus);
        jobStatusDTO.setId(jobStatus.getId());
        }catch(EJBException ex){
            throw new DBOperationException(ex.getMessage());
        }
        return jobStatusDTO;
    }

    @Override
    public JobStatusDTO update(JobStatusDTO jobStatusDTO) throws DBOperationException {
        if (jobStatusDTO.getId() != null && jobStatusDTO.getId() > 0) {
            JobStatus jobStatus = jobStatusFacade.find(jobStatusDTO.getId());
            Department department = departmentFacade.find(jobStatusDTO.getDepartmentId());
            jobStatusDTO.unmarshall(jobStatus);
            jobStatus.setDepartment(department);
            jobStatusFacade.edit(jobStatus);
            jobStatusDTO.setId(jobStatus.getId());
        } else {
            this.create(jobStatusDTO);
        }
        return jobStatusDTO;
    }

    @Override
    public JobStatusDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JobStatusDTO find(Long id) {
        JobStatus jobStatus = jobStatusFacade.find(id);
        JobStatusDTO jobStatusDTO = new JobStatusDTO();
        jobStatusDTO.marshall(jobStatus);
        return jobStatusDTO;
    }

    @Override
    public List<JobStatusDTO> findAll() {
        List<JobStatus> jobList = jobStatusFacade.findAll();
        List<JobStatusDTO> jobDTOs = new ArrayList<JobStatusDTO>();
        for (JobStatus jobStatus : jobList) {
            JobStatusDTO jobStatusDTO = new JobStatusDTO();
            jobStatusDTO.marshall(jobStatus);
            jobDTOs.add(jobStatusDTO);
        }
        return jobDTOs;
    }

    @Override
    public List<JobStatusDTO> findAllByDepartmentId(Long departmentId) {
        List<JobStatus> jobList = jobStatusFacade.findAllByDepartmentId(departmentId);
        List<JobStatusDTO> jobDTOs = new ArrayList<JobStatusDTO>();
        for (JobStatus jobStatus : jobList) {
            JobStatusDTO jobStatusDTO = new JobStatusDTO();
            jobStatusDTO.marshall(jobStatus);
            jobDTOs.add(jobStatusDTO);
        }
        return jobDTOs;
    }

    @Override
    public List<JobStatusDTO> findAllByCompanyId(Long companyId) {
        List<JobStatus> jobList = jobStatusFacade.findAllByCompanyId(companyId);
        List<JobStatusDTO> jobDTOs = new ArrayList<JobStatusDTO>();
        for (JobStatus jobStatus : jobList) {
            JobStatusDTO jobStatusDTO = new JobStatusDTO();
            jobStatusDTO.marshall(jobStatus);
            jobDTOs.add(jobStatusDTO);
        }
        return jobDTOs;
    }
}
