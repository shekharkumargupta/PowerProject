/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.converter;

import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.web.dto.EmployeeDTO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@FacesConverter(value = "employeeConverter")
public class EmployeeConverter implements Converter {

    @Inject
    private EmployeeService employeeService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            //Long empId = Long.parseLong(value);
            //System.out.println("Employee Id: "+empId);
            EmployeeDTO empDTO = new EmployeeDTO();
            empDTO.setId(Long.parseLong(value));
            return empDTO;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        EmployeeDTO employeeDTO = (EmployeeDTO) value;
        return String.valueOf(employeeDTO.getId());
    }
}
