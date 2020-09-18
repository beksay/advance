package org.infosystema.advance.controller.finance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.conversation.ConversationMoney;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.AdminType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.PersonService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;


@ManagedBean
@ViewScoped
public class IncomeController {

	@EJB
	private MoneySimulationService moneyService;
	@EJB
	private PersonService personService;
	@Inject
	private EntityValidator validator;
	@Inject
	private ConversationMoney conversation;	
	@Inject
	private LoginUtil loginUtil;	
	
	private MoneySimulation money;
    
	@PostConstruct
	public void init() {
		money=conversation.getMoneySimulation();
		if(money==null) money= new MoneySimulation();
	}
	
	public String add() {
		money = new MoneySimulation();
		conversation.setMoneySimulation(money);
		return form();
	}
	
	public String edit(MoneySimulation money) {
		this.money = money;
		conversation.setMoneySimulation(money);
		return form();
	}
	
	public String save() {
		System.out.println(money);
		if(money == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
		
    	money.setSimulationType(SimulationType.INCOME);
    	money.setUser(loginUtil.getCurrentUser());

		validator.validate(money);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		if(money.getId() == null) {
			//money.setDateCreated(new Date());
			moneyService.persist(money); 
		}else {
			money.setDateModify(new Date());
			moneyService.merge(money);
		}
		
		conversation.setMoneySimulation(null);
		
	    return cancel();  
		
	}
	
	public String delete(MoneySimulation c) {
		System.out.println(c);
		moneyService.remove(c);
		return cancel();
	}
	
	public String cancel() {
		money = null;
		return list();
	}
	
	private String list() {
		return "/view/income/income_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/income/income_form.xhtml";
	}
	
	public List<AccountType> getAccountTypeList() {
		return Arrays.asList(AccountType.values());
	}
	
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}
	
	public List<AdminType> getAdminTypeList() {
		return Arrays.asList(AdminType.values());
	}
	
	public List<CenterType> getCenterTypeList() {
		return Arrays.asList(CenterType.values());
	}
	
	public List<Person> getPersonList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("deal", true, InequalityConstants.EQUAL));
		examples.add(new FilterExample(true, "lastname", '%' + query + '%', InequalityConstants.LIKE, true));
		examples.add(new FilterExample(true, "firstname", '%' + query + '%', InequalityConstants.LIKE, true));
		examples.add(new FilterExample(true, "patronymic", '%' + query + '%', InequalityConstants.LIKE, true));
		return personService.findByExample(0, 10, examples);
	}

	public MoneySimulation getMoney() {
		return money;
	}

	public void setMoney(MoneySimulation money) {
		this.money = money;
	}

}
