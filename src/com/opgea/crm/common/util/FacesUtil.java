/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.common.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ramesh
 */
public class FacesUtil {

    public static HttpSession getHttpSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpSession) context.getSession(true);
    }

    public static void addMessage(String messageId,
            FacesMessage.Severity severity,
            String summaryMessage,
            String detailedMessage) {
        FacesMessage facesMessage = new FacesMessage(severity,
                summaryMessage, detailedMessage);
        FacesContext.getCurrentInstance().addMessage(messageId, facesMessage);
    }
    
    public static HttpServletRequest getHttpServletRequest(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletRequest) context.getRequest();
    }
    
    public static HttpServletResponse getHttpServletResponse(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletResponse) context.getResponse();
    }
}
