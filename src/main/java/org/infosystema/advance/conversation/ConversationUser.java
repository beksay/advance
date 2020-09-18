package org.infosystema.advance.conversation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Role;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.domain.UserPerson;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationUser extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private User user;
	private UserPerson person;
	private Role role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
