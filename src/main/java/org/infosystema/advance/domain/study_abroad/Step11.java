package org.infosystema.advance.domain.study_abroad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.User;

@Entity
@Table(name="step11")
public class Step11 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Date visaPreperation;
	private Date dateCreated;
	private User user;
	private Attachment caseFile;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="visa_preperation_date")
	public Date getVisaPreperation() {
		return visaPreperation;
	}

	public void setVisaPreperation(Date visaPreperation) {
		this.visaPreperation = visaPreperation;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@ManyToOne
	@JoinColumn (name="case_file_id")
	public Attachment getCaseFile() {
		return caseFile;
	}

	public void setCaseFile(Attachment caseFile) {
		this.caseFile = caseFile;
	}
}
