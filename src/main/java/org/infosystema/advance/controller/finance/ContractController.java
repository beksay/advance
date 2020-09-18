package org.infosystema.advance.controller.finance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.beans.SortEnum;
import org.infosystema.advance.controller.base.BasePersonController;
import org.infosystema.advance.conversation.ConversationPerson;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step1;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.FeeType;
import org.infosystema.advance.enums.ScopeConstants;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.service.ModuleService;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.PaymentsService;
import org.infosystema.advance.service.PersonService;
import org.infosystema.advance.service.Step1Service;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.FacesScopeQualifier;
import org.infosystema.advance.util.web.ScopeQualifier;
import org.primefaces.event.SelectEvent;


@Named
@RequestScoped
public class ContractController extends BasePersonController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PersonService personService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private Step1Service step1Service;
	@EJB
	private ModuleService moduleService;
	@EJB
	private MoneySimulationService moneyService;
	@EJB
	private PaymentsService paymentsService;
	
	private BigDecimal appFee;
	private BigDecimal shippingFee;
	private BigDecimal somPayment;
	private BigDecimal dollarPayment;
	private BigDecimal euroPayment;

	@Inject
	private ConversationPerson conversation;	
	
	public void onRowSelect(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		try {
			if (conversation.getPerson().getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(conversation.getPerson().getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		conversation.setPerson(conversation.getPerson());
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"programs","majors","countries","semesters"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/contract/main.xhtml?cid="+conversation.getId());
        
    }
	
	public String mainProfile(Module module) {
		conversation.setPerson(service.getByIdWithFields(module.getPerson().getId(), new String[] {"programs","majors","countries","semesters"}));
		conversation.setCountries(new HashSet<Dictionary>()); conversation.getCountries().addAll(conversation.getPerson().getCountries());
		conversation.setPrograms(new HashSet<Dictionary>()); conversation.getPrograms().addAll(conversation.getPerson().getPrograms());
		conversation.setMajors(new HashSet<Dictionary>()); conversation.getMajors().addAll(conversation.getPerson().getMajors());
		return "/view/person/details/main.xhtml?faces-redirect=true";
	}
	
	public List<Module> getModules() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer id = qualifier.getValue(PERSON_KEY, ScopeConstants.SESSION_SCOPE);
		List<Module> modules = qualifier.getValue(id + "_", ScopeConstants.REQUEST_SCOPE);
		
		if(modules == null) {
			List<FilterExample> list = new ArrayList<>();
			list.add(new FilterExample("person.id", id, InequalityConstants.EQUAL));
			modules = moduleService.findByExample(0, 200, SortEnum.ASCENDING, list, "index");
			
			qualifier.setValue(id + "_", modules, ScopeConstants.REQUEST_SCOPE);
		}
		
		System.out.println("modules = " + modules + " id = " + id);
		
		return modules;
	}
	
	public String editProfile() {
		return "main.xhtml";
	}
	
	public String editContract() {
		return "deal.xhtml";
	}
	
	
	public String cancel() {
		conversation.setPerson(null);
		return list();
	}
	
	private String list() {
		return "/view/contract/person_list.xhtml?faces-redirect=true";
	}
    
    public List<Dictionary> getCountryList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getDict(Integer dictType){
		List<FilterExample> filter = new ArrayList<>();
		filter.add(new FilterExample("dictionaryType.id", dictType, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 1000, SortEnum.ASCENDING, filter, "name");
	}
    
    public List<Step1> step1(Person person) {
		return step1Service.findByProperty("person", person);
	}
	
	public ConversationPerson getConversation() {
		return conversation;
	}
	
	public void setConversation(ConversationPerson conversation) {
		this.conversation = conversation;
	}

	public BigDecimal getAppFee(Person person) {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("appfee.person", person, InequalityConstants.EQUAL));
		filters.add(new FilterExample("feeType", FeeType.APPLICATION_FEE, InequalityConstants.EQUAL));
		appFee=moneyService.sumByExample("amount", filters);
		return appFee;
	}
	
	public BigDecimal getTotalAppFee() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("feeType", FeeType.APPLICATION_FEE, InequalityConstants.EQUAL));
		appFee=moneyService.sumByExample("amount", filters);
		return appFee;
	}

	public BigDecimal getShippingFee(Person person) {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("shipfee.person", person, InequalityConstants.EQUAL));
		filters.add(new FilterExample("feeType", FeeType.SHIPPING_FEE, InequalityConstants.EQUAL));
		shippingFee=moneyService.sumByExample("amount", filters);
		return shippingFee;
	}
	
	public BigDecimal getTotalShippingFee() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("feeType", FeeType.SHIPPING_FEE, InequalityConstants.EQUAL));
		shippingFee=moneyService.sumByExample("amount", filters);
		return shippingFee;
	}

	public BigDecimal getSomPayment(Person person) {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("person", person, InequalityConstants.EQUAL));
		filters.add(new FilterExample("currency", CurrencyType.SOM, InequalityConstants.EQUAL));
		somPayment=paymentsService.sumByExample("amount", filters);
		return somPayment;
	}
	
	public BigDecimal getDollarPayment(Person person) {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("person", person, InequalityConstants.EQUAL));
		filters.add(new FilterExample("currency", CurrencyType.DOLLAR, InequalityConstants.EQUAL));
		dollarPayment=paymentsService.sumByExample("amount", filters);
		return dollarPayment;
	}
	
	public BigDecimal getTotalDollarPayment() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("currency", CurrencyType.DOLLAR, InequalityConstants.EQUAL));
		dollarPayment=paymentsService.sumByExample("amount", filters);
		return dollarPayment;
	}
	
	public BigDecimal getEuroPayment(Person person) {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("person", person, InequalityConstants.EQUAL));
		filters.add(new FilterExample("currency", CurrencyType.EURO, InequalityConstants.EQUAL));
		euroPayment=paymentsService.sumByExample("amount", filters);
		return euroPayment;
	}

}
