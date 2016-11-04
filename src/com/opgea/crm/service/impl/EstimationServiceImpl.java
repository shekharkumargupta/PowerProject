/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.domain.entities.JobStatus;
import com.opgea.crm.ejb.local.EstimationFacadeLocal;
import com.opgea.crm.ejb.local.ClientFacadeLocal;
import com.opgea.crm.ejb.local.JobStatusFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.EstimationService;
import com.opgea.crm.web.dto.EstimationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Ramesh
 */
public class EstimationServiceImpl implements EstimationService {

    @EJB
    private EstimationFacadeLocal estimationFacade;
    @EJB
    private ClientFacadeLocal clientFacade;
    @EJB
    private JobStatusFacadeLocal jobStatusFacade;

    @Override
    public EstimationDTO create(EstimationDTO estimationDTO) throws DBOperationException {
        Estimation estimation = new Estimation();
        estimationDTO.unmarshall(estimation);
        estimationFacade.create(estimation);

        Client client = clientFacade.find(estimationDTO.getClientId());
        JobStatus status = jobStatusFacade.find(estimationDTO.getJobStatusId());
        estimation.setClient(client);
        estimation.setJobStatus(status);
        estimationFacade.edit(estimation);
        estimationDTO.setId(estimation.getId());
        return estimationDTO;
    }

    @Override
    public EstimationDTO update(EstimationDTO estimationDTO) throws DBOperationException {
        Estimation estimation = estimationFacade.find(estimationDTO.getId());
        estimationDTO.unmarshall(estimation);

        Client client = clientFacade.find(estimationDTO.getClientId());
        JobStatus status = jobStatusFacade.find(estimationDTO.getJobStatusId());
        estimation.setClient(client);
        estimation.setJobStatus(status);
        estimationFacade.edit(estimation);
        estimationDTO.setId(estimation.getId());
        return estimationDTO;
    }

    @Override
    public EstimationDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EstimationDTO find(Long id) {
        Estimation estimation = estimationFacade.find(id);
        EstimationDTO estimationDTO = new EstimationDTO();
        estimationDTO.marshall(estimation);
        return estimationDTO;
    }

    @Override
    public List<EstimationDTO> findAll() {
        List<Estimation> estimationList = estimationFacade.findAll();
        List<EstimationDTO> dtoList = new ArrayList<EstimationDTO>();
        for (Estimation estimation : estimationList) {
            EstimationDTO estimationDTO = new EstimationDTO();
            estimationDTO.marshall(estimation);
            dtoList.add(estimationDTO);
        }
        return dtoList;
    }

    @Override
    public List<EstimationDTO> findAllByCompanyId(Long companyId) {
        List<Estimation> estimationList = estimationFacade.findAllByCompanyId(companyId);
        List<EstimationDTO> dtoList = new ArrayList<EstimationDTO>();
        for (Estimation estimation : estimationList) {
            EstimationDTO estimationDTO = new EstimationDTO();
            estimationDTO.marshall(estimation);
            dtoList.add(estimationDTO);
        }
        return dtoList;
    }

    @Override
    public List<EstimationDTO> findAllByClientId(Long clientId) {
        List<Estimation> estimationList = estimationFacade.findAllByClientId(clientId);
        List<EstimationDTO> dtoList = new ArrayList<EstimationDTO>();
        for (Estimation estimation : estimationList) {
            EstimationDTO estimationDTO = new EstimationDTO();
            estimationDTO.marshall(estimation);
            dtoList.add(estimationDTO);
        }
        return dtoList;
    }
}
