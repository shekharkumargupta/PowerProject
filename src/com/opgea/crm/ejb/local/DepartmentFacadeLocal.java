/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Department;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface DepartmentFacadeLocal {

    void create(Department department)throws DBOperationException;

    void edit(Department department)throws DBOperationException;

    void remove(Department department);

    Department find(Object id);

    List<Department> findAll();
    
    List<Department> findAllByCompanyId(Long companyId);

    List<Department> findRange(int[] range);

    int count();
    
}
