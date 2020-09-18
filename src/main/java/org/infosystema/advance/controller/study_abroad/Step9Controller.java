package org.infosystema.advance.controller.study_abroad;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step9;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.Step9Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step9Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step9Service moduleService;
	private Step9 module;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String edit(Module module) {
		this.module = (Step9)module;
		
		return "embassy_appointment.xhtml";
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
		
		return "embassy_appointment.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "embassy_appointment.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "embassy_appointment.xhtml";
	}
	
	public Step9 getModule() {
		return module;
	}
	
	public void setModule(Step9 module) {
		this.module = module;
	}
	
}
