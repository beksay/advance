package org.infosystema.advance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.infosystema.advance.enums.UserStatus;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="username"))
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private UserStatus status;
	private Integer countFailed;
	private Role role;
	private UserPerson person;

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@Column(name="count_failed")
	public Integer getCountFailed() {
		return countFailed;
	}
	
	public void setCountFailed(Integer countFailed) {
		this.countFailed = countFailed;
	}
	
	@ManyToOne
	@JoinColumn (name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn (name="person_id")
	public UserPerson getPerson() {
		return person;
	}
	
	public void setPerson(UserPerson person) {
		this.person = person;
	}
	
}