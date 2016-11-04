/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Designation;
import com.opgea.crm.exceptions.DBOperationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface DesignationFacadeLocal {

    void create(Designation designation) throws DBOperationException;

    void edit(Designation designation) throws DBOperationException;

    void remove(Designation designation);

    Designation find(Object id);

    List<Designation> findAll();

    List<Designation> findAllByDepartmentId(Long departmentId);

    List<Designation> findAllByCompanyId(Long companyId);

    List<Designation> findRange(int[] range);

    int count();
}
