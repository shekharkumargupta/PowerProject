/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.domain.entities.Task;
import com.opgea.crm.domain.entities.embeddable.TaskId;
import java.util.Date;

/**
 *
 * @author Ramesh
 */
public class TaskDTO {

    private TaskId taskId = new TaskId();
    private String name;
    private String description;
    private Date startedAt;
    private Date completedAt;
    private Long assignedHrs;
    private Long takenHrs;
    private Long itemEstimationId;
    private String itemEstimationName;
    private Long assignedToId;
    private String assignedToName;
    private Long jobId;
    private String jobName;
    private Long jobStatusId;
    private String jobStatusName;
    private Long projectId;
    private String projectName;

    public Long getAssignedHrs() {
        return assignedHrs;
    }

    public void setAssignedHrs(Long assignedHrs) {
        this.assignedHrs = assignedHrs;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public String getAssignedToName() {
        return assignedToName;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskId taskId) {
        this.taskId = taskId;
    }

    public Long getItemEstimationId() {
        return itemEstimationId;
    }

    public void setItemEstimationId(Long itemEstimationId) {
        this.itemEstimationId = itemEstimationId;
    }

    public String getItemEstimationName() {
        return itemEstimationName;
    }

    public void setItemEstimationName(String itemEstimationName) {
        this.itemEstimationName = itemEstimationName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Long jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    public String getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Long getTakenHrs() {
        return takenHrs;
    }

    public void setTakenHrs(Long takenHrs) {
        this.takenHrs = takenHrs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskDTO other = (TaskDTO) obj;
        if (this.taskId != other.taskId && (this.taskId == null || !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    /**
     * Set values as initial.
     */
    public void reset() {
        this.taskId = null;
        this.name = null;
        this.description = null;
        this.assignedToId = null;
        this.assignedToName = null;
        this.startedAt = null;
        this.completedAt = null;
        this.takenHrs = null;

        this.itemEstimationId = null;
        this.itemEstimationName = null;
        this.jobId = null;
        this.jobName = null;
        this.jobStatusId = null;
        this.jobStatusName = null;
        this.projectId = null;
        this.projectName = null;
    }

    /**
     * Copy values from Task to TaskDTO.
     * @param Task 
     */
    public void marshall(Task task) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.startedAt = task.getStartedAt();
        this.completedAt = task.getCompletedAt();
        this.takenHrs = task.getTakenHrs();
        if (task.getTaskId() != null && task.getTaskId().getTaskId() != null
                && task.getTaskId().getProjectId() != null) {
            //this.taskId = task.getTaskId();
            this.taskId.setTaskId(task.getTaskId().getTaskId());
            this.taskId.setProjectId(task.getTaskId().getProjectId());
        }
        if (task != null && task.getAssignedTo() != null) {
            this.assignedToId = task.getAssignedTo().getId();
            this.assignedToName = task.getAssignedTo().getFirstName()
                    + task.getAssignedTo().getMiddleInitial()
                    + task.getAssignedTo().getLastName();
        }
        if (task != null && task.getItemEstimation() != null) {
            this.itemEstimationId = task.getItemEstimation().getItem().getId();
            this.itemEstimationName = task.getItemEstimation().getItem().getName();
        }
        if (task != null && task.getProject() != null) {
            this.projectId = task.getProject().getId();
            this.projectName = task.getProject().getName();
        }
        if (task != null && task.getJob() != null) {
            this.jobId = task.getJob().getId();
            this.jobName = task.getJob().getName();
        }
        if (task != null && task.getJobStatus() != null) {
            this.jobStatusId = task.getJobStatus().getId();
            this.jobStatusName = task.getJobStatus().getName();
        }
    }

    /**
     * Copy values from TaskDTO to Task.
     * @param task 
     */
    public void unmarshall(Task task) {
        System.out.println("TaskId: " + this.taskId);
        TaskId id = new TaskId(this.taskId.getProjectId(), this.taskId.getTaskId());
        task.setTaskId(id);
        task.setName(this.name);
        task.setDescription(this.description);
        task.setStartedAt(this.startedAt);
        task.setCompletedAt(this.completedAt);
        task.setTakenHrs(this.takenHrs);
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.taskId != null ? this.taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TaskDTO{" + "taskId=" + taskId + ", name=" + name + ", description=" + description + ", startedAt=" + startedAt + ", completedAt=" + completedAt + ", assignedHrs=" + assignedHrs + ", takenHrs=" + takenHrs + ", itemEstimationId=" + itemEstimationId + ", itemEstimationName=" + itemEstimationName + ", assignedToId=" + assignedToId + ", assignedToName=" + assignedToName + ", jobStatusId=" + jobStatusId + ", jobStatusName=" + jobStatusName + ", projectId=" + projectId + ", projectName=" + projectName + '}';
    }
}
