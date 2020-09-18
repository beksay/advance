package org.infosystema.advance.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="user_person")
public class UserPerson extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Transient
	public String getFullname() {
		StringBuffer buffer = new StringBuffer();
		
		if(lastname != null) buffer.append(lastname + " ");
		if(firstname != null) buffer.append(firstname + " ");
		
		return buffer.toString();
	}
}