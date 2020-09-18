package org.infosystema.advance.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.conversation.ConversationCity;
import org.infosystema.advance.domain.City;
import org.infosystema.advance.enums.CityType;
import org.infosystema.advance.service.CityService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;


@ManagedBean
@ViewScoped
public class CityController {

	@EJB
	private CityService cityService;
	@Inject
	private EntityValidator validator;
	@Inject
	private ConversationCity conversation;	
	@Inject
	private CountryController countryController;
	
	private City city;
    
	@PostConstruct
	public void init() {
		city=conversation.getCity();
		if (city==null)	city= new City();
	}
	
	public String add() {
		city = new City();
		conversation.setCity(city);
		return form();
	}
	
	public String edit(City city) {
		this.city = city;
		conversation.setCity(city);
		return form();
	}
	
	public String save() {
		System.out.println(city);
		if(city == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
		List<City> identifiers = cityService.findByProperty("name", city.getName());
    	if(identifiers.equals(0)){
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("nameAlreadyExists"), null) );
			return null;
    	}
    	
    	city.setCountry(countryController.getCountry());
		validator.validate(city);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		city = city.getId() == null ? cityService.persist(city) : cityService.merge(city);
		
		conversation.setCity(null);
		
	    return cancel();  
		
	}
	
	public String delete(City c) {
		System.out.println(c);
		cityService.remove(c);
		return cancel();
	}
	
	public String cancel() {
		city = null;
		return list();
	}
	
	private String list() {
		return "/view/country/city_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/country/city_form.xhtml";
	}
	
	public List<CityType> getCityTypeList() {
		return Arrays.asList(CityType.values());
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	public CountryController getCountryController() {
		return countryController;
	}

	public void setCountryController(CountryController countryController) {
		this.countryController = countryController;
	}

}
