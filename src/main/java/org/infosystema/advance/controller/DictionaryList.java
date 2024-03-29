package org.infosystema.advance.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.controller.base.BaseController;
import org.infosystema.advance.model.DictionaryModel;
import org.infosystema.advance.service.DictionaryService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class DictionaryList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private DictionaryService service;
	private DictionaryModel model;
	@Inject
	private DictionaryTypeController dictionaryTypeController;
	
	private String name;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("dictionaryType",dictionaryTypeController.getDictionaryType(), InequalityConstants.EQUAL));
		if(name != null) filters.add(new FilterExample("name",'%'+ name + '%', InequalityConstants.LIKE));
		model = new DictionaryModel(filters, service);
	}
	
	public String clearData() {
		name = null;
		init();	
		return null;
	}

	public DictionaryService getService() {
		return service;
	}
	
	public void setService(DictionaryService service) {
		this.service = service;
	}
	
    public DictionaryModel getModel() {
		return model;
	}
    
    public void setModel(DictionaryModel model) {
		this.model = model;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}

	public DictionaryTypeController getDictionaryTypeController() {
		return dictionaryTypeController;
	}

	public void setDictionaryTypeController(DictionaryTypeController dictionaryTypeController) {
		this.dictionaryTypeController = dictionaryTypeController;
	}
    
}
