/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.domain.entities.Estimation;
import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.domain.entities.ItemEstimation;
import com.opgea.crm.domain.entities.ItemTimeDefinition;
import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.ejb.local.EstimationFacadeLocal;
import com.opgea.crm.ejb.local.ItemEstimationFacadeLocal;
import com.opgea.crm.ejb.local.ItemFacadeLocal;
import com.opgea.crm.ejb.local.ItemTimeDefinitionFacadeLocal;
import com.opgea.crm.ejb.local.JobFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ItemEstimationService;
import com.opgea.crm.web.dto.ItemEstimationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Ramesh
 */
public class ItemEstimationServiceImpl implements ItemEstimationService {

    @EJB
    private ItemEstimationFacadeLocal itemEstimationFacade;
    @EJB
    private ItemFacadeLocal itemFacade;
    @EJB
    private EstimationFacadeLocal estimationFacade;
    @EJB
    private JobFacadeLocal jobFacade;
    @EJB
    private ItemTimeDefinitionFacadeLocal itemTimeDefinitionFacade;

    @Override
    public ItemEstimationDTO create(ItemEstimationDTO itemEstimationDTO) throws DBOperationException {
        ItemEstimation itemEstimation = new ItemEstimation();
        itemEstimationDTO.unmarshall(itemEstimation);
        itemEstimationFacade.create(itemEstimation);
        Item item = itemFacade.find(itemEstimationDTO.getItemId());
        Estimation estimation = estimationFacade.find(itemEstimationDTO.getEstimationId());
        Job job = jobFacade.find(itemEstimationDTO.getJobId());
        itemEstimation.setItem(item);
        itemEstimation.setEstimation(estimation);
        itemEstimation.setJob(job);
        itemEstimationFacade.edit(itemEstimation);
        itemEstimationDTO.setId(itemEstimation.getId());
        return itemEstimationDTO;
    }

    @Override
    public ItemEstimationDTO update(ItemEstimationDTO itemEstimationDTO) throws DBOperationException {
        ItemEstimation itemEstimation = itemEstimationFacade.find(itemEstimationDTO.getId());
        itemEstimationDTO.unmarshall(itemEstimation);
        Item item = itemFacade.find(itemEstimationDTO.getItemId());
        Estimation estimation = estimationFacade.find(itemEstimationDTO.getEstimationId());
        Job job = jobFacade.find(itemEstimationDTO.getJobId());
        itemEstimation.setItem(item);
        itemEstimation.setEstimation(estimation);
        itemEstimation.setJob(job);
        itemEstimationFacade.edit(itemEstimation);
        itemEstimationDTO.setId(itemEstimation.getId());
        return itemEstimationDTO;
    }

