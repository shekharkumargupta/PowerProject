/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities;

import com.opgea.crm.domain.entities.embeddable.TaskId;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.taskId = :taskId"),
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByItemEstimationId", query = "SELECT t FROM Task t WHERE t.itemEstimation.id = :itemEstimationId"),
    @NamedQuery(name = "Task.findAllByProjectId", query = "SELECT t FROM Task t WHERE t.project.id = :projectId")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TaskId taskId;
    private String name;
    private String description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startedAt;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date completedAt;
    private Long assignedHrs;
    private Long takenHrs;
    @OneToOne
    private ItemEstimation itemEstimation;
    @OneToOne
    private Job job;
    @OneToOne
    private JobStatus jobStatus;
    @OneToOne
    private Employee assignedTo;
    @ManyToOne
    private Project project;

    public Long getAssignedHrs() {
        return assignedHrs;
    }

    public void setAssignedHrs(Long assignedHrs) {
        this.assignedHrs = assignedHrs;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public TaskId getTaskId() {
        return taskId;
    }

    public void setTaskId(TaskId taskId) {
        this.taskId = taskId;
    }

    public ItemEstimation getItemEstimation() {
        return itemEstimation;
    }

    public void setItemEstimation(ItemEstimation itemEstimation) {
        this.itemEstimation = itemEstimation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (this.taskId != other.taskId && (this.taskId == null || !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.taskId != null ? this.taskId.hashCode() : 0);
        return hash;
    }

  
}
