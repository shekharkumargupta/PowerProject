package com.opgea.crm.domain.model;

public class Authorization {

    private Integer userId;
    private Integer pageId;
    private String actionURL;
    private Boolean isCreatable;
    private Boolean isUpdatable;
    private Boolean isReadable;
    private Boolean isDeletable;

    public Authorization() {
        super();
    }

    @Override
    public String toString() {
        return "Authorization [userId=" + userId + ", pageId=" + pageId
                + ", actionURL=" + actionURL + ", isCreatable=" + isCreatable
                + ", isUpdatable=" + isUpdatable + ", isReadable=" + isReadable
                + ", isDeletable=" + isDeletable + "]";
    }

    public Authorization(Integer userId, Integer pageId, String actionURL,
            Boolean isCreatable, Boolean isUpdatable, Boolean isReadable,
            Boolean isDeletable) {
        super();
        this.userId = userId;
        this.pageId = pageId;
        this.actionURL = actionURL;
        this.isCreatable = isCreatable;
        this.isUpdatable = isUpdatable;
        this.isReadable = isReadable;
        this.isDeletable = isDeletable;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }

    public Boolean getIsCreatable() {
        return isCreatable;
    }

    public void setIsCreatable(Boolean isCreatable) {
        this.isCreatable = isCreatable;
    }

    public Boolean getIsUpdatable() {
        return isUpdatable;
    }

    public void setIsUpdatable(Boolean isUpdatable) {
        this.isUpdatable = isUpdatable;
    }

    public Boolean getIsReadable() {
        return isReadable;
    }

    public void setIsReadable(Boolean isReadable) {
        this.isReadable = isReadable;
    }

    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
    }
}
