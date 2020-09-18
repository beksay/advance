package org.infosystema.advance.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.controller.base.BaseController;
import org.infosystema.advance.model.UserModel;
import org.infosystema.advance.service.UserService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class UserList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private UserService service;
	private UserModel model;
	
	private String name;
	
	@PostConstruct
	private void init() {
		List<FilterExample> filters = Collections.emptyList();
		model = new UserModel(filters, service);
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();

		if(name != null) filters.add(new FilterExample("firstname", name, InequalityConstants.EQUAL));	
		model = new UserModel(filters, service);
	}
	
	public String clearData() {
		name = null;
		
		init();
		
		return null;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public UserModel getModel() {
		return model;
	}

	public void setModel(UserModel model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
