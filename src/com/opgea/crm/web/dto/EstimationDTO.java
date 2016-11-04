/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.domain.entities.Project;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public class EstimationDTO {

    private Long id;
    private String name;
    private Long clientId;
    private String clientName;
    private Long jobStatusId;
    private String jobStatusName;
    private Long itemEstimationId;
    private Long projectId;
    private String projectName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getItemEstimationId() {
        return itemEstimationId;
    }

    public void setItemEstimationId(Long itemEstimationId) {
        this.itemEstimationId = itemEstimationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Long jobStatusId) {
        this.jobStatusId = jobStatusId;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets all the values as Initial.
     */
    public void reset() {
        this.id = null;
        this.clientId = null;
        this.clientName = null;
        this.itemEstimationId = null;
        this.jobStatusId = null;
        this.jobStatusName = null;
        this.name = null;
        this.projectId = null;
        this.projectName = null;
    }

    /**
     * Copy values from Estimation to EstimationDTO;
     * @param estimation 
     */
    public void marshall(Estimation estimation) {
        this.id = estimation.getId();
        this.name = estimation.getName();

        if (estimation.getItemEstimations() != null) {
            //this.itemEstimationId = estimation.getItemEstimations();
        }
        if (estimation.getClient() != null) {
            this.clientId = estimation.getClient().getId();
            this.clientName = estimation.getClient().getClientName();
        }
        if (estimation.getJobStatus() != null) {
            this.jobStatusId = estimation.getJobStatus().getId();
            this.jobStatusName = estimation.getJobStatus().getName();
        }
        if (estimation.getProject() != null) {
            this.projectId = estimation.getProject().getId();
            this.projectName = estimation.getProject().getName();
        }
    }
    
    /**
     * Copy values from EstimationDTO to Estimation.
     * @param estimation
     * @return 
     */
    public Estimation unmarshall(Estimation estimation){
        Client client = estimation.getClient();
        List<ItemEstimation> details = estimation.getItemEstimations();
        JobStatus status = estimation.getJobStatus();
        Project project = estimation.getProject();
        if(client != null && this.clientId != null){
            client.setId(this.id);
            estimation.setClient(client);
        }
        if(details != null && this.itemEstimationId != null){
            //details.setId(this.itemEstimationId);
            //estimation.setItemEstimation(details);
        }
        if(status != null && this.jobStatusId != null){
            status.setId(this.jobStatusId);
            estimation.setJobStatus(status);
        }
        if(project != null && this.projectId != null){
            project.setId(this.projectId);
            estimation.setProject(project);
        }
        estimation.setId(this.id);
        estimation.setName(this.name);
        
        return estimation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstimationDTO other = (EstimationDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "EstimationDTO{" + "id=" + id + ", name=" + name + ", clientId=" + clientId + ", jobStatusId=" + jobStatusId + ", itemEstimationId=" + itemEstimationId + ", projectId=" + projectId + '}';
    }
}
