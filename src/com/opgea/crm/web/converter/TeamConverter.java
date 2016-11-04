/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.converter;

import com.opgea.crm.service.TeamService;
import com.opgea.crm.web.dto.TeamDTO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@FacesConverter(value="teamConverter")
public class TeamConverter implements Converter{

    @Inject
    private TeamService teamService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.trim().equals("")){
            return null;
        }else{
            Long teamId = Long.parseLong(value);
            TeamDTO teamDTO = teamService.find(teamId);
            return teamDTO;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TeamDTO dto = (TeamDTO) value;
        return String.valueOf(dto.getTeamLeaderId());
    }
    
}
