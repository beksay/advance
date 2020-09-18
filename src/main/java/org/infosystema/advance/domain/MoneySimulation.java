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

import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step3;
import org.infosystema.advance.domain.study_abroad.Step6;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.AdminType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.FeeType;
import org.infosystema.advance.enums.SimulationType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="money_simulation")
public class MoneySimulation extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private SimulationType simulationType;
	private AccountType accountType;
	private BigDecimal amount;
	private CurrencyType currencyType;
	private CenterType centerType;
	private AdminType adminType;
	private FeeType feeType;
	private String note;
	private Date dateCreated;
	private Date dateModify;
	private User user;
	private Person person;
	private Step3 appfee;
	private Step6 shipfee;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="simulation_type")
	public SimulationType getSimulationType() {
		return simulationType;
	}

	public void setSimulationType(SimulationType simulationType) {
		this.simulationType = simulationType;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="account_type")
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="fee_type")
	public FeeType getFeeType() {
		return feeType;
	}
	
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
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

	@Enumerated(EnumType.ORDINAL)
	@Column(name="center_type")
	public CenterType getCenterType() {
		return centerType;
	}

	public void setCenterType(CenterType centerType) {
		this.centerType = centerType;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="admin_type")
	public AdminType getAdminType() {
		return adminType;
	}

	public void setAdminType(AdminType adminType) {
		this.adminType = adminType;
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

	@ManyToOne
	@JoinColumn(name="person_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne
	@JoinColumn (name="appfee_id")
	public Step3 getAppfee() {
		return appfee;
	}

	public void setAppfee(Step3 appfee) {
		this.appfee = appfee;
	}

	@ManyToOne
	@JoinColumn (name="shipfee_id")
	public Step6 getShipfee() {
		return shipfee;
	}

	public void setShipfee(Step6 shipfee) {
		this.shipfee = shipfee;
	}
	
}