package com.mancom.domain;

import java.io.Serializable;
import java.util.Date;

public class WebsiteDataKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1090331004113494939L;

	private Date downloadDate;

	private String url;

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
