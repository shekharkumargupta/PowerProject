/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.LoginException;
import com.opgea.crm.web.dto.LoginDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface LoginService {

    public LoginDTO create(LoginDTO loginDTO);

    public LoginDTO update(LoginDTO loginDTO);

    public LoginDTO remove(String loginId);

    public LoginDTO find(String loginId);

    public LoginDTO findByEmployeeId(Long employeeId);

    public Boolean isAuthenticUser(String loginId, String password) throws LoginException;

    public List<LoginDTO> findAll();
}
