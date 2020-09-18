package org.infosystema.advance.controller.study_abroad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.controller.base.BaseController;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.enums.PersonStatus;
import org.infosystema.advance.model.PersonModel;
import org.infosystema.advance.service.PersonService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class FailedList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private PersonService service;
	private PersonModel model;
	
	private String fullName;
	private String pin;
	private Set<Dictionary> countryList;
	private Set<Dictionary> programList;
	private Set<Dictionary> majorList;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("status", PersonStatus.FAILED, InequalityConstants.EQUAL));
		if (fullName != null) {
			filters.add(new FilterExample(true, "lastname", '%' + fullName + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "firstname", '%' + fullName + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "patronymic", '%' + fullName + '%', InequalityConstants.LIKE, true));
		}
		if(pin != null) filters.add(new FilterExample("pin",'%'+ pin + '%', InequalityConstants.LIKE));
		if (countryList != null && countryList.size()>0) {
			System.out.println("countryList======="+countryList);
			filters.add(new FilterExample("countries", countryList, InequalityConstants.MEMBER_OF));
			//filters.add(new FilterExample(true, "countries", countryList, InequalityConstants.MEMBER_OF, true));
		}
		if (programList != null && programList.size()>0) {
			filters.add(new FilterExample("programs", programList, InequalityConstants.MEMBER_OF));
			//filters.add(new FilterExample(true, "programs", countryList, InequalityConstants.MEMBER_OF, true));
		}
		if (majorList != null && majorList.size()>0) {
			filters.add(new FilterExample("majors", majorList, InequalityConstants.MEMBER_OF));
		}
		
		model = new PersonModel(filters, service);
		model.setFetchProperties(new String[] {"programs","majors","countries","semesters","languages"});
	}
	
	public String clearData() {
		fullName = null;
		pin = null;
		majorList = null;
		programList = null;
		countryList = null;
		init();	
		return null;
	}

	public PersonService getService() {
		return service;
	}
	
	public void setService(PersonService service) {
		this.service = service;
	}
	
    public PersonModel getModel() {
		return model;
	}
    
    public void setModel(PersonModel model) {
		this.model = model;
	}
    
    public String getFullName() {
		return fullName;
	}
    
    public void setFullName(String fullName) {
		this.fullName = fullName;
	}
    
    public String getPin() {
		return pin;
	}
    
    public void setPin(String pin) {
		this.pin = pin;
	}

	public Set<Dictionary> getCountryList() {
		return countryList;
	}
	
	public void setCountryList(Set<Dictionary> countryList) {
		this.countryList = countryList;
	}
	
	public Set<Dictionary> getMajorList() {
		return majorList;
	}
	
	public void setMajorList(Set<Dictionary> majorList) {
		this.majorList = majorList;
	}
	
	public Set<Dictionary> getProgramList() {
		return programList;
	}
	
	public void setProgramList(Set<Dictionary> programList) {
		this.programList = programList;
	}
    
}
