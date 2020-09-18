package org.infosystema.advance.controller.study_abroad;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step3;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.FeeType;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.Step3Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step3Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private MoneySimulationService service;
	@EJB
	private Step3Service moduleService;
	private Step3 module;
	private List<MoneySimulation> list;
	private MoneySimulation applicationFee;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {
		if (applicationFee==null) applicationFee= new MoneySimulation();
	}
	
	public String edit(Module module) {
		this.module = (Step3)module;
		
		list = service.findByProperty("appfee", module);
		
		return "application_fee.xhtml";
	}
	
	public List<MoneySimulation> getList() {
		return list;
	}
	
	public String save() {
		if (applicationFee.getId()==null) {
			applicationFee.setUser(loginUtil.getCurrentUser());
			applicationFee.setDateCreated(new Date());
			applicationFee.setAppfee(module);
			applicationFee.setCenterType(CenterType.SAC);
			applicationFee.setFeeType(FeeType.APPLICATION_FEE);
			applicationFee.setSimulationType(SimulationType.OUTCOUME);
			applicationFee.setPerson(module.getPerson());
			applicationFee = service.persist(applicationFee);
			
			list.add(applicationFee);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			applicationFee = service.merge(applicationFee);
			list = service.findByProperty("appfee", applicationFee.getAppfee());
		}
		
		applicationFee = new MoneySimulation();
		
		return "application_fee.xhtml";
	}
	
    public String complete() {
		System.out.println("COMPLETE METHOD+++++");
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "application_fee.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "application_fee.xhtml";
	}
    
    public String delete(MoneySimulation applicationFee) {
		System.out.println("applicationFee===" + applicationFee);
		service.remove(applicationFee);
		list = service.findByProperty("appfee", applicationFee.getAppfee());
		return "application_fee.xhtml";
	}
    
    public String editData(MoneySimulation applicationFee) {
		System.out.println("applicationFee===" + applicationFee);
		this.applicationFee = service.findById(applicationFee.getId(), false);
		return "application_fee.xhtml";
	}
	
    public List<AccountType> getAccountTypeList() {
		return Arrays.asList(AccountType.values());
	}
    
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}

	public MoneySimulation getApplicationFee() {
		return applicationFee;
	}
	
	public void setApplicationFee(MoneySimulation applicationFee) {
		this.applicationFee = applicationFee;
	}
	
	public Step3 getModule() {
		return module;
	}
	
	public void setModule(Step3 module) {
		this.module = module;
	}
}
