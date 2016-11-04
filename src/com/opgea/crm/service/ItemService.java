/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ItemDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ItemService {

    public ItemDTO create(ItemDTO itemDTO) throws DBOperationException;

    public ItemDTO update(ItemDTO itemDTO) throws DBOperationException;

    public ItemDTO remove(Long id);

    public ItemDTO find(Long id);

    public List<ItemDTO> findAll();

    public List<ItemDTO> findAllByCompanyId(Long companyId);
}
