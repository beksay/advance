package org.infosystema.advance.domain.study_abroad;

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
import javax.persistence.Transient;

import org.infosystema.advance.domain.AbstractEntity;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.enums.DiscountType;
import org.infosystema.advance.enums.PersonStatus;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="person")
public class Person extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private String pin;
	private Date dateCreated;
	private Date dateModify;
	private PersonStatus status;
	private String firstname;
	private String lastname;
	private String patronymic;
	private Dictionary countryBirth;
	private String city;
	private String address;
	private String email;
	private String phone;
	private Dictionary mentor;
	private Dictionary citizenship;
	private Dictionary english;
	private String abroad;
	private String finance;
	private String cu;
	private Date birthDate;
	private Attachment profile;
	private User user;
	private BigDecimal contract;
	private BigDecimal discount;
	private DiscountType discountType;
	private String discountReason;
	private Boolean deal;
	private String toefl;
	private String ielts;
	private Parents father;
	private Parents mother;
	private Boolean visaAcceptance;
	private Boolean visaRejection;
	private Boolean immediateRelatives;
	private Boolean anyRelatives;
	private String weOccupation;
	private String weEmployer;
	private String weCountry;
	private String weCity;
	private String weAddress;
	private String weDuties;
	
	private Set<Dictionary> countries;
	private Set<Dictionary> programs;
	private Set<Dictionary> majors;
	private Set<Dictionary> semesters;
	private Set<Dictionary> languages;
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
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

	@Enumerated(EnumType.ORDINAL)
	public PersonStatus getStatus() {
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn (name="profile_id")
	public Attachment getProfile() {
		return profile;
	}
	
	public void setProfile(Attachment profile) {
		this.profile = profile;
	}
	
	@ManyToOne
	@JoinColumn (name="father_id")
	public Parents getFather() {
		return father;
	}
	
	public void setFather(Parents father) {
		this.father = father;
	}
	
	@ManyToOne
	@JoinColumn (name="mother_id")
	public Parents getMother() {
		return mother;
	}
	
	public void setMother(Parents mother) {
		this.mother = mother;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	@JoinColumn (name="mentor_id")
	public Dictionary getMentor() {
		return mentor;
	}
	
	public void setMentor(Dictionary mentor) {
		this.mentor = mentor;
	}

	public String getCu() {
		return cu;
	}

	public void setCu(String cu) {
		this.cu = cu;
	}
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Transient
	public String getFullname() {
		StringBuffer buffer = new StringBuffer();
		
		if(lastname != null) buffer.append(lastname + " ");
		if(firstname != null) buffer.append(firstname + " ");
		if(patronymic != null) buffer.append(patronymic + " ");
		
		return buffer.toString();
	}

	@ManyToOne
	@JoinColumn (name="country_birth_id")
	public Dictionary getCountryBirth() {
		return countryBirth;
	}

	public void setCountryBirth(Dictionary countryBirth) {
		this.countryBirth = countryBirth;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_programs", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Dictionary> programs) {
		this.programs = programs;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_majors", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getMajors() {
		return majors;
	}

	public void setMajors(Set<Dictionary> majors) {
		this.majors = majors;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_countries", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getCountries() {
		return countries;
	}

	public void setCountries(Set<Dictionary> countries) {
		this.countries = countries;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToOne
	@JoinColumn (name="citizenship_id")
	public Dictionary getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Dictionary citizenship) {
		this.citizenship = citizenship;
	}

	@ManyToOne
	@JoinColumn (name="english_id")
	public Dictionary getEnglish() {
		return english;
	}

	public void setEnglish(Dictionary english) {
		this.english = english;
	}

	public String getAbroad() {
		return abroad;
	}

	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}

	public String getFinance() {
		return finance;
	}

	public void setFinance(String finance) {
		this.finance = finance;
	}

	public BigDecimal getContract() {
		return contract;
	}

	public void setContract(BigDecimal contract) {
		this.contract = contract;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Boolean getDeal() {
		return deal;
	}

	public void setDeal(Boolean deal) {
		this.deal = deal;
	}

	public String getToefl() {
		return toefl;
	}

	public void setToefl(String toefl) {
		this.toefl = toefl;
	}

	public String getIelts() {
		return ielts;
	}

	public void setIelts(String ielts) {
		this.ielts = ielts;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_semesters", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getSemesters() {
		return semesters;
	}

	public void setSemesters(Set<Dictionary> semesters) {
		this.semesters = semesters;
	}
	
	@Column(name="discount_reason")
	public String getDiscountReason() {
		return discountReason;
	}
	
	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="discount_type")
	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	@Column(name="visa_acceptance")
	public Boolean getVisaAcceptance() {
		return visaAcceptance;
	}

	public void setVisaAcceptance(Boolean visaAcceptance) {
		this.visaAcceptance = visaAcceptance;
	}

	@Column(name="visa_rejection")
	public Boolean getVisaRejection() {
		return visaRejection;
	}

	public void setVisaRejection(Boolean visaRejection) {
		this.visaRejection = visaRejection;
	}

	@Column(name="immediate_relatives")
	public Boolean getImmediateRelatives() {
		return immediateRelatives;
	}

	public void setImmediateRelatives(Boolean immediateRelatives) {
		this.immediateRelatives = immediateRelatives;
	}

	@Column(name="any_relatives")
	public Boolean getAnyRelatives() {
		return anyRelatives;
	}

	public void setAnyRelatives(Boolean anyRelatives) {
		this.anyRelatives = anyRelatives;
	}

	@Column(name="we_occupation")
	public String getWeOccupation() {
		return weOccupation;
	}

	public void setWeOccupation(String weOccupation) {
		this.weOccupation = weOccupation;
	}

	@Column(name="we_employer")
	public String getWeEmployer() {
		return weEmployer;
	}

	public void setWeEmployer(String weEmployer) {
		this.weEmployer = weEmployer;
	}

	@Column(name="we_country")
	public String getWeCountry() {
		return weCountry;
	}

	public void setWeCountry(String weCountry) {
		this.weCountry = weCountry;
	}

	@Column(name="we_city")
	public String getWeCity() {
		return weCity;
	}

	public void setWeCity(String weCity) {
		this.weCity = weCity;
	}

	@Column(name="we_address")
	public String getWeAddress() {
		return weAddress;
	}

	public void setWeAddress(String weAddress) {
		this.weAddress = weAddress;
	}

	@Column(name="we_duties")
	public String getWeDuties() {
		return weDuties;
	}

	public void setWeDuties(String weDuties) {
		this.weDuties = weDuties;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_languages", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Dictionary> languages) {
		this.languages = languages;
	}
	
	
}