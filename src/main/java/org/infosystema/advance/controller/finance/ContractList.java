package org.infosystema.advance.controller.finance;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class ContractList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private PersonService service;
	private PersonModel model;
	
	private String fullName;
	private String pin;
	private Set<Dictionary> countryList;
	private BigDecimal contractSum;
	private BigDecimal payedSum;
	private BigDecimal lefSum;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("status", PersonStatus.NEW, InequalityConstants.EQUAL));
		if (fullName != null) {
			filters.add(new FilterExample(true, "lastname", '%' + fullName + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "firstname", '%' + fullName + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "patronymic", '%' + fullName + '%', InequalityConstants.LIKE, true));
		}
		if(pin != null) filters.add(new FilterExample("pin",'%'+ pin + '%', InequalityConstants.LIKE));
		if (countryList != null && countryList.size()>0) {
			System.out.println("countryList======="+countryList);
			filters.add(new FilterExample("countries", countryList, InequalityConstants.MEMBER_OF));
		}	
		
		model = new PersonModel(filters, service);
		model.setFetchProperties(new String[] {"countries","programs","majors","semesters"});
		contractSum=service.sumByExample("contract", filters);
	}
	
	public String clearData() {
		fullName = null;
		pin = null;
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

	public BigDecimal getContractSum() {
		return contractSum;
	}

	public void setContractSum(BigDecimal contractSum) {
		this.contractSum = contractSum;
	}

	public BigDecimal getPayedSum() {
		return payedSum;
	}

	public void setPayedSum(BigDecimal payedSum) {
		this.payedSum = payedSum;
	}

	public BigDecimal getLefSum() {
		return lefSum;
	}

	public void setLefSum(BigDecimal lefSum) {
		this.lefSum = lefSum;
	}
    
}
