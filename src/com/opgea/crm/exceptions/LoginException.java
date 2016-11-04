/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.exceptions;

import com.opgea.crm.domain.entities.Login;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class LoginException extends EJBException{
    
    private String message;
    
    public LoginException(Login login){
        if(login == null){
            this.message = "Not a valid User Id!";
        }else{
            this.message = "Password Incorrect!";
        }
        
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }

    @Override
    public String toString() {
        return "LoginException{" + "message=" + message + '}';
    }
    
}
