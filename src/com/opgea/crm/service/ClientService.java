/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service;

import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.web.dto.ClientContactDTO;
import com.opgea.crm.web.dto.ClientDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientService {

    public ClientDTO create(ClientDTO clientDTO) throws DBOperationException;

    public ClientDTO update(ClientDTO clientDTO) throws DBOperationException;

    public void addContact(Long clientId, ClientContactDTO clientContactDTO)throws DBOperationException;

    public ClientDTO remove(Long id);

    public ClientDTO find(Long id);

    public List<ClientDTO> findAll();

    public List<ClientDTO> findAllByCompanyId(Long companyId);

    public List<ClientContactDTO> getContactList(Long clientId);
}
