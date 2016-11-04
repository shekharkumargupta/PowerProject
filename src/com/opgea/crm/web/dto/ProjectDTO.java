/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Project;

/**
 *
 * @author Ramesh
 */
public class ProjectDTO {

    private Long id;
    private String name;
    private Long estimationId;
    private String estimationName;
    private Long clientId;
    private String clientName;
    private Long companyId;
    private String companyName;
    private Long jobStatusId;
    private String jobStatusName;
    private Long teamId;
    private String teamName;
    private Long durationValue;
    private String durationType;

    public Long getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(Long estimationId) {
        this.estimationId = estimationId;
    }

    public String getEstimationName() {
        return estimationName;
    }

    public void setEstimationName(String estimationName) {
        this.estimationName = estimationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    
    public void reset() {
        this.id = null;
        this.name = null;
        this.estimationId = null;
        this.estimationName = null;
        this.clientId = null;
        this.clientName = null;
        this.companyId = null;
        this.companyName = null;
        this.jobStatusId = null;
        this.jobStatusName = null;
        this.teamId = null;
        this.teamName = null;
    }

    /**
     * Copy values from Project to ProjectDTO.
     * @param project 
     */
    public void marshall(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        if (project.getEstimation() != null) {
            this.estimationId = project.getEstimation().getId();
            this.estimationName = project.getEstimation().getName();
        }
        if(project.getClient() != null){
            this.clientId = project.getClient().getId();
            this.clientName = project.getClient().getClientName();
        }
        if(project.getCompany() != null){
            this.companyId = project.getCompany().getId();
            this.companyName = project.getCompany().getName();
        }
        if(project.getJobStatus() != null){
            this.jobStatusId = project.getJobStatus().getId();
            this.jobStatusName = project.getJobStatus().getName();
        }
        if(project.getTeam() != null){
            this.teamId = project.getTeam().getId();
            this.teamName = project.getTeam().getName();
        }
    }
    
    /**
     * Copy values from ProjectDTO to Project.
     * @param project
     * @return 
     */
    public Project unmarshall(Project project){
        project.setId(this.getId());
        project.setName(this.getName());
        if (this.estimationId != null && project.getEstimation() != null) {
            project.getEstimation().setName(this.estimationName);
            project.getEstimation().setId(this.estimationId);
        }
        if(this.clientId != null && project.getClient() != null){
           project.getClient().setId(this.clientId);
           project.getClient().setClientName(this.clientName);
        }
        if(this.jobStatusId != null && project.getJobStatus() != null){
            project.getJobStatus().setId(this.getJobStatusId());
            project.getJobStatus().setName(this.jobStatusName);
        }
        if(this.teamId != null && project.getTeam() != null){
            project.getTeam().setId(this.getTeamId());
            project.getTeam().setName(this.getTeamName());
        }
        return project;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjectDTO other = (ProjectDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "id=" + id + ", name=" + name + ", estimationId=" + estimationId + ", estimationName=" + estimationName + ", clientId=" + clientId + ", clientName=" + clientName + ", companyId=" + companyId + ", companyName=" + companyName + ", jobStatusId=" + jobStatusId + ", jobStatusName=" + jobStatusName + '}';
    }

    
}
