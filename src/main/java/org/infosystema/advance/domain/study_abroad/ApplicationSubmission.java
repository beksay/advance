package org.infosystema.advance.domain.study_abroad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.advance.domain.AbstractEntity;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.enums.UniversityStatus;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="application_submission")
public class ApplicationSubmission extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private Step4 module;
	private String universityName;
	private Date dateCreated;
	private Date dateModify;
	private UniversityStatus status;
	private User user;
	private User userStatus;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn (name="user_status_id")
	public User getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(User userStatus) {
		this.userStatus = userStatus;
	}

	@ManyToOne
	@JoinColumn (name="module_id")
	public Step4 getModule() {
		return module;
	}
	
	public void setModule(Step4 module) {
		this.module = module;
	}

	@Column(name="university_name")
	public String getUniversityName() {
		return universityName;
	}
	
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_modify")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public UniversityStatus getStatus() {
		return status;
	}

	public void setStatus(UniversityStatus status) {
		this.status = status;
	}
	
}