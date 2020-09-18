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

import org.infosystema.advance.domain.User;
import org.infosystema.advance.enums.VisaStatus;

@Entity
@Table(name="step14")
public class Step14 extends Module {

	private static final long serialVersionUID = 1L;
	
	private VisaStatus visaStatus;
	private Date dateCreated;
	private User user;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="visa_status")
	public VisaStatus getVisaStatus() {
		return visaStatus;
	}
	
	public void setVisaStatus(VisaStatus visaStatus) {
		this.visaStatus = visaStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
