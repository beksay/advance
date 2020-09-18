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
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step10;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.Step10Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step10Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step10Service moduleService;
	private Step10 module;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {

	}
	
	public String edit(Module module) {
		this.module = (Step10)module;
		
		return "sevis_fee.xhtml";
	}
	
	public String save() {
		module.setUser(loginUtil.getCurrentUser());
		module.setDateCreated(new Date());
		if(module.getId()==null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}
		
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
		
		return "sevis_fee.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "sevis_fee.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "sevis_fee.xhtml";
	}
	
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}
	
	public Step10 getModule() {
		return module;
	}
	
	public void setModule(Step10 module) {
		this.module = module;
	}
	
}
