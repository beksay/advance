package org.infosystema.advance.domain.study_abroad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.advance.domain.User;

@Entity
@Table(name="step9")
public class Step9 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Date embassyAppointment;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="embassy_appointment_date")
	public Date getEmbassyAppointment() {
		return embassyAppointment;
	}

	public void setEmbassyAppointment(Date embassyAppointment) {
		this.embassyAppointment = embassyAppointment;
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
