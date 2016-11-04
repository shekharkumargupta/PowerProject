package com.opgea.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.opgea.crm.domain.model.Authorization;
import com.opgea.crm.domain.model.Page;
import com.opgea.crm.domain.model.StringBinding;
import com.opgea.crm.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService {

    private static final List<Page> pageList = new ArrayList<Page>();
    
    static{
        pageList.add(new Page(1, "/company/list.xhtml", "Company"));
        pageList.add(new Page(2, "/country/list.xhtml", "Country"));
        pageList.add(new Page(3, "/city/list.xhtml", "City"));
        pageList.add(new Page(4, "/employee/list.xhtml", "Employee"));
        pageList.add(new Page(5, "/department/list.xhtml", "Department"));
        pageList.add(new Page(6, "/designation/list.xhtml", "Designation"));
        pageList.add(new Page(7, "/job/list.xhtml", "Job"));
        pageList.add(new Page(8, "/jobStatus/list.xhtml", "JobStatus"));
        pageList.add(new Page(9, "/client/list.xhtml", "Client"));
        pageList.add(new Page(10, "/clientcontact/list.xhtml", "Client Contact"));
        pageList.add(new Page(11, "/item/list.xhtml", "Item"));
        pageList.add(new Page(12, "/itemtime/list.xhtml", "Time Definition"));
        pageList.add(new Page(13, "/estimation/list.xhtml", "Estimation"));
        pageList.add(new Page(14, "/itemestimation/list.xhtml", "Item Estimation"));
        pageList.add(new Page(15, "/project/list.xhtml", "Project"));
        pageList.add(new Page(16, "/team/list.xhtml", "Team"));
        pageList.add(new Page(17, "/task/list.xhtml", "Task"));
    }
    
    @Override
    public List<Page> getPageList(int accessLevelId) {
        return pageList;
    }
    
    /**
     * 
     * @param pageName
     * @return 
     */
    public static Page getPage(String pageName){
        Page foundPage = null;
        for(Page page: pageList){
            if(page.getPageName().equalsIgnoreCase(pageName)){
                foundPage = page;
            }
        }
        return foundPage;
    }
    
    @Override
    public Page getPage(String pageName, int accessLevelId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<StringBinding> getActionList(int pageId, int accessLevelId) {
        List<StringBinding> menuList = new ArrayList<StringBinding>();
        menuList.add(new StringBinding("new.xhtml", "New"));
        menuList.add(new StringBinding("list.xhtml", "View All"));
        return menuList;
    }

    @Override
    public List<Authorization> getAllAuthorizations(int pageId,
            int accessLevelId) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
