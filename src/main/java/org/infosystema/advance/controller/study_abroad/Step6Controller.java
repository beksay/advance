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
import org.infosystema.advance.domain.study_abroad.ShippingFee;
import org.infosystema.advance.domain.study_abroad.Step6;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.FeeType;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.Step6Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step6Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private MoneySimulationService service;
	@EJB
	private Step6Service moduleService;
	private Step6 module;
	private List<MoneySimulation> list;
	private MoneySimulation shippingFee;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {
		if (shippingFee==null) shippingFee= new MoneySimulation();
	}
	
	public String edit(Module module) {
		this.module = (Step6)module;
		
		list = service.findByProperty("shipfee", module);
		
		return "shipping_fee.xhtml";
	}
	
	public List<MoneySimulation> getList() {
		return list;
	}
	
	public String save() {
		if (shippingFee.getId()==null) {
			shippingFee.setUser(loginUtil.getCurrentUser());
			shippingFee.setDateCreated(new Date());
			shippingFee.setShipfee(module);
			shippingFee.setCenterType(CenterType.SAC);
			shippingFee.setFeeType(FeeType.SHIPPING_FEE);
			shippingFee.setSimulationType(SimulationType.OUTCOUME);
			shippingFee.setPerson(module.getPerson());
			shippingFee = service.persist(shippingFee);
			
			list.add(shippingFee);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			shippingFee = service.merge(shippingFee);
			list = service.findByProperty("shipfee", shippingFee.getShipfee());
		}
		
		
		shippingFee = new MoneySimulation();
		
		return "shipping_fee.xhtml";
	}
	
     public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "shipping_fee.xhtml";
	}
     
     public String sendToEdit() {
    		if(module.getId()!=null) {	
    			module.setStatus(ModuleStatus.FILLED);
    			module = moduleService.merge(module);
    			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
    	        FacesContext.getCurrentInstance().addMessage(null, msg); 
    		}
 		return "shipping_fee.xhtml";
 	}
     
     public String delete(MoneySimulation shippingFee) {
 		System.out.println(shippingFee);
 		service.remove(shippingFee);
 		list = service.findByProperty("shipfee", shippingFee.getShipfee());
 		return "shipping_fee.xhtml";
 	}
     
     public String editData(ShippingFee shippingFee) {
 		this.shippingFee = service.findById(shippingFee.getId(), false);
 		return "shipping_fee.xhtml";
 	}
	
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}
	
	 public List<AccountType> getAccountTypeList() {
			return Arrays.asList(AccountType.values());
		}

	public MoneySimulation getShippingFee() {
		return shippingFee;
	}
	
	public void setShippingFee(MoneySimulation shippingFee) {
		this.shippingFee = shippingFee;
	}
	
	public Step6 getModule() {
		return module;
	}
	
	public void setModule(Step6 module) {
		this.module = module;
	}
}
