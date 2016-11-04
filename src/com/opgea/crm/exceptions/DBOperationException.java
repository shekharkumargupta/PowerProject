/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.exceptions;

import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class DBOperationException extends EJBException{
    
    private String message;

    public DBOperationException(String message) {
        this.message = message;
    }

    
    public String getExceptionMessage(){
        this.message = super.getMessage();
        return this.message;
    }
    
    @Override
    public String getMessage(){
        return this.message;
    }
    
    @Override
    public String toString() {
        return "DBOperationException{" + "message=" + message + '}';
    }
    
    
}
