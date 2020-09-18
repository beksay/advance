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
import org.infosystema.advance.domain.study_abroad.I20;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step15;
import org.infosystema.advance.domain.study_abroad.VisaQuestion;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.Step15Service;
import org.infosystema.advance.service.VisaQuestionService;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class Step15Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private VisaQuestionService service;
	@EJB
	private Step15Service moduleService;
	private Step15 module;
	private List<VisaQuestion> list;
	private VisaQuestion visaQuestion;
	@Inject
	private LoginUtil loginUtil;
	
	@PostConstruct
	public void init() {
		if (visaQuestion==null) visaQuestion= new VisaQuestion();
	}
	
	public String edit(Module module) {
		this.module = (Step15)module;
		
		list = service.findByProperty("module", module);
		
		return "interview_question.xhtml";
	}
	
	public List<VisaQuestion> getList() {
		return list;
	}
	
	public String save() {
		if (visaQuestion.getId()==null) {
			visaQuestion.setUser(loginUtil.getCurrentUser());
			visaQuestion.setDateCreated(new Date());
			visaQuestion.setModule(module);
			visaQuestion = service.persist(visaQuestion);
			
			list.add(visaQuestion);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		} else {
			visaQuestion = service.merge(visaQuestion);
			list = service.findByProperty("module", visaQuestion.getModule());
		}
		
		
		visaQuestion = new VisaQuestion();
		
		return "interview_question.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "interview_question.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "interview_question.xhtml";
	}
    
    public String delete(VisaQuestion visaQuestion) {
		System.out.println(visaQuestion);
		service.remove(visaQuestion);
		list = service.findByProperty("module", visaQuestion.getModule());
		return "interview_question.xhtml";
	}
    
    public String editData(VisaQuestion visaQuestion) {
		this.visaQuestion = service.findById(visaQuestion.getId(), false);
		return "interview_question.xhtml";
	}

	public VisaQuestion getVisaQuestion() {
		return visaQuestion;
	}

	public void setVisaQuestion(VisaQuestion visaQuestion) {
		this.visaQuestion = visaQuestion;
	}
	
	public Step15 getModule() {
		return module;
	}
	
	public void setModule(Step15 module) {
		this.module = module;
	}
}
