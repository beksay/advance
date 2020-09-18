package org.infosystema.advance.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.advance.enums.CurrencyType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="programs")
public class Programs extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private Country country;
	private City city;
	private City state;
	private String universityName;
	private String universityShortName;
	private Set<Dictionary> programs;
	private Set<Dictionary> majors;
	private BigDecimal toefl;
	private BigDecimal tuitionFee;
	private CurrencyType currency;
	private String bankStatement;
	private Integer age;
	private BigDecimal applicationFee;
	private Set<Dictionary> contacts;
	private String website;
	private Date deadline;
	private Date deadline2;
	private Boolean toeflExist;
	private String note;
	private User user;
	private Date dateCreated;
	private Date dateModify;
	
	@Column(name="university_name")
	public String getUniversityName() {
		return universityName;
	}
	
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	@Column(name="university_short_name")
	public String getUniversityShortName() {
		return universityShortName;
	}
	
	public void setUniversityShortName(String universityShortName) {
		this.universityShortName = universityShortName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="date_modify")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn (name="country_id")
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne
	@JoinColumn (name="city_id")
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	@ManyToOne
	@JoinColumn (name="state_id")
	public City getState() {
		return state;
	}
	
	public void setState(City state) {
		this.state = state;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "programs_programs", 
	  joinColumns = @JoinColumn(name = "program_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Dictionary> programs) {
		this.programs = programs;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "program_majors", 
	  joinColumns = @JoinColumn(name = "program_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getMajors() {
		return majors;
	}

	public void setMajors(Set<Dictionary> majors) {
		this.majors = majors;
	}

	public BigDecimal getToefl() {
		return toefl;
	}

	public void setToefl(BigDecimal toefl) {
		this.toefl = toefl;
	}

	@Column(name="tuition_fee")
	public BigDecimal getTuitionFee() {
		return tuitionFee;
	}

	public void setTuitionFee(BigDecimal tuitionFee) {
		this.tuitionFee = tuitionFee;
	}

	@Enumerated(EnumType.ORDINAL)
	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	@Column(name="bank_statement")
	public String getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(String bankStatement) {
		this.bankStatement = bankStatement;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name="application_fee")
	public BigDecimal getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(BigDecimal applicationFee) {
		this.applicationFee = applicationFee;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "program_contacts", 
	  joinColumns = @JoinColumn(name = "program_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Dictionary> contacts) {
		this.contacts = contacts;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name="toefl_exist")
	public Boolean getToeflExist() {
		return toeflExist;
	}

	public void setToeflExist(Boolean toeflExist) {
		this.toeflExist = toeflExist;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeadline2() {
		return deadline2;
	}

	public void setDeadline2(Date deadline2) {
		this.deadline2 = deadline2;
	}
	
	
}