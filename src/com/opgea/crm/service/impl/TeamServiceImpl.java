/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.domain.entities.Team;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.EmployeeFacadeLocal;
import com.opgea.crm.ejb.local.TeamFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.TeamService;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Ramesh
 */
public class TeamServiceImpl implements TeamService {

    @EJB
    private TeamFacadeLocal teamFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;
    @EJB
    private EmployeeFacadeLocal employeeFacade;

    @Override
    public TeamDTO create(TeamDTO teamDTO) throws DBOperationException {
        Company company = companyFacade.find(teamDTO.getCompanyId());
        Employee employee = employeeFacade.find(teamDTO.getTeamLeaderId());
        Team team = new Team();
        teamDTO.unmarshall(team);
        teamFacade.create(team);
        team.setCompany(company);
        team.setLeader(employee);
        team.addEmployee(employee);
        teamFacade.edit(team);
        teamDTO.setId(team.getId());
        return teamDTO;
    }

    @Override
    public TeamDTO update(TeamDTO teamDTO) throws DBOperationException {
        if (teamDTO.getId() != null && teamDTO.getId() > 0) {
            Team team = teamFacade.find(teamDTO.getId());
            if (teamDTO.getTeamLeaderId() != null) {
                Employee leader = employeeFacade.find(teamDTO.getTeamLeaderId());
                team.setLeader(leader);
            }
            teamDTO.unmarshall(team);
            teamFacade.edit(team);
            return teamDTO;
        } else {
            return this.create(teamDTO);
        }
    }

    @Override
    public TeamDTO remove(TeamDTO teamDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TeamDTO find(Object id) {
        Team team = teamFacade.find(id);
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.marshall(team);
        return teamDTO;
    }

    @Override
    public List<TeamDTO> findAll() {
        List<Team> teams = teamFacade.findAll();
        List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
        for (Team team : teams) {
            TeamDTO dto = new TeamDTO();
            dto.marshall(team);
            teamDTOList.add(dto);
        }
        return teamDTOList;
    }

    @Override
    public List<TeamDTO> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmployeeDTO> findAllMembers(Long teamId) {
        Team team = teamFacade.find(teamId);
        List<Employee> members = team.getEmployees();
        List<EmployeeDTO> memberDTOList = new ArrayList<EmployeeDTO>();
        for (Employee member : members) {
            EmployeeDTO dto = new EmployeeDTO();
            dto.marshall(member);
            memberDTOList.add(dto);
        }
        return memberDTOList;
    }

    @Override
    public void addMember(Long teamId, Long employeeId) throws DBOperationException {
        Team team = teamFacade.find(teamId);
        Employee member = employeeFacade.find(employeeId);
        team.addEmployee(member);
        teamFacade.edit(team);
        member.addTeam(team);
        employeeFacade.edit(member);
    }
    
    @Override
    public void removeMember(Long teamId, Long employeeId) throws DBOperationException {
        Team team = teamFacade.find(teamId);
        Employee member = employeeFacade.find(employeeId);
        team.getEmployees().remove(member);
        teamFacade.edit(team);
        member.addTeam(team);
        employeeFacade.edit(member);
    }

    @Override
    public List<TeamDTO> findAllByCompanyId(Object companyId) {
        List<Team> teams = teamFacade.findAllByCompanyId(companyId);
        List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
        for (Team team : teams) {
            TeamDTO dto = new TeamDTO();
            dto.marshall(team);
            teamDTOList.add(dto);
        }
        return teamDTOList;
    }
}
