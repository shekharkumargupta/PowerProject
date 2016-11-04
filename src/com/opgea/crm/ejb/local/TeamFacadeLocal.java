/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.ejb.local;

import com.opgea.crm.domain.entities.Team;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ramesh
 */
@Local
public interface TeamFacadeLocal {

    void create(Team team);

    void edit(Team team);

    void remove(Team team);

    Team find(Object id);

    List<Team> findAll();
    
    List<Team> findAllByCompanyId(Object companyId);

    List<Team> findRange(int[] range);

    int count();
    
}
