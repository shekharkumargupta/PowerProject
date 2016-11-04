/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.web.dto.EmployeeDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name="image")
@RequestScoped
public class ImageController {
    
    @Inject
    private EmployeeService employeeService;
    
    /**
     * Not Working method.
     */
    public void getImage(){
        HttpServletResponse response = FacesUtil.getHttpServletResponse();
        String empStringId = (String) 
                FacesUtil.getHttpServletRequest().getParameter("employeeId");
        Long employeeId = Long.parseLong(empStringId);
        
        EmployeeDTO employeeDTO = employeeService.find(employeeId);
        byte[] thumb = employeeDTO.getPicture();
 
        String name = "EmployeeImage";
        response.setContentType("image/jpeg");
        response.setContentLength(thumb.length);
 
        response.setHeader("Content-Disposition", "inline; filename=\"" + name
                + "\"");
 
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
 
        try {
            input = new BufferedInputStream(new ByteArrayInputStream(thumb));
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[8192];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println("There are errors in reading/writing image stream "
                    + e.getMessage());
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException ignore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException ignore) {
                }
        }
    }
}
