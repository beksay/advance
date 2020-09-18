package org.infosystema.advance.controller.finance;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.conversation.ConversationMovement;
import org.infosystema.advance.domain.Movement;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.service.MovementService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;


@ManagedBean
@ViewScoped
public class MovementController {

	@EJB
	private MovementService movementService;
	@Inject
	private EntityValidator validator;
	@Inject
	private ConversationMovement conversation;	
	@Inject
	private LoginUtil loginUtil;	
	
	private Movement movement;
    
	@PostConstruct
	public void init() {
		movement=conversation.getMovement();
		if(movement==null) movement= new Movement();
	}
	
	public String add() {
		movement = new Movement();
		conversation.setMovement(movement);
		return form();
	}
	
	public String edit(Movement movement) {
		this.movement = movement;
		conversation.setMovement(movement);
		return form();
	}
	
	public String save() {
		System.out.println(movement);
		if(movement == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}

    	movement.setUser(loginUtil.getCurrentUser());

		validator.validate(movement);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		if(movement.getId() == null) {
			//movement.setDateCreated(new Date());
			movementService.persist(movement); 
		}else {
			movement.setDateModify(new Date());
			movementService.merge(movement);
		}
		
		conversation.setMovement(null);
		
	    return cancel();  
		
	}
	
	public String delete(Movement c) {
		System.out.println(c);
		movementService.remove(c);
		return cancel();
	}
	
	public String cancel() {
		movement = null;
		return list();
	}
	
	private String list() {
		return "/view/movement/movement_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/movement/movement_form.xhtml";
	}
	
	public List<AccountType> getAccountTypeList() {
		return Arrays.asList(AccountType.values());
	}
	
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

}
