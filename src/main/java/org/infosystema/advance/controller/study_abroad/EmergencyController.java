package org.infosystema.advance.controller.study_abroad;

import java.util.ArrayList;
import java.util.Date;
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
import org.infosystema.advance.conversation.ConversationPerson;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.study_abroad.Emergency;
import org.infosystema.advance.service.EmergencyService;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;

@Named
@ConversationScoped
public class EmergencyController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private EmergencyService emergencyService;
	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationPerson conversationPerson;
	
	private Emergency emergency;
	private List<Emergency> emergencyList;
	
	@PostConstruct
	public void init() {
        if(emergency==null) emergency = new Emergency();
        List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("person", conversationPerson.getPerson(), InequalityConstants.EQUAL));
		emergencyList =  emergencyService.findByExample(0, 10, examples);
	}
	
	public String edit(Emergency emergency) {
		this.emergency = emergency;
		return "emergency_contact.xhtml";
	}
	
	public String save() {
		emergency.setUser(loginUtil.getCurrentUser());
		emergency.setDateCreated(new Date());
		emergency.setPerson(conversationPerson.getPerson());
		if(emergency.getId()==null) {
			emergency = emergencyService.persist(emergency);
		} else {
			emergency = emergencyService.merge(emergency);
		}
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
        emergency = new Emergency();
        
        List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("person", conversationPerson.getPerson(), InequalityConstants.EQUAL));
		emergencyList =  emergencyService.findByExample(0, 10, examples);
		
		return "emergency_contact.xhtml";
	}
	
    public String delete(Emergency emergency) {
		emergencyService.remove(emergency);
		FacesMessage msg = new FacesMessage(Messages.getMessage("deleted").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("person", conversationPerson.getPerson(), InequalityConstants.EQUAL));
		emergencyList =  emergencyService.findByExample(0, 10, examples);
        
		return "emergency_contact.xhtml";
	}

	public ConversationPerson getConversationPerson() {
		return conversationPerson;
	}

	public void setConversationPerson(ConversationPerson conversationPerson) {
		this.conversationPerson = conversationPerson;
	}

	public Emergency getEmergency() {
		return emergency;
	}

	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}

	public List<Emergency> getEmergencyList() {
		return emergencyList;
	}

	public void setEmergencyList(List<Emergency> emergencyList) {
		this.emergencyList = emergencyList;
	}
	
}
