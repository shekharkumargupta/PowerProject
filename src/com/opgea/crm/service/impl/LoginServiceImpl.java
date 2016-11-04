/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Login;
import com.opgea.crm.ejb.local.LoginFacadeLocal;
import com.opgea.crm.exceptions.LoginException;
import com.opgea.crm.service.LoginService;
import com.opgea.crm.web.dto.LoginDTO;
import java.util.List;
import javax.ejb.EJB;


/**
 *
 * @author Ramesh
 */
public class LoginServiceImpl implements LoginService{

    @EJB
    private LoginFacadeLocal loginFacade;
    
    @Override
    public LoginDTO create(LoginDTO loginDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO update(LoginDTO loginDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO remove(String loginId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoginDTO find(String loginId) {
        Login login = loginFacade.find(loginId);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.marshall(login);
        return loginDTO;
    }

    @Override
    public LoginDTO findByEmployeeId(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean isAuthenticUser(String loginId, String password) throws LoginException{
        Login login = loginFacade.findByLoginId(loginId);
        if(login != null && login.getPassword().equals(password)){
            return true;
        }
        else{
            throw new LoginException(login);
        }
        
    }

    @Override
    public List<LoginDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
