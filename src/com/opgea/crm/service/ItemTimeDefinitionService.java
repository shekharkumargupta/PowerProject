/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ItemTimeDefinitionDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ItemTimeDefinitionService {

    public ItemTimeDefinitionDTO create(ItemTimeDefinitionDTO timeDTO) throws DBOperationException;

    public ItemTimeDefinitionDTO update(ItemTimeDefinitionDTO timeDTO) throws DBOperationException;

    public ItemTimeDefinitionDTO remove(Long id);

    public ItemTimeDefinitionDTO find(Long id);

    public List<ItemTimeDefinitionDTO> findAll();

    public List<ItemTimeDefinitionDTO> findAllByCompanyId(Long companyId);

    public List<ItemTimeDefinitionDTO> findAllByItemId(Long itemId);

    List<ItemTimeDefinitionDTO> findAllByCompanyAndItemId(Long companyId, Long itemId);
}
