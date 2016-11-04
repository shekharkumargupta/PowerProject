/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities.embeddable;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Ramesh
 */
@Embeddable
public class TaskId implements Serializable {
    
    private Long projectId = 0L;
    private String taskId;

    public TaskId() {
    }

    public TaskId(Long projectId, String taskId) {
        this.projectId = projectId;
        this.taskId = taskId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TaskId other = (TaskId) obj;
        if (this.projectId != other.projectId && (this.projectId == null || !this.projectId.equals(other.projectId))) {
            return false;
        }
        if (this.taskId != other.taskId && (this.taskId == null || !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.projectId != null ? this.projectId.hashCode() : 0);
        hash = 29 * hash + (this.taskId != null ? this.taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TaskId{" + "projectId=" + projectId + ", taskId=" + taskId + '}';
    }
    
}
