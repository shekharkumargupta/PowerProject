/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Employee;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface EmployeeFacadeLocal {

    void create(Employee employee) throws DBOperationException;

    void edit(Employee employee) throws DBOperationException;

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findAllByCompanyId(Long companyId);

    List<Employee> findRange(int[] range);

    int count();
}
