/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.converter;

import com.opgea.crm.service.ItemEstimationService;
import com.opgea.crm.web.dto.ItemEstimationDTO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@FacesConverter(value="itemEstimationConverter")
public class ItemEstimationConverter implements Converter{

    @Inject
    ItemEstimationService itemEstimationService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.trim().equals("")){
            return null;
        }else{
            Long itemEstimationId = Long.parseLong(value);
            ItemEstimationDTO dto = itemEstimationService.find(itemEstimationId);
            return dto;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ItemEstimationDTO dto = (ItemEstimationDTO) value;
        return String.valueOf(dto.getId());
    }
    
    
}
