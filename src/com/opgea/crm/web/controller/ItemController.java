/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.web.controller;

import com.opgea.crm.common.constant.SessionConstants;
import com.opgea.crm.common.util.FacesUtil;
import com.opgea.crm.domain.model.SessionData;
import com.opgea.crm.exceptions.DBOperationException;
import com.opgea.crm.service.CompanyService;
import com.opgea.crm.service.ItemService;
import com.opgea.crm.web.dto.ItemDTO;
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
@ManagedBean(name = "itemController")
@RequestScoped
public class ItemController {

    @Inject
    private CompanyService companyService;
    @Inject
    private ItemService itemService;
    private ItemDTO itemDTO = new ItemDTO();
    private List<ItemDTO> itemList = new ArrayList<ItemDTO>();

    @PostConstruct
    public void init() {
        System.out.println("ItemController Initialized...");
        SessionData sessionData = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        itemList = itemService.findAllByCompanyId(sessionData.getCompanyId());
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }

    public String newEntry() {
        itemDTO.reset();
        return "new.xhtml";
    }

    public String view() {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = extContext.getRequestParameterMap();
        String itemId = (String) params.get("itemId");
        this.itemDTO = itemService.find(new Long(itemId));
        return "view.xhtml";
    }

    public String save() {
        SessionData data = (SessionData) FacesUtil.getHttpSession().getAttribute(SessionConstants.SESSION_DATA);
        itemDTO.setCompanyId(data.getCompanyId());
        try {
            itemService.update(itemDTO);
            FacesUtil.addMessage("messages", FacesMessage.SEVERITY_INFO,
                    itemDTO.getName() + " saved successfully!", null);
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
        String itemId = (String) params.get("itemId");
        this.itemDTO = itemService.find(new Long(itemId));
        return "create.xhtml";
    }

    public String delete() {
        this.itemDTO = itemService.find(itemDTO.getId());
        return "list.xhtml";
    }
}
