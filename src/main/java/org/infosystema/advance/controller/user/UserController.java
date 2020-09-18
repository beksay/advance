package org.infosystema.advance.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.beans.SortEnum;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.enums.ScopeConstants;
import org.infosystema.advance.enums.UserStatus;
import org.infosystema.advance.service.UserService;
import org.infosystema.advance.singleton.Configuration;
import org.infosystema.advance.util.web.FacesScopeQualifier;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.util.web.ScopeQualifier;


/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@ManagedBean
@RequestScoped
public class UserController {
	@EJB
	private UserService userService;
    private String username;
    private String password;
    
    @Inject
    private LoginUtil loginUtil;
  
    public void register() {
    	
    }

    public String login() throws Exception{
    	if( username.equals("") ) {
    		return null;
		} else if ( password.equals("") ) {
			return null;
		}
		
    	System.out.println("login:" + username);
    	
		String hashPassword = loginUtil.getHashPassword(password);
    	
    	System.out.println("password:" + password + " hash = " + hashPassword);
    	
    	List<User> users = userService.findByProperty("username", username);
    	if(users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameIsIncorrect"), null) );
			return null;
    	}
    	
		List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("username", username, InequalityConstants.EQUAL, true));	
		examples.add(new FilterExample("password", hashPassword, InequalityConstants.EQUAL));
		
		List<User> userList = userService.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");
		System.out.println("userList: " + userList);
		
		if(userList.isEmpty()){
			User user = users.get(0);
			user.setCountFailed(user.getCountFailed() == null ? 1 : user.getCountFailed() + 1);
			userService.merge(user);
			
			if(user.getCountFailed() >= 3){
				user.setStatus(UserStatus.BLOCKED);
				userService.merge(user);
				
				FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
				FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/user/blocked.xhtml");
			}
			
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameOrPasswordIncorrect"), null) );
			return null;
		}
			
		User user = userList.get(0);
		
		if(user.getStatus() == null || user.getStatus().equals(UserStatus.INACTIVE) || user.getStatus().equals(UserStatus.BLOCKED)){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
			FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/user/blocked.xhtml");
			
			return null;
		}
		
		loginUtil.setCurrentUser(user);
		
		if(user.getDatePasswordExpired() == null || user.getDatePasswordExpired().getTime()  <= System.currentTimeMillis()){
			ScopeQualifier qualifier = new FacesScopeQualifier();
			qualifier.setValue("changePassword", true, ScopeConstants.SESSION_SCOPE);
			return "/view/user/change_password.xhtml";
		}
		
		String address = loginUtil.userHasRole(user, "admin") ? "/" + Configuration.getInstance().getProperty("projectName") 
				+ "/view/main.xhtml" : "/" + Configuration.getInstance().getProperty("projectName") + "/view/main.xhtml";
		
		user.setCountFailed(0);
		userService.merge(user);
		
    	FacesContext.getCurrentInstance().getExternalContext().redirect(address);
		return address;
    }
    
    public String logout() {
		loginUtil.logout();
		return "/view/user/login.xhtml";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
