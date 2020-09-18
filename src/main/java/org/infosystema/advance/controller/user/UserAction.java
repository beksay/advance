package org.infosystema.advance.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.conversation.ConversationUser;
import org.infosystema.advance.domain.Role;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.domain.UserPerson;
import org.infosystema.advance.enums.UserStatus;
import org.infosystema.advance.service.RoleService;
import org.infosystema.advance.service.UserPersonService;
import org.infosystema.advance.service.UserService;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.validator.EntityValidator;


@ManagedBean
@ViewScoped
public class UserAction {

	@EJB
	private UserService service;
	@EJB
	private UserPersonService personService;
	@EJB
	private RoleService roleService;
	
	@Inject
	private EntityValidator validator;
	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationUser conversation;	
	
	private User user;
	private UserPerson person;
	private Role role;
	
	private String username;
    private String password;
	
    
	@PostConstruct
	public void init() {
		user=conversation.getUser();
		if (user==null)	user= new User();
		role=conversation.getRole();
		person=conversation.getPerson();
		if (person==null)	person= new UserPerson();
	}
	
	public String add() {
		user = new User();
		conversation.setUser(user);
		return form();
	}
	
	public String edit(User user) {
		this.setPerson(user.getPerson());
		this.setRole(user.getRole());
		this.user = user;
		conversation.setUser(user);
		conversation.setRole(role);
		conversation.setPerson(person);
		
		return form();
	}
	
	public String reg() {
		return form();
	}
	
	public String registerLogin(){
		return registerLog();
	}
	
	public String saveUser() {
		System.out.println(user);
		if(user == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
		List<User> users = service.findByProperty("username", username);
    	if(users.size()>0){
    		FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameIsAlreadyExists"), null) );
			return null;
    	}
		
        user.setPerson(person);
		user.setStatus(UserStatus.ACTIVE);
		user.setCountFailed(0);
		validator.validate(user);
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		user.setPerson(user.getPerson().getId() == null ? personService.persist(user.getPerson()) : personService.merge(user.getPerson()));
		if(user.getId() == null) {
			try {
				String hashPassword = loginUtil.getHashPassword("123");
				user.setPassword(hashPassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
			service.persist(user);
		}else {
			service.merge(user);
		}

		conversation.setUser(null);
		conversation.setRole(null);
		
		return cancel();  
	}
	
	public String delete(User c) {
		System.out.println(c);
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		user = null;
		return list();
	}
	
	public String returnHome() {
		user = null;
		return home();
	}
	
	private String list() {
		return "/view/user/user_journal.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/user/user_form.xhtml";
	}
	
	private String registerLog(){
		return "/view/public/registration.xhtml";
	}
	
	private String home(){
		return "/home.xhtml";
	}
	
	public String block(User user) {
		user.setCountFailed(0);
		user.setStatus(UserStatus.INACTIVE);
		service.merge(user);		
		return "user_journal.xhtml?faces-redirect=true";
	}
	
	public String unblock(User user) {
		user.setCountFailed(0);
		user.setStatus(UserStatus.ACTIVE);
		service.merge(user);
		return "ujer_journal.xhtml?faces-redirect=true";
	}
	
	public String refreshPassword(User user) {
		setPassword("123");
		try {
			String hashPassword = loginUtil.getHashPassword(password);
			user.setPassword(hashPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		user.setCountFailed(0);
		user.setStatus(UserStatus.ACTIVE);
		service.merge(user);	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "123!","123!"));
		return "user_journal.xhtml?faces-redirect=true";
	}
	
	public List<Role> getAvailableRoles() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("name", "admin", InequalityConstants.NOT_EQUAL));
		return roleService.findByExample(0, 10, examples);
	}
	
	public void deletePassword(User user){
		user.setPassword("2dcb6c95b1fdda5605aa58356915327d95e8b11ad729d67255aa1b934f7c904467aa47d3cc1590b838162428f15c5bbe1fb45fc351a1e92f9003e0366749c2f8");
		service.merge(user);
		
		FacesMessage message = new FacesMessage(Messages.getMessage("passwordChanged"));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserPerson getPerson() {
		return person;
	}
	
	public void setPerson(UserPerson person) {
		this.person = person;
	}

}
