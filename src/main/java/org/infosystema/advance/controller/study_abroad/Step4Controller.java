package org.infosystema.advance.controller.study_abroad;

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
import org.infosystema.advance.domain.study_abroad.ApplicationFee;
import org.infosystema.advance.domain.study_abroad.ApplicationSubmission;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step4;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.ApplicationSubmissionService;
import org.infosystema.advance.service.Step4Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step4Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ApplicationSubmissionService service;
	@EJB
	private Step4Service moduleService;
	private Step4 module;
	private List<ApplicationSubmission> list;
	private ApplicationSubmission applicationSubmission;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {
		if (applicationSubmission==null) applicationSubmission= new ApplicationSubmission();
	}
	
	public String edit(Module module) {
		this.module = (Step4)module;
		list = service.findByProperty("module", module);
		return "application_submission.xhtml";
	}	
	
	public String save() {
		if (applicationSubmission.getId()==null) {
			applicationSubmission.setUser(loginUtil.getCurrentUser());
			applicationSubmission.setDateCreated(new Date());
			applicationSubmission.setModule(module);
			applicationSubmission = service.persist(applicationSubmission);
			
			list.add(applicationSubmission);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			applicationSubmission = service.merge(applicationSubmission);
			list = service.findByProperty("module", applicationSubmission.getModule());
		}
		
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        applicationSubmission = new ApplicationSubmission();
		
		return "application_submission.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "application_submission.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "application_submission.xhtml";
	}
    
    public String editData(ApplicationSubmission applicationSubmission) {
		this.applicationSubmission = service.findById(applicationSubmission.getId(), false);
		return "application_submission.xhtml";
	}
    
    public String delete(ApplicationSubmission applicationSubmission) {
		System.out.println(applicationSubmission);
		service.remove(applicationSubmission);
		list = service.findByProperty("module", applicationSubmission.getModule());
		return "application_submission.xhtml";
	}
	
	public Step4 getModule() {
		return module;
	}
	
	public void setModule(Step4 module) {
		this.module = module;
	}

	public ApplicationSubmission getApplicationSubmission() {
		return applicationSubmission;
	}

	public void setApplicationSubmission(ApplicationSubmission applicationSubmission) {
		this.applicationSubmission = applicationSubmission;
	}

	public List<ApplicationSubmission> getList() {
		return list;
	}

	public void setList(List<ApplicationSubmission> list) {
		this.list = list;
	}
	
}
