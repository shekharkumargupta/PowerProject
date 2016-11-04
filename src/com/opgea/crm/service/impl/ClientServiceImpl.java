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
import com.opgea.crm.domain.entities.Company;
import com.opgea.crm.domain.entities.qualifier.ContactType;
import com.opgea.crm.ejb.local.CityFacadeLocal;
import com.opgea.crm.ejb.local.ClientFacadeLocal;
import com.opgea.crm.ejb.local.CompanyFacadeLocal;
import com.opgea.crm.service.ClientService;
import com.opgea.crm.web.dto.ClientContactDTO;
import com.opgea.crm.web.dto.ClientDTO;
import javax.ejb.EJB;
import javax.ejb.EJBException;

/**
 *
 * @author Ramesh
 */
public class ClientServiceImpl implements ClientService {

    @EJB
    private ClientFacadeLocal clientFacade;
    @EJB
    private CompanyFacadeLocal companyFacade;
    @EJB
    private CityFacadeLocal cityFacade;

    @Override
    public ClientDTO create(ClientDTO clientDTO) throws DBOperationException {
        Company company = companyFacade.find(clientDTO.getCompanyId());
        Client client = new Client();
        clientDTO.unmarshall(client);
        try {
            clientFacade.create(client);
            client.setCompany(company);
            clientFacade.edit(client);
            clientDTO.setClientId(client.getId());
        } catch (EJBException ex) {
            throw new DBOperationException(ex.getMessage());
        }
        return clientDTO;
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) throws DBOperationException {
        if (clientDTO.getClientId() != null && clientDTO.getClientId() > 0) {
            Client client = clientFacade.find(clientDTO.getClientId());
            clientDTO.unmarshall(client);
            clientFacade.edit(client);
            clientDTO.setClientId(client.getId());
        } else {
            this.create(clientDTO);
        }
        return clientDTO;
    }

    @Override
    public ClientDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientDTO find(Long id) {
        Client client = clientFacade.find(id);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.marshall(client);
        return clientDTO;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clientList = clientFacade.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
        for (Client client : clientList) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.marshall(client);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    @Override
    public List<ClientDTO> findAllByCompanyId(Long companyId) {
        List<Client> clientList = clientFacade.findByCompanyId(companyId);
        List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
        for (Client client : clientList) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.marshall(client);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    @Override
    public void addContact(Long clientId, ClientContactDTO clientContactDTO) throws DBOperationException {
        Client client = clientFacade.find(clientId);
        ClientContact contact = new ClientContact();
        contact.setFirstName(clientContactDTO.getFirstName());
        contact.setMiddleInitial(clientContactDTO.getMiddleInitial());
        contact.setLastName(clientContactDTO.getLastName());
        contact.setContactType(ContactType.values()[clientContactDTO.getContactType()]);
        contact.setEmail(clientContactDTO.getEmail());
        contact.setPhone1(clientContactDTO.getPhone1());
        contact.setPhone2(clientContactDTO.getPhone2());
        contact.setClient(client);

        if (clientContactDTO.getContactId() > 0) {
            contact.setId(clientContactDTO.getContactId());
            client.getContactPersons().remove(client);
        }

        client.addContact(contact);
        clientFacade.edit(client);

    }

    @Override
    public List<ClientContactDTO> getContactList(Long clientId) {
        Client client = clientFacade.find(clientId);
        List<ClientContact> contacts = client.getContactPersons();
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for (ClientContact contact : contacts) {
            ClientContactDTO contactDTO = new ClientContactDTO();
            contactDTO.setContactId(contact.getId());
            contactDTO.setFirstName(contact.getFirstName());
            contactDTO.setMiddleInitial(contact.getMiddleInitial());
            contactDTO.setLastName(contact.getLastName());
            contactDTO.setDesignation(contact.getDesignation());
            contactDTO.setContactType(contact.getContactType().ordinal());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setPhone1(contact.getPhone1());
            contactDTO.setPhone2(contact.getPhone2());
            contactDTOList.add(contactDTO);
        }
        return contactDTOList;
    }
}
