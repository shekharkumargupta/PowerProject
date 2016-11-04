/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.EmployeeDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface EmployeeDetailsFacadeLocal {

    void create(EmployeeDetails employeeDetails);

    void edit(EmployeeDetails employeeDetails);

    void remove(EmployeeDetails employeeDetails);

    EmployeeDetails find(Object id);

    List<EmployeeDetails> findAll();

    List<EmployeeDetails> findRange(int[] range);

    int count();
    
}
