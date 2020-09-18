package org.infosystema.advance.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.conversation.ConversationDict;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;


@ManagedBean
@ViewScoped
public class DictionaryController {

	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private EntityValidator validator;
	@Inject
	private ConversationDict conversation;	
	@Inject
	private DictionaryTypeController dictionaryTypeController;
	
	private Dictionary dictionary;
    
	@PostConstruct
	public void init() {
		dictionary=conversation.getDictionary();
		if (dictionary==null)	dictionary= new Dictionary();
	}
	
	public String add() {
		dictionary = new Dictionary();
		conversation.setDictionary(dictionary);
		return form();
	}
	
	public String edit(Dictionary dictionary) {
		this.dictionary = dictionary;
		conversation.setDictionary(dictionary);
		return form();
	}
	
	public String save() {
		System.out.println(dictionary);
		if(dictionary == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
		List<Dictionary> identifiers = dictionaryService.findByProperty("name", dictionary.getName());
    	if(identifiers.equals(0)){
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("nameAlreadyExists"), null) );
			return null;
    	}
    	
    	dictionary.setDictionaryType(dictionaryTypeController.getDictionaryType());
		validator.validate(dictionary);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		dictionary = dictionary.getId() == null ? dictionaryService.persist(dictionary) : dictionaryService.merge(dictionary);
		
		conversation.setDictionary(null);
		
	    return cancel();  
		
	}
	
	public String delete(Dictionary c) {
		System.out.println(c);
		dictionaryService.remove(c);
		return cancel();
	}
	
	public String cancel() {
		dictionary = null;
		return list();
	}
	
	private String list() {
		return "/view/dictionary/dictionary_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/dictionary/dictionary_form.xhtml";
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public DictionaryTypeController getDictionaryTypeController() {
		return dictionaryTypeController;
	}

	public void setDictionaryTypeController(DictionaryTypeController dictionaryTypeController) {
		this.dictionaryTypeController = dictionaryTypeController;
	}

}
