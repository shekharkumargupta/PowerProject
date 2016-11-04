/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.domain.entities.Project;
import com.opgea.crm.domain.entities.Task;
import com.opgea.crm.domain.entities.embeddable.TaskId;
import com.opgea.crm.ejb.local.EmployeeFacadeLocal;
import com.opgea.crm.ejb.local.ItemEstimationFacadeLocal;
import com.opgea.crm.ejb.local.JobFacadeLocal;
import com.opgea.crm.ejb.local.JobStatusFacadeLocal;
import com.opgea.crm.ejb.local.ProjectFacadeLocal;
import com.opgea.crm.ejb.local.TaskFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.TaskService;
import com.opgea.crm.web.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Ramesh
 */
public class TaskServiceImpl implements TaskService {

    @EJB
    private TaskFacadeLocal taskFacade;
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    @EJB
    private ItemEstimationFacadeLocal itemEstimationFacade;
    @EJB
    private ProjectFacadeLocal projectFacade;
    @EJB
    private JobFacadeLocal jobFacade;
    @EJB
    private JobStatusFacadeLocal jobStatusFacade;

    @Override
    public TaskDTO create(TaskDTO taskDTO) throws DBOperationException {
        Task task = new Task();
        taskDTO.unmarshall(task);
        System.out.println("Task: "+task);
        //task.getTaskId().setProjectId(taskDTO.getTaskId().getProjectId());
        taskFacade.create(task);
        if (taskDTO.getAssignedToId() != null) {
            Employee employee = employeeFacade.find(taskDTO.getAssignedToId());
            task.setAssignedTo(employee);
        }
        if (taskDTO.getItemEstimationId() != null) {
            ItemEstimation itemEstimation =
                    itemEstimationFacade.find(taskDTO.getItemEstimationId());
            task.setItemEstimation(itemEstimation);
            
        }
        if (taskDTO.getJobId() != null) {
            Job job = jobFacade.find(taskDTO.getJobId());
            task.setJob(job);
        }
        if (taskDTO.getJobStatusId() != null) {
            JobStatus jobStatus = jobStatusFacade.find(taskDTO.getJobStatusId());
            task.setJobStatus(jobStatus);
        }
        if (taskDTO.getTaskId().getProjectId() != null) {
            Project project = projectFacade.find(taskDTO.getTaskId().getProjectId());
            task.setProject(project);
        }
        taskFacade.edit(task);
        taskDTO.setTaskId(task.getTaskId());
        return taskDTO;
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) throws DBOperationException {
        Task task = taskFacade.find(taskDTO.getTaskId());
        if (task != null) {
            //Task task = taskFacade.find(taskDTO.getTaskId());
            taskDTO.unmarshall(task);
            if (taskDTO.getAssignedToId() != null) {
                Employee employee = employeeFacade.find(taskDTO.getAssignedToId());
                task.setAssignedTo(employee);
            }
            if (taskDTO.getItemEstimationId() != null) {
                ItemEstimation itemEstimation =
                        itemEstimationFacade.find(taskDTO.getItemEstimationId());
                task.setItemEstimation(itemEstimation);
            }
            if (taskDTO.getJobId() != null) {
                Job job = jobFacade.find(taskDTO.getJobId());
                task.setJob(job);
            }
            if (taskDTO.getJobStatusId() != null) {
                JobStatus jobStatus = jobStatusFacade.find(taskDTO.getJobStatusId());
                task.setJobStatus(jobStatus);
            }
            if (taskDTO.getProjectId() != null) {
                Project project = projectFacade.find(taskDTO.getProjectId());
                task.setProject(project);
            }
            taskFacade.edit(task);
            return taskDTO;
        } else {
            return this.create(taskDTO);
        }
    }

    @Override
    public TaskDTO remove(TaskDTO taskDTO) {
        Task task = taskFacade.find(taskDTO.getTaskId());
        taskFacade.remove(task);
        taskDTO.marshall(task);
        return taskDTO;
    }

    @Override
    public TaskDTO find(Object id) {
        Task task = taskFacade.find(id);
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.marshall(task);
        return taskDTO;
    }

    @Override
    public List<TaskDTO> findAll() {
        List<Task> tasks = taskFacade.findAll();
        List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            TaskDTO dto = new TaskDTO();
            dto.marshall(task);
            taskDTOList.add(dto);
        }
        return taskDTOList;
    }


    @Override
    public List<TaskDTO> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TaskDTO> findAllByProjectId(Long projectId) {
        List<Task> tasks = taskFacade.findAllByProjectId(projectId);
        List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            TaskDTO dto = new TaskDTO();
            dto.marshall(task);
            taskDTOList.add(dto);
        }
        return taskDTOList;
    }

    @Override
    public List<TaskDTO> findAllByItemEstimationId(Long itemEstimationId) {
        List<Task> tasks = taskFacade.findAllByItemEstimationId(itemEstimationId);
        List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            TaskDTO dto = new TaskDTO();
            dto.marshall(task);
            taskDTOList.add(dto);
        }
        return taskDTOList;
    }
}
