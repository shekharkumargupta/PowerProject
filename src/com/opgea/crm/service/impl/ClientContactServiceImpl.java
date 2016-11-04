/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.service.impl;

import com.opgea.crm.exceptions.DBOperationException;
import java.util.ArrayList;
import java.util.List;

import com.opgea.crm.domain.entities.Client;
import com.opgea.crm.domain.entities.ClientContact;
import com.opgea.crm.ejb.local.ClientContactFacadeLocal;
import com.opgea.crm.ejb.local.ClientFacadeLocal;
import com.opgea.crm.service.ClientContactService;
import com.opgea.crm.web.dto.ClientContactDTO;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class ClientContactServiceImpl implements ClientContactService {

    @EJB
    private ClientContactFacadeLocal clientContactFacade;
    @EJB
    private ClientFacadeLocal clientFacade;

    @Override
    public ClientContactDTO create(ClientContactDTO clientContactDTO) throws DBOperationException {
        System.out.println(this.getClass().getName() + " >>: " + clientContactDTO);
        try {
            Client client = clientFacade.find(clientContactDTO.getClientId());
            ClientContact contact = new ClientContact();
            clientContactDTO.unmarshall(contact);
            clientContactFacade.create(contact);
            contact.setClient(client);
            clientContactFacade.edit(contact);
            clientContactDTO.setContactId(contact.getId());
        }catch(EJBException ex){
            throw new DBOperationException(ex.getMessage());
        }
        return clientContactDTO;
    }

    @Override
    public ClientContactDTO update(ClientContactDTO clientContactDTO) throws DBOperationException {
        if (clientContactDTO.getContactId() != null && clientContactDTO.getContactId() > 0) {
            System.out.println(this.getClass().getName() + " >>: " + clientContactDTO);
            Client client = clientFacade.find(clientContactDTO.getClientId());
            ClientContact contact = clientContactFacade.find(clientContactDTO.getContactId());
            clientContactDTO.unmarshall(contact);
            contact.setClient(client);
            clientContactFacade.edit(contact);
            clientContactDTO.setContactId(contact.getId());
        } else {
            this.create(clientContactDTO);
        }
        return clientContactDTO;
    }

    @Override
    public ClientContactDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientContactDTO find(Long id) {
        ClientContact contact = clientContactFacade.find(id);
        ClientContactDTO clientContactDTO = new ClientContactDTO();
        clientContactDTO.marshall(contact);
        return clientContactDTO;
    }

    @Override
    public List<ClientContactDTO> findAll() {
        List<ClientContact> contactList = clientContactFacade.findAll();
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for (ClientContact contact : contactList) {
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.marshall(contact);
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }

    @Override
    public List<ClientContactDTO> findAllByClientId(Long clientId) {
        List<ClientContact> contactList = clientContactFacade.findByClientId(clientId);
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for (ClientContact contact : contactList) {
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.marshall(contact);
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }

    @Override
    public List<ClientContactDTO> findAllByCompanyId(Long companyId) {
        List<ClientContact> contactList = clientContactFacade.findByCompanyId(companyId);
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for (ClientContact contact : contactList) {
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.marshall(contact);
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }
}
