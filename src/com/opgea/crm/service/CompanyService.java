/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.CompanyDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CompanyService {

    public CompanyDTO create(CompanyDTO companyDTO) throws DBOperationException;

    public CompanyDTO update(CompanyDTO companyDTO) throws DBOperationException;

    public CompanyDTO remove(Long id);

    public CompanyDTO find(Long id);

    public List<CompanyDTO> findAll();
}
