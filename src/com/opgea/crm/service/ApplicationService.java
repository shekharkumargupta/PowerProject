package com.opgea.crm.service;

import java.util.List;

import com.opgea.crm.domain.model.Authorization;
import com.opgea.crm.domain.model.Page;
import com.opgea.crm.domain.model.StringBinding;

public interface ApplicationService {

    public List<Page> getPageList(int accessLevelId);
    
    public Page getPage(String pageName, int accessLevelId);

    public List<Authorization> getAllAuthorizations(int pageId, int accessLevelId);

    public List<StringBinding> getActionList(int pageId, int accessLevelId);
}
