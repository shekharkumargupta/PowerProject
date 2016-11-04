/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.Item;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.ejb.local.ItemFacadeLocal;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ItemService;
import com.opgea.crm.web.dto.ItemDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class ItemServiceImpl implements ItemService {

    @EJB
    private ItemFacadeLocal itemFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;

    @Override
    public ItemDTO create(ItemDTO itemDTO) throws DBOperationException {
        try {
            Item item = new Item();
            Company company = companyFacade.find(itemDTO.getCompanyId());
            itemDTO.unmarshall(item);
            itemFacade.create(item);
            item.setCompany(company);
            itemFacade.edit(item);
            itemDTO.setId(item.getId());
        }catch(EJBException ex){
            throw new DBOperationException(ex.getMessage());
        }
        return itemDTO;
    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) throws DBOperationException {
        if (itemDTO.getId() != null && itemDTO.getId() > 0) {
            Item item = itemFacade.find(itemDTO.getId());
            Company company = companyFacade.find(itemDTO.getCompanyId());
            itemDTO.unmarshall(item);
            item.setCompany(company);
            itemFacade.edit(item);
        } else {
            this.create(itemDTO);
        }
        return itemDTO;
    }

    @Override
    public ItemDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemDTO find(Long id) {
        Item item = itemFacade.find(id);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.marshall(item);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> itemList = itemFacade.findAll();
        List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
        for (Item item : itemList) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.marshall(item);
            itemDTOs.add(itemDTO);
        }
        return itemDTOs;
    }

    @Override
    public List<ItemDTO> findAllByCompanyId(Long companyId) {
        List<Item> itemList = itemFacade.findAllByCompanyId(companyId);
        List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
        for (Item item : itemList) {
            ItemDTO ItemDTO = new ItemDTO();
            ItemDTO.marshall(item);
            itemDTOs.add(ItemDTO);
        }
        return itemDTOs;
    }
}
