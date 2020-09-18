package org.infosystema.advance.controller;

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
import org.infosystema.advance.domain.City;
import org.infosystema.advance.domain.Country;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.enums.CityType;
import org.infosystema.advance.model.ProgramsModel;
import org.infosystema.advance.service.CityService;
import org.infosystema.advance.service.CountryService;
import org.infosystema.advance.service.ProgramsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class ProgramsList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private ProgramsService service;
	@EJB
	private CountryService countryService;
	@EJB
	private CityService cityService;
	private ProgramsModel model;
	
	private String universityName;
	private Set<Dictionary> programList;
	private Set<Dictionary> majorList;
	private Country country;
	private City city;
	private BigDecimal toeflFrom;
	private BigDecimal toeflTo;
	private BigDecimal tuitionFeeFrom;
	private BigDecimal tuitionFeeTo;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		if(universityName != null && universityName.length()>0) filters.add(new FilterExample("universityName",'%'+ universityName + '%', InequalityConstants.LIKE));
		if (programList != null && programList.size()>0) {
			filters.add(new FilterExample("programs", programList, InequalityConstants.MEMBER_OF));
		}
		if (majorList != null && majorList.size()>0) {
			filters.add(new FilterExample("majors", majorList, InequalityConstants.MEMBER_OF));
		}
		if(country!=null) filters.add(new FilterExample("country", country, InequalityConstants.EQUAL));
		if(city!=null) filters.add(new FilterExample("city", city, InequalityConstants.EQUAL));
		if(toeflFrom!=null)
			filters.add(new FilterExample("toefl",toeflFrom , InequalityConstants.GREATER_OR_EQUAL));
		if(toeflTo!=null)
			filters.add(new FilterExample("toefls",toeflTo , InequalityConstants.LESSER_OR_EQUAL));
		if(tuitionFeeFrom!=null)
			filters.add(new FilterExample("tuitionFee",tuitionFeeFrom , InequalityConstants.GREATER_OR_EQUAL));
		if(tuitionFeeTo!=null)
			filters.add(new FilterExample("tuitionFee",tuitionFeeTo , InequalityConstants.LESSER_OR_EQUAL));
		model = new ProgramsModel(filters, service);
		model.setFetchProperties(new String[] {"programs","majors","contacts"});
	}
	
	public String clearData() {
		universityName = null;
		programList = null;
		majorList = null;
		country = null;
		city = null;
		toeflFrom = null;
		toeflTo = null;
		tuitionFeeFrom = null;
		tuitionFeeTo = null;
		init();	
		return null;
	}
	
	public List<Country> getCountryList() {
		List<FilterExample> examples = new ArrayList<>();
		//examples.add(new FilterExample("dictionaryType.id", 7, InequalityConstants.EQUAL));
		return countryService.findByExample(0, 10, examples);
	}
    
    public List<City> getCityList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		if (country!=null) {
			examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
			examples.add(new FilterExample("type", CityType.CITY, InequalityConstants.EQUAL));
			examples.add(new FilterExample("country", country, InequalityConstants.EQUAL));
		}else {
			examples.add(new FilterExample("id", -1, InequalityConstants.EQUAL));
		}
		return cityService.findByExample(0, 50, examples);
	}

	public ProgramsService getService() {
		return service;
	}
	
	public void setService(ProgramsService service) {
		this.service = service;
	}
	
    public ProgramsModel getModel() {
		return model;
	}
    
    public void setModel(ProgramsModel model) {
		this.model = model;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public BigDecimal getToeflFrom() {
		return toeflFrom;
	}

	public void setToeflFrom(BigDecimal toeflFrom) {
		this.toeflFrom = toeflFrom;
	}

	public BigDecimal getToeflTo() {
		return toeflTo;
	}

	public void setToeflTo(BigDecimal toeflTo) {
		this.toeflTo = toeflTo;
	}

	public BigDecimal getTuitionFeeFrom() {
		return tuitionFeeFrom;
	}

	public void setTuitionFeeFrom(BigDecimal tuitionFeeFrom) {
		this.tuitionFeeFrom = tuitionFeeFrom;
	}

	public BigDecimal getTuitionFeeTo() {
		return tuitionFeeTo;
	}

	public void setTuitionFeeTo(BigDecimal tuitionFeeTo) {
		this.tuitionFeeTo = tuitionFeeTo;
	}
    
}