    @Override
    public ItemEstimationDTO remove(ItemEstimationDTO itemEstimationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemEstimationDTO find(Long id) {
        ItemEstimation itemEstimation = itemEstimationFacade.find(id);
        System.out.println(itemEstimation);
        ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
        itemEstimationDTO.marshall(itemEstimation);
        /*
        if (itemEstimationDTO.getTotalTime() > 0
                && itemEstimation.getTotalTime() > 0) {
            itemEstimationDTO.setItemTotalTime(itemEstimationDTO.getItemTotalTime() + itemEstimation.getTotalTime());
            itemEstimationDTO.setGrandTotalTime(itemEstimationDTO.getItemTotalTime() + itemEstimation.getTotalTime());
        }
         * 
         */
        return itemEstimationDTO;
    }

    @Override
    public List<ItemEstimationDTO> findAll() {
        List<ItemEstimation> itemEstimationList = itemEstimationFacade.findAll();
        List<ItemEstimationDTO> itemEstimatoinDTOList = new ArrayList<ItemEstimationDTO>();
        Long itemTotalTime = 0L;
        for (ItemEstimation itemEstimation : itemEstimationList) {
            if (itemEstimation.getTotalTime() != null) {
                itemTotalTime = itemTotalTime + itemEstimation.getTotalTime();
            } else {
                itemTotalTime = itemEstimation.getTotalTime();
            }
            ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
            itemEstimationDTO.marshall(itemEstimation);
            itemEstimationDTO.setItemTotalTime(itemTotalTime);
            itemEstimatoinDTOList.add(itemEstimationDTO);
        }
        return itemEstimatoinDTOList;
    }

    @Override
    public List<ItemEstimationDTO> findAllByEstimation(Long estimationId) {
        List<ItemEstimation> itemEstimationList = itemEstimationFacade.findAllByEstimation(estimationId);
        List<ItemEstimationDTO> itemEstimatoinDTOList = new ArrayList<ItemEstimationDTO>();
        Long itemTotalTime = 0L;
        for (ItemEstimation itemEstimation : itemEstimationList) {
            System.out.println(itemEstimation);
            if (itemEstimation != null && itemEstimation.getTotalTime() != null) {
                itemTotalTime = itemTotalTime + itemEstimation.getTotalTime();
            } else {
                itemTotalTime = itemEstimation.getTotalTime();
            }
            ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
            itemEstimationDTO.marshall(itemEstimation);
            itemEstimationDTO.setItemTotalTime(itemTotalTime);
            itemEstimatoinDTOList.add(itemEstimationDTO);
        }
        return itemEstimatoinDTOList;
    }

    @Override
    public List<ItemEstimationDTO> findAllByEstimationGroupBy(Long estimationId) {
        List<ItemEstimation> itemEstimationList = itemEstimationFacade.findAllByEstimationGroupBy(estimationId);
        List<ItemEstimationDTO> itemEstimatoinDTOList = new ArrayList<ItemEstimationDTO>();
        Long itemTotalTime = 0L;
        for (ItemEstimation itemEstimation : itemEstimationList) {
            System.out.println(itemEstimation);
            if (itemEstimation != null && itemEstimation.getTotalTime() != null) {
                itemTotalTime = itemTotalTime + itemEstimation.getTotalTime();
            } else {
                itemTotalTime = itemEstimation.getTotalTime();
            }
            ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
            itemEstimationDTO.marshall(itemEstimation);
            itemEstimationDTO.setItemTotalTime(itemTotalTime);
            itemEstimatoinDTOList.add(itemEstimationDTO);
        }
        return itemEstimatoinDTOList;
    }
    
    @Override
    public List<ItemEstimationDTO> findAllByEstimationAndItem(Long estimationId, Long itemId) {
        List<ItemEstimation> itemEstimationList =
                itemEstimationFacade.findAllByEstimationAndItem(estimationId, itemId);
        List<ItemEstimationDTO> itemEstimatoinDTOList = new ArrayList<ItemEstimationDTO>();
        Long itemTotalTime = 0L;
        for (ItemEstimation itemEstimation : itemEstimationList) {
            if (itemEstimation.getTotalTime() != null) {
                itemTotalTime = itemTotalTime + itemEstimation.getTotalTime();
            } else {
                itemTotalTime = itemEstimation.getTotalTime();
            }
            ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
            itemEstimationDTO.marshall(itemEstimation);
            itemEstimationDTO.setItemTotalTime(itemTotalTime);
            itemEstimatoinDTOList.add(itemEstimationDTO);
        }
        return itemEstimatoinDTOList;
    }

    @Override
    public List<ItemEstimationDTO> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createBatch(ItemEstimationDTO itemEstimationDTO) throws DBOperationException {
        List<ItemTimeDefinition> itemTimeDefinitionList =
                itemTimeDefinitionFacade.findAllByItemId(itemEstimationDTO.getItemId());
        List<ItemEstimation> itemEstimationList = new ArrayList<ItemEstimation>();
        Item item = itemFacade.find(itemEstimationDTO.getItemId());
        Estimation estimation = estimationFacade.find(itemEstimationDTO.getEstimationId());
        //Job job = jobFacade.find(itemEstimationDTO.getJobId());

        for (ItemTimeDefinition defaultTime : itemTimeDefinitionList) {
            ItemEstimation itemEstimation =
                    itemEstimationFacade.findExisting(itemEstimationDTO.getEstimationId(),
                    defaultTime.getJob().getId(),
                    itemEstimationDTO.getItemId());
            /**
             * Inserting record if the entity is not already present in database.
             */
            if (itemEstimation == null) {
                itemEstimation = new ItemEstimation();
                itemEstimation.setId(null);
                itemEstimation.setItem(defaultTime.getItem());
                itemEstimation.setQuantity(ApplicationConstants.DEFAULT_QUANTITY);
                itemEstimation.setDurationType(defaultTime.getDurationType());
                itemEstimation.setDurationValue(defaultTime.getDurationValue());
                itemEstimation.setJob(defaultTime.getJob());
                itemEstimation.setItem(item);
                itemEstimation.setEstimation(estimation);
                itemEstimationList.add(itemEstimation);
            }
        }
        itemEstimationFacade.createBatch(itemEstimationList);
    }

    @Override
    public List<ItemEstimationDTO> findAllByCompanyId(Long companyId) {
        List<ItemEstimation> itemEstimationList = itemEstimationFacade.findAllByCompanyId(companyId);
        List<ItemEstimationDTO> itemEstimatoinDTOList = new ArrayList<ItemEstimationDTO>();
        Long itemTotalTime = 0L;
        for (ItemEstimation itemEstimation : itemEstimationList) {
            if (itemEstimation.getTotalTime() != null) {
                itemTotalTime = itemTotalTime + itemEstimation.getTotalTime();
            } else {
                itemTotalTime = itemEstimation.getTotalTime();
            }
            ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
            itemEstimationDTO.marshall(itemEstimation);
            itemEstimationDTO.setItemTotalTime(itemTotalTime);
            itemEstimatoinDTOList.add(itemEstimationDTO);
        }
        return itemEstimatoinDTOList;
    }

    @Override
    public ItemEstimationDTO findExisting(Long estimationId, Long jobId, Long itemId) {
        ItemEstimation itemEstimation = itemEstimationFacade.findExisting(estimationId, jobId, itemId);
        ItemEstimationDTO itemEstimationDTO = new ItemEstimationDTO();
        itemEstimationDTO.marshall(itemEstimation);
        return itemEstimationDTO;
    }
}
