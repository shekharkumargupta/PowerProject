/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.dto;

import com.opgea.crm.domain.entities.Team;

/**
 *
 * @author Ramesh
 */
public class TeamDTO {

    private Long id;
    private String name;
    private Long companyId;
    private String companyName;
    private Long teamLeaderId;
    private String teamLeaderName;

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

    public Long getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(Long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    /**
     * Set values as initial.
     */
    public void reset() {
        this.id = null;
        this.name = null;
        this.companyId = null;
        this.companyName = null;
        this.teamLeaderId = null;
        this.teamLeaderName = null;
    }

    /**
     * Copy values from Team to TeamDTO.
     * @param team 
     */
    public void marshall(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        if (team.getCompany() != null) {
            this.companyId = team.getCompany().getId();
            this.companyName = team.getCompany().getName();
        }
        if (team.getLeader() != null) {
            this.teamLeaderId = team.getLeader().getId();
            this.teamLeaderName = (team.getLeader().getFirstName()
                    + " " + team.getLeader().getMiddleInitial()
                    + " " + team.getLeader().getLastName()).trim();
        }
    }

    /**
     * Copy values from TeamDTO to Team.
     * @param team 
     */
    public void unmarshall(Team team) {
        team.setId(this.id);
        team.setName(this.name);
        if (this.companyId != null && team.getCompany() != null) {
            team.getCompany().setId(this.companyId);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeamDTO other = (TeamDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TeamDTO{" + "id=" + id + ", name=" + name + ", companyId=" + companyId + ", companyName=" + companyName + ", teamLeaderId=" + teamLeaderId + ", teamLeaderName=" + teamLeaderName + '}';
    }
}
