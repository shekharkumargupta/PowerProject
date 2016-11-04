package com.opgea.crm.domain.model;

public class Page {

	private Integer pageId;
	private String pageURL;
	private String pageName;
	
	public Page() {
		super();
	}

	public Page(Integer pageId, String pageURL, String pageName) {
		super();
		this.pageId = pageId;
		this.pageURL = pageURL;
		this.pageName = pageName;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	public String getURL(){
		return this.getPageURL();
	}

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageName=" + pageName
				+ ", pageURL=" + pageURL + "]";
	}
	
	
	
}
