/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ClientContactDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientContactService {

    public ClientContactDTO create(ClientContactDTO clientContactDTO) throws DBOperationException;

    public ClientContactDTO update(ClientContactDTO clientContactDTO) throws DBOperationException;

    public ClientContactDTO remove(Long id);

    public ClientContactDTO find(Long id);

    public List<ClientContactDTO> findAll();

    public List<ClientContactDTO> findAllByClientId(Long clientId);

    public List<ClientContactDTO> findAllByCompanyId(Long companyId);
}
