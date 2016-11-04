/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ItemEstimationDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ItemEstimationService {

    public ItemEstimationDTO create(ItemEstimationDTO itemEstimationDTO) throws DBOperationException;

    public void createBatch(ItemEstimationDTO itemEstimationDTO) throws DBOperationException;

    public ItemEstimationDTO update(ItemEstimationDTO itemEstimationDTO) throws DBOperationException;

    public ItemEstimationDTO remove(ItemEstimationDTO itemEstimationDTO);

    public ItemEstimationDTO find(Long id);

    public ItemEstimationDTO findExisting(Long estimationId, Long jobId, Long itemId);

    public List<ItemEstimationDTO> findAll();

    public List<ItemEstimationDTO> findAllByEstimation(Long estimationId);
    
    public List<ItemEstimationDTO> findAllByEstimationGroupBy(Long estimationId);

    public List<ItemEstimationDTO> findAllByCompanyId(Long companyId);

    public List<ItemEstimationDTO> findAllByEstimationAndItem(Long estimationId, Long itemId);

    public List<ItemEstimationDTO> findRange(int[] range);

    public int count();
}
