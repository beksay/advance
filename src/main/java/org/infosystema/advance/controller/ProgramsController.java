package org.infosystema.advance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.conversation.ConversationPrograms;
import org.infosystema.advance.domain.City;
import org.infosystema.advance.domain.Country;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.Programs;
import org.infosystema.advance.enums.CityType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.service.CityService;
import org.infosystema.advance.service.CountryService;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.service.ProgramsService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;
import org.primefaces.event.SelectEvent;


@ManagedBean
@ViewScoped
public class ProgramsController {

	@EJB
	private ProgramsService programsService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private CountryService countryService;
	@EJB
	private CityService cityService;
	@Inject
	private EntityValidator validator;
	@Inject
	private ConversationPrograms conversation;	
	
	private Programs program;
	private Set<Dictionary> programs;
	private Set<Dictionary> majors;
	private Set<Dictionary> contacts;
    
	@PostConstruct
	public void init() {
		program=conversation.getProgram();
		if (program==null)	program= new Programs();
		programs = conversation.getPrograms();
		if(programs==null) programs = new HashSet<>();
		majors = conversation.getMajors();
		if(majors==null) majors = new HashSet<>();
		contacts = conversation.getContacts();
		if(contacts==null) contacts = new HashSet<>();
	}
	
	public String add() {
		program = new Programs();
		conversation.setProgram(program);
		programs = new HashSet<>();
		majors = new HashSet<>();
		contacts = new HashSet<>();
		return form();
	}
	
	public String edit(Programs program) {
		this.program = program;
		conversation.setProgram(program);
		programs = new HashSet<>();
		programs.addAll(program.getPrograms());
		conversation.setPrograms(programs);
		majors = new HashSet<>();
		majors.addAll(program.getMajors());
		conversation.setMajors(majors);
		contacts = new HashSet<>();
		contacts.addAll(program.getContacts());
		conversation.setContacts(contacts);
		return form();
	}
	
	public String save() {
		System.out.println(program);
		if(program == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
	    program.setPrograms(programs);
	    program.setMajors(majors);
	    program.setContacts(contacts);
		validator.validate(program);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		program = program.getId() == null ? programsService.persist(program) : programsService.merge(program);
		
		conversation.setPrograms(null);
		
	    return cancel();  
		
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		program=(Programs) event.getObject();
		conversation.setProgram(program);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/programs/programs_preview.xhtml?cid="+conversation.getId());
        
    }
	
	public String delete(Programs c) {
		System.out.println(c);
		programsService.remove(c);
		return cancel();
	}
	
	public String cancel() {
		program = null;
		return list();
	}
	
	private String list() {
		return "/view/programs/programs_journal.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/programs/programs_form.xhtml";
	}

	public List<Dictionary> getProgramList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 5, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 20, examples);
	}
    
    public List<Dictionary> getMajorList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 7, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 20, examples);
	}
    
    public List<Dictionary> getContactsList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 10, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Country> getCountryList() {
		List<FilterExample> examples = new ArrayList<>();
		//examples.add(new FilterExample("dictionaryType.id", 7, InequalityConstants.EQUAL));
		return countryService.findByExample(0, 10, examples);
	}
    
    public List<City> getCityList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		if (program.getCountry() !=null) {
			examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
			examples.add(new FilterExample("type", CityType.CITY, InequalityConstants.EQUAL));
			examples.add(new FilterExample("country", program.getCountry(), InequalityConstants.EQUAL));
		}else {
			examples.add(new FilterExample("id", -1, InequalityConstants.EQUAL));
		}
		return cityService.findByExample(0, 50, examples);
	}
    
    public List<City> getStateList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		if (program.getCountry() !=null) {
			examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
			examples.add(new FilterExample("type", CityType.STATE, InequalityConstants.EQUAL));
			examples.add(new FilterExample("country", program.getCountry(), InequalityConstants.EQUAL));
		}else {
			examples.add(new FilterExample("id", -1, InequalityConstants.EQUAL));
		}
		return cityService.findByExample(0, 50, examples);
	}
    
    public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}

	public Programs getProgram() {
		return program;
	}

	public void setProgram(Programs program) {
		this.program = program;
	}

	public Set<Dictionary> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Dictionary> programs) {
		this.programs = programs;
	}

	public Set<Dictionary> getMajors() {
		return majors;
	}

	public void setMajors(Set<Dictionary> majors) {
		this.majors = majors;
	}

	public Set<Dictionary> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Dictionary> contacts) {
		this.contacts = contacts;
	}
	


}
