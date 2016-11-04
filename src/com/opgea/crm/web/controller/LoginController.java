/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.LoginException;
import com.opgea.crm.service.CompanyService;
import com.opgea.crm.service.EmployeeService;
import com.opgea.crm.service.LoginService;
import com.opgea.crm.web.dto.CompanyDTO;
import com.opgea.crm.web.dto.EmployeeDTO;
import com.opgea.crm.web.dto.LoginDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController {

    @Inject
    private LoginService loginService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private CompanyService companyService;
    private LoginDTO loginDTO = new LoginDTO();

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public String validateUser() {
        String outcome = "login.xhtml";
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            boolean isValid = loginService.isAuthenticUser(loginDTO.getLoginId(), loginDTO.getPassword());
            HttpSession session = FacesUtil.getHttpSession();
            if (isValid) {
                LoginDTO loginDetails = loginService.find(loginDTO.getLoginId());
                EmployeeDTO employeeDetails = employeeService.find(loginDetails.getEmployeeId());
                CompanyDTO companyDetails = companyService.find(employeeDetails.getCompanyId());

                SessionData sessionData = new SessionData();
                sessionData.setLoginId(loginDetails.getLoginId());
                sessionData.setCompanyId(companyDetails.getId());
                sessionData.setCompanyName(companyDetails.getName());
                sessionData.setEmployeeId(employeeDetails.getId());
                sessionData.setEmployeeName(employeeDetails.getFirstName() + " "
                        + employeeDetails.getLastName());
                sessionData.setEmployeeDTO(employeeDetails);
                sessionData.setThemeName(ApplicationConstants.DEFAULT_THEME);
                session.setAttribute(SessionConstants.SESSION_DATA, sessionData);
                session.setAttribute(SessionConstants.COMPANY_ID, companyDetails.getId());
                System.out.println("Session Data: " + sessionData);
                outcome = "/company/create.xhtml";
            }
        } catch (LoginException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.INFO, ex.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ex.getMessage(), null);
            context.addMessage("messages", message);
            outcome = "login.xhtml";
        } catch (EJBException ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Application Exception", null);
            context.addMessage(null, message);
            outcome = "login.xhtml";
        }
        return outcome;
    }
    
    public String logout(){
        HttpSession session = (HttpSession) 
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/login/login.xhtml";
    }
}
