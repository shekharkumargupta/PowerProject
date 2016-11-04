/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.domain.entities.ItemTimeDefinition;
import com.opgea.crm.domain.entities.Job;
import com.opgea.crm.ejb.local.ItemFacadeLocal;
import com.opgea.crm.ejb.local.ItemTimeDefinitionFacadeLocal;
import com.opgea.crm.ejb.local.JobFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ItemTimeDefinitionService;
import com.opgea.crm.web.dto.ItemTimeDefinitionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class ItemTimeDefinitionServiceImpl implements ItemTimeDefinitionService {

    @EJB
    private ItemTimeDefinitionFacadeLocal timeFacade;
    @EJB
    private ItemFacadeLocal itemFacade;
    @EJB
    private JobFacadeLocal jobFacade;

    @Override
    public ItemTimeDefinitionDTO create(ItemTimeDefinitionDTO timeDTO) throws DBOperationException {
        try {
            ItemTimeDefinition time = new ItemTimeDefinition();
            timeDTO.unmarshall(time);
            timeFacade.create(time);
            Item item = itemFacade.find(timeDTO.getItemId());
            Job job = jobFacade.find(timeDTO.getJobId());
            time.setItem(item);
            time.setJob(job);
            timeFacade.edit(time);
            timeDTO.setId(time.getId());
            timeDTO.setDurationTypeName(time.getDurationType().name());
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getMessage());
        }
        return timeDTO;
    }

    @Override
    public ItemTimeDefinitionDTO update(ItemTimeDefinitionDTO timeDTO) throws DBOperationException {
        if (timeDTO.getId() != null && timeDTO.getId() > 0) {
            ItemTimeDefinition time = timeFacade.find(timeDTO.getId());
            timeDTO.unmarshall(time);
            Item item = itemFacade.find(timeDTO.getItemId());
            Job job = jobFacade.find(timeDTO.getJobId());
            time.setItem(item);
            time.setJob(job);
            timeFacade.edit(time);
            timeDTO.setId(time.getId());
        } else {
            this.create(timeDTO);
        }
        return timeDTO;
    }

    @Override
    public ItemTimeDefinitionDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemTimeDefinitionDTO find(Long id) {
        ItemTimeDefinition time = timeFacade.find(id);
        ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
        timeDTO.marshall(time);
        return timeDTO;
    }

    @Override
    public List<ItemTimeDefinitionDTO> findAll() {
        List<ItemTimeDefinition> timeList = timeFacade.findAll();
        List<ItemTimeDefinitionDTO> timeDTOList = new ArrayList<ItemTimeDefinitionDTO>();
        for (ItemTimeDefinition time : timeList) {
            ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
            timeDTO.marshall(time);
            timeDTOList.add(timeDTO);
        }
        return timeDTOList;
    }

    @Override
    public List<ItemTimeDefinitionDTO> findAllByCompanyId(Long companyId) {
        List<ItemTimeDefinition> timeList = timeFacade.findAllByCompanyId(companyId);
        List<ItemTimeDefinitionDTO> timeDTOList = new ArrayList<ItemTimeDefinitionDTO>();
        for (ItemTimeDefinition time : timeList) {
            ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
            timeDTO.marshall(time);
            timeDTOList.add(timeDTO);
        }
        return timeDTOList;
    }

    @Override
    public List<ItemTimeDefinitionDTO> findAllByItemId(Long itemId) {
        List<ItemTimeDefinition> timeList = timeFacade.findAllByItemId(itemId);
        List<ItemTimeDefinitionDTO> timeDTOList = new ArrayList<ItemTimeDefinitionDTO>();
        for (ItemTimeDefinition time : timeList) {
            ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
            timeDTO.marshall(time);
            timeDTOList.add(timeDTO);
        }
        return timeDTOList;
    }

    @Override
    public List<ItemTimeDefinitionDTO> findAllByCompanyAndItemId(Long companyId, Long itemId) {
        List<ItemTimeDefinition> timeList = timeFacade.findAllByCompanyAndItemId(companyId, itemId);
        List<ItemTimeDefinitionDTO> timeDTOList = new ArrayList<ItemTimeDefinitionDTO>();
        System.out.println("Time definition count: " + timeDTOList.size());
        for (ItemTimeDefinition time : timeList) {
            ItemTimeDefinitionDTO timeDTO = new ItemTimeDefinitionDTO();
            timeDTO.marshall(time);
            timeDTOList.add(timeDTO);
        }
        return timeDTOList;
    }
}
