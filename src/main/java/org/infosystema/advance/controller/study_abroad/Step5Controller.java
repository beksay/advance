package org.infosystema.advance.controller.study_abroad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.study_abroad.ApplicationSubmission;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step5;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.UniversityStatus;
import org.infosystema.advance.service.ApplicationSubmissionService;
import org.infosystema.advance.service.Step5Service;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.primefaces.event.RowEditEvent;

@Named
@ConversationScoped
public class Step5Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ApplicationSubmissionService submissionService;
	@EJB
	private Step5Service moduleService;
	private Step5 module;
	@Inject
	private LoginUtil loginUtil;

	private ApplicationSubmission applicationSubmission;
	private List<ApplicationSubmission> list;
	
	@PostConstruct
	public void init() {
		if (applicationSubmission==null) applicationSubmission= new ApplicationSubmission();
		
	}
	
	public String edit(Module module) {
		this.module = (Step5)module;
		return "letter_of_acceptence.xhtml";
	}
	
    public String complete() {
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "letter_of_acceptence.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "letter_of_acceptence.xhtml";
	}
    
    public void onRowEdit(RowEditEvent event) {
		applicationSubmission=(ApplicationSubmission) event.getObject();
		System.out.println("applicationSubmission====="  +applicationSubmission);
		applicationSubmission.setStatus(UniversityStatus.ACCEPTED);
		applicationSubmission.setUserStatus(loginUtil.getCurrentUser());
		submissionService.merge(applicationSubmission);
        FacesMessage msg = new FacesMessage("Status accepted", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	applicationSubmission=(ApplicationSubmission) event.getObject();
    	applicationSubmission.setStatus(UniversityStatus.REJECTED);
    	applicationSubmission.setUserStatus(loginUtil.getCurrentUser());
    	submissionService.merge(applicationSubmission);
        FacesMessage msg = new FacesMessage("Status rejected", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public List<UniversityStatus> getUniversityStatusList() {
		return Arrays.asList(UniversityStatus.values());
	}
    
    public List<ApplicationSubmission> getApplicationSubmissionList(Person person) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("module.person", person, InequalityConstants.EQUAL));
		return submissionService.findByExample(0, 10, examples);
	}
	
	public Step5 getModule() {
		return module;
	}
	
	public void setModule(Step5 module) {
		this.module = module;
	}

	public ApplicationSubmission getApplicationSubmission() {
		return applicationSubmission;
	}

	public void setApplicationSubmission(ApplicationSubmission applicationSubmission) {
		this.applicationSubmission = applicationSubmission;
	}

	public List<ApplicationSubmission> getList(Person person) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("module.person", person, InequalityConstants.EQUAL));
		list = submissionService.findByExample(0, 10, examples);
		return list;
	}

	public void setList(List<ApplicationSubmission> list) {
		this.list = list;
	}
}
