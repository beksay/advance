package org.infosystema.advance.domain.study_abroad;

import java.math.BigDecimal;
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
import org.infosystema.advance.enums.CurrencyType;

@Entity
@Table(name="step10")
public class Step10 extends Module {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal sevis;
	private CurrencyType currency;
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
	public CurrencyType getCurrency() {
		return currency;
	}
	
	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}
	
	public BigDecimal getSevis() {
		return sevis;
	}
	
	public void setSevis(BigDecimal sevis) {
		this.sevis = sevis;
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
