/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.ClientService;
import com.opgea.crm.web.dto.ClientDTO;
import java.io.Serializable;
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
@ManagedBean(name = "clientController")
@RequestScoped
public class ClientController implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private ClientService clientService;
    private ClientDTO clientDTO = new ClientDTO();
    private List<ClientDTO> clientList = new ArrayList<ClientDTO>();

    public ClientController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("ClientController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        clientList = clientService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }

    public String newEntry() {
        clientDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String clientId = (String) params.get("clientId");
        this.clientDTO = clientService.find(new Long(clientId));
        return "view.xhtml";
    }

    public String save() {
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        clientDTO.setCompanyId(data.getCompanyId());
        System.out.println(clientDTO);
        try {
            clientService.update(clientDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    clientDTO.getClientName() + " saved successfully!", null);
        } catch (DBOperationException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(), null);
        }
        return "";
    }

    public String edit() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String clientId = (String) params.get("clientId");
        this.clientDTO = clientService.find(new Long(clientId));
        return "create.xhtml";
    }

    public String delete() {
        this.clientDTO = clientService.find(getClientDTO().getClientId());
        return "list.xhtml";
    }
}
