package org.infosystema.advance.conversation;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.study_abroad.Parents;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.dto.AttachmentBinaryDTO;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationPerson extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Person person;
	private AttachmentBinaryDTO profile;
	private Set<Dictionary> countries;
	private Set<Dictionary> programs;
	private Set<Dictionary> majors;
	private Set<Dictionary> semesters;
	private Set<Dictionary> languages;
	private Boolean dontHaveFather;
	private Boolean dontHaveMother;
	private Parents father;
	private Parents mother;
	private Dictionary countryBirth;
	private Dictionary mentor;
	private Dictionary citizenship;
	private Dictionary english;
	
	@PostConstruct
	public void init() {
		person = new Person();
		super.initialize();
	}

	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public AttachmentBinaryDTO getProfile() {
		return profile;
	}

	public void setProfile(AttachmentBinaryDTO profile) {
		this.profile = profile;
	}

	public Set<Dictionary> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Dictionary> programs) {
		this.programs = programs;
	}

	public Set<Dictionary> getMajors() {
		return majors;
	}

	public void setMajors(Set<Dictionary> majors) {
		this.majors = majors;
	}

	public Set<Dictionary> getCountries() {
		return countries;
	}

	public void setCountries(Set<Dictionary> countries) {
		this.countries = countries;
	}

	public Boolean getDontHaveFather() {
		return dontHaveFather;
	}

	public void setDontHaveFather(Boolean dontHaveFather) {
		this.dontHaveFather = dontHaveFather;
	}

	public Boolean getDontHaveMother() {
		return dontHaveMother;
	}

	public void setDontHaveMother(Boolean dontHaveMother) {
		this.dontHaveMother = dontHaveMother;
	}

	public Parents getFather() {
		return father;
	}

	public void setFather(Parents father) {
		this.father = father;
	}

	public Parents getMother() {
		return mother;
	}

	public void setMother(Parents mother) {
		this.mother = mother;
	}

	public Set<Dictionary> getSemesters() {
		return semesters;
	}

	public void setSemesters(Set<Dictionary> semesters) {
		this.semesters = semesters;
	}

	public Dictionary getCountryBirth() {
		return countryBirth;
	}

	public void setCountryBirth(Dictionary countryBirth) {
		this.countryBirth = countryBirth;
	}

	public Dictionary getMentor() {
		return mentor;
	}

	public void setMentor(Dictionary mentor) {
		this.mentor = mentor;
	}

	public Dictionary getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Dictionary citizenship) {
		this.citizenship = citizenship;
	}

	public Dictionary getEnglish() {
		return english;
	}

	public void setEnglish(Dictionary english) {
		this.english = english;
	}

	public Set<Dictionary> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Dictionary> languages) {
		this.languages = languages;
	}
	
}
