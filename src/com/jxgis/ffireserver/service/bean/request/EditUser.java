package com.jxgis.ffireserver.service.bean.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.jxgis.ffireserver.web.bean.TSUser;

@XmlRootElement(name = "editUser")
public class EditUser {

	private TSUser user;

	public TSUser getUser() {
		return user;
	}

	public void setUser(TSUser user) {
		this.user = user;
	}

}
