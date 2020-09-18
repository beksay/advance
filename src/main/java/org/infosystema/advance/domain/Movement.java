package org.infosystema.advance.domain;

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

import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CurrencyType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="movement")
public class Movement extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private AccountType accountFrom;
	private AccountType accountTo;
	private BigDecimal amount;
	private CurrencyType currencyType;
	private String note;
	private Date dateCreated;
	private Date dateModify;
	private User user;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="account_to")
	public AccountType getAccountTo() {
		return accountTo;
	}
	
	public void setAccountTo(AccountType accountTo) {
		this.accountTo = accountTo;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="account_from")
	public AccountType getAccountFrom() {
		return accountFrom;
	}
	
	public void setAccountFrom(AccountType accountFrom) {
		this.accountFrom = accountFrom;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="currency_type")
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}