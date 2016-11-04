/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.domain.model.SimpleModel;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ClientContactService;
import com.opgea.crm.service.ClientService;
import com.opgea.crm.service.UtilService;
import com.opgea.crm.web.dto.ClientContactDTO;
import com.opgea.crm.web.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Ramesh
 */
@ManagedBean(name = "clientContactController")
@RequestScoped
public class ClientContactController {

    private static final long serialVersionUID = 1L;
    @Inject
    private ClientService clientService;
    @Inject
    private ClientContactService clientContactService;
    @Inject
    private UtilService utilService;
    private ClientContactDTO clientContactDTO = new ClientContactDTO();
    private List<ClientContactDTO> clientContactList = new ArrayList<ClientContactDTO>();
    private List<ClientDTO> clientList = new ArrayList<ClientDTO>();
    private List<SimpleModel> contactTypeList = new ArrayList<SimpleModel>();

    public ClientContactController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("ClientContactController Initialized...");
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        clientList = clientService.findAllByCompanyId(data.getCompanyId());
        clientContactList = clientContactService.findAllByCompanyId(data.getCompanyId());
        contactTypeList = utilService.getAllContactTypes();
    }

    public ClientContactDTO getClientContactDTO() {
        return clientContactDTO;
    }

    public void setClientContactDTO(ClientContactDTO clientContactDTO) {
        this.clientContactDTO = clientContactDTO;
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }

    public List<ClientContactDTO> getClientContactList() {
        return clientContactList;
    }

    public void setClientContactList(List<ClientContactDTO> clientContactList) {
        this.clientContactList = clientContactList;
    }

    public List<SimpleModel> getContactTypeList() {
        return contactTypeList;
    }

    public void setContactTypeList(List<SimpleModel> contactTypeList) {
        this.contactTypeList = contactTypeList;
    }

    public String newEntry() {
        clientContactDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String contactId = (String) params.get("contactId");
        this.clientContactDTO = clientContactService.find(new Long(contactId));
        return "view.xhtml";
    }

    public String save() {
        try {
            clientContactService.update(clientContactDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    clientContactDTO.getFirstName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex.getMessage());
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String contactId = (String) params.get("contactId");
        this.clientContactDTO = clientContactService.find(new Long(contactId));
        System.out.println(this.getClass().getName() + " >>: " + clientContactDTO);
        return "create.xhtml";
    }

    public String delete() {
        this.clientContactDTO = clientContactService.find(getClientContactDTO().getContactId());
        return "list.xhtml";
    }
}
