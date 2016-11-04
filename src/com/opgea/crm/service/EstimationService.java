/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.EstimationDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface EstimationService {

    public EstimationDTO create(EstimationDTO estimationDTO) throws DBOperationException;

    public EstimationDTO update(EstimationDTO estimationDTO) throws DBOperationException;

    public EstimationDTO remove(Long id);

    public EstimationDTO find(Long id);

    public List<EstimationDTO> findAll();

    public List<EstimationDTO> findAllByCompanyId(Long companyId);

    public List<EstimationDTO> findAllByClientId(Long clientId);
}
