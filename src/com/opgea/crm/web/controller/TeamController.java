/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.service.TeamService;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name="teamController")
@SessionScoped
public class TeamController {
    
    private static final long serialVersionUID = 1L;
    @Inject
    private TeamService teamService;
    @Inject
    private EmployeeService employeeService;
    private TeamDTO teamDTO = new TeamDTO();
    private List<TeamDTO> teamDTOList = new ArrayList<TeamDTO>();
    private List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
    private List<EmployeeDTO> memberDTOList = new ArrayList<EmployeeDTO>();
    
    private Long selectedTeamId;

    public TeamController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("TeamController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        teamDTOList = teamService.findAllByCompanyId(sessionData.getCompanyId());
        employeeDTOList = employeeService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }

    public List<TeamDTO> getTeamDTOList() {
        return teamDTOList;
    }

    public void setTeamDTOList(List<TeamDTO> teamDTOList) {
        this.teamDTOList = teamDTOList;
    }

    public List<EmployeeDTO> getEmployeeDTOList() {
        return employeeDTOList;
    }

    public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }

    public Long getSelectedTeamId() {
        return selectedTeamId;
    }

    public void setSelectedTeamId(Long selectedTeamId) {
        this.selectedTeamId = selectedTeamId;
    }

    public List<EmployeeDTO> getMemberDTOList() {
        return memberDTOList;
    }

    public void setMemberDTOList(List<EmployeeDTO> memberDTOList) {
        this.memberDTOList = memberDTOList;
    }

    public String newEntry() {
        teamDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String teamId = (String) params.get("teamId");
        this.teamDTO = teamService.find(new Long(teamId));
        return "view.xhtml";
    }

    public String save() {
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        teamDTO.setCompanyId(data.getCompanyId());
        System.out.println(teamDTO);
        try {
            teamService.update(teamDTO);
            teamService.findAllByCompanyId(data.getCompanyId());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    teamDTO.getName() + " saved successfully!", null);
            teamService.findAllByCompanyId(data.getCompanyId());
        } catch (DBOperationException ex) {
            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String teamId = (String) params.get("teamId");
        this.teamDTO = teamService.find(new Long(teamId));
        return "create.xhtml";
    }

    public String delete() {
        this.teamDTO = teamService.find(getTeamDTO().getId());
        return "list.xhtml";
    }
    
    public void showTeamMembers(){
        System.out.println("Selected Team Id: "+selectedTeamId);
        this.teamDTO.setTeamLeaderId(teamService.find(selectedTeamId).getTeamLeaderId());
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        memberDTOList = teamService.findAllMembers(selectedTeamId);
        employeeDTOList = employeeService.findAllWithTeamId(sessionData.getCompanyId(), selectedTeamId);
    }
    
    public void addMembers(Long employeeId){
        teamService.addMember(selectedTeamId, employeeId);
        //memberDTOList = teamService.findAllMembers(selectedTeamId);
        showTeamMembers();
        FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    "Member added to the team: "+teamDTO.getName(), null);
    }
    
    public void removeMembers(Long employeeId){
        teamService.removeMember(selectedTeamId, employeeId);
        //memberDTOList = teamService.findAllMembers(selectedTeamId);
        showTeamMembers();
        FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    "Member removed from the team: "+teamDTO.getName(), null);
    }
    
}
