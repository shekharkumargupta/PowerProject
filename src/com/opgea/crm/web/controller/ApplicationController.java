package com.opgea.crm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.opgea.crm.domain.model.Page;
import com.opgea.crm.domain.model.StringBinding;
import com.opgea.crm.service.ApplicationService;
import com.opgea.crm.service.impl.ApplicationServiceImpl;

@ManagedBean(name = "appController")
@ApplicationScoped
public class ApplicationController {

    private ApplicationService applicationService;
    private List<StringBinding> actionList = new ArrayList<StringBinding>();
    private List<Page> pageList = new ArrayList<Page>();
    private Integer pageId;

    public ApplicationController() {
        applicationService = new ApplicationServiceImpl();
        pageList = applicationService.getPageList(1);
        actionList = applicationService.getActionList(1, 1);
    }

    public List<StringBinding> getActionList() {
        System.out.println("Page Id: " + pageId);
        return actionList;
    }

    public void setMenuList(List<StringBinding> actionList) {
        this.actionList = actionList;
    }

    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
}
