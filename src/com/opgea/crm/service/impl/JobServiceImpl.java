/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.ejb.local.DepartmentFacadeLocal;
import com.opgea.crm.ejb.local.JobFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.JobService;
import com.opgea.crm.web.dto.JobDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class JobServiceImpl implements JobService {

    @EJB
    private JobFacadeLocal jobFacade;
    @EJB
    private DepartmentFacadeLocal departmentFacade;

    @Override
    public JobDTO create(JobDTO jobDTO) throws DBOperationException {
        Job job = new Job();
        Department department = departmentFacade.find(jobDTO.getDepartmentId());
        jobDTO.unmarshall(job);
        try {
            jobFacade.create(job);
            job.setDepartment(department);
            jobFacade.edit(job);
            jobDTO.setId(job.getId());
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getMessage());
        }
        return jobDTO;
    }

    @Override
    public JobDTO update(JobDTO jobDTO) throws DBOperationException {
        if (jobDTO.getId() != null && jobDTO.getId() > 0) {
            Job job = jobFacade.find(jobDTO.getId());
            Department department = departmentFacade.find(jobDTO.getDepartmentId());
            jobDTO.unmarshall(job);
            job.setDepartment(department);
            jobFacade.edit(job);
        } else {
            this.create(jobDTO);
        }
        return jobDTO;
    }

    @Override
    public JobDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JobDTO find(Long id) {
        Job job = jobFacade.find(id);
        JobDTO jobDTO = new JobDTO();
        jobDTO.marshall(job);
        return jobDTO;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobList = jobFacade.findAll();
        List<JobDTO> jobDTOs = new ArrayList<JobDTO>();
        for (Job job : jobList) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.marshall(job);
            jobDTOs.add(jobDTO);
        }
        return jobDTOs;
    }

    @Override
    public List<JobDTO> findAllByDepartmentId(Long departmentId) {
        List<Job> jobList = jobFacade.findAllByDepartmentId(departmentId);
        List<JobDTO> jobDTOs = new ArrayList<JobDTO>();
        for (Job job : jobList) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.marshall(job);
            jobDTOs.add(jobDTO);
        }
        return jobDTOs;
    }

    @Override
    public List<JobDTO> findAllByCompanyId(Long companyId) {
        List<Job> jobList = jobFacade.findAllByCompanyId(companyId);
        List<JobDTO> jobDTOs = new ArrayList<JobDTO>();
        for (Job job : jobList) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.marshall(job);
            jobDTOs.add(jobDTO);
        }
        return jobDTOs;
    }
}
