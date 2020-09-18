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

@Entity
@Table(name="step2")
public class Step2 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Attachment passport;
	private Attachment picture;
	private Attachment bankStatement;
	private Attachment parents;
	private Attachment transcript;
	private Attachment schoolDiploma;
	private Attachment universityDiploma;
	private Attachment motivation;
	private Attachment recomendation;
	private Attachment toefl;
	private Attachment health;
	private Attachment employment;
	private Attachment gre;
	private Attachment autobiography;
	private Attachment sat;
	private Attachment financial;
	
	private Date passportDate;
	private Date pictureDate;
	private Date bankStatementDate;
	private Date parentsDate;
	private Date transcriptDate;
	private Date schoolDiplomaDate;
	private Date universityDiplomaDate;
	private Date motivationDate;
	private Date recomendationDate;
	private Date toeflDate;
	private Date healthDate;
	private Date employmentDate;
	private Date greDate;
	private Date autobiographyDate;
	private Date satDate;
	private Date financialDate;
	
	@ManyToOne
	@JoinColumn (name="passport_id")
	public Attachment getPassport() {
		return passport;
	}
	
	public void setPassport(Attachment passport) {
		this.passport = passport;
	}
	
	@ManyToOne
	@JoinColumn (name="picture_id")
	public Attachment getPicture() {
		return picture;
	}
	
	public void setPicture(Attachment picture) {
		this.picture = picture;
	}
	
	@ManyToOne
	@JoinColumn (name="bank_statement_id")
	public Attachment getBankStatement() {
		return bankStatement;
	}
	
	public void setBankStatement(Attachment bankStatement) {
		this.bankStatement = bankStatement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="passport_date")
	public Date getPassportDate() {
		return passportDate;
	}

	public void setPassportDate(Date passportDate) {
		this.passportDate = passportDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="picture_date")
	public Date getPictureDate() {
		return pictureDate;
	}

	public void setPictureDate(Date pictureDate) {
		this.pictureDate = pictureDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="bank_statement_date")
	public Date getBankStatementDate() {
		return bankStatementDate;
	}

	public void setBankStatementDate(Date bankStatementDate) {
		this.bankStatementDate = bankStatementDate;
	}

	@ManyToOne
	@JoinColumn (name="parents_id")
	public Attachment getParents() {
		return parents;
	}

	public void setParents(Attachment parents) {
		this.parents = parents;
	}

	@ManyToOne
	@JoinColumn (name="transcript_id")
	public Attachment getTranscript() {
		return transcript;
	}

	public void setTranscript(Attachment transcript) {
		this.transcript = transcript;
	}

	@ManyToOne
	@JoinColumn (name="school_diploma_id")
	public Attachment getSchoolDiploma() {
		return schoolDiploma;
	}

	public void setSchoolDiploma(Attachment schoolDiploma) {
		this.schoolDiploma = schoolDiploma;
	}

	@ManyToOne
	@JoinColumn (name="university_diploma_id")
	public Attachment getUniversityDiploma() {
		return universityDiploma;
	}

	public void setUniversityDiploma(Attachment universityDiploma) {
		this.universityDiploma = universityDiploma;
	}

	@ManyToOne
	@JoinColumn (name="motivation_id")
	public Attachment getMotivation() {
		return motivation;
	}

	public void setMotivation(Attachment motivation) {
		this.motivation = motivation;
	}

	@ManyToOne
	@JoinColumn (name="recomendation_id")
	public Attachment getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(Attachment recomendation) {
		this.recomendation = recomendation;
	}

	@ManyToOne
	@JoinColumn (name="toefl_id")
	public Attachment getToefl() {
		return toefl;
	}

	public void setToefl(Attachment toefl) {
		this.toefl = toefl;
	}

	@ManyToOne
	@JoinColumn (name="health_id")
	public Attachment getHealth() {
		return health;
	}

	public void setHealth(Attachment health) {
		this.health = health;
	}

	@ManyToOne
	@JoinColumn (name="employment_id")
	public Attachment getEmployment() {
		return employment;
	}

	public void setEmployment(Attachment employment) {
		this.employment = employment;
	}

	@ManyToOne
	@JoinColumn (name="gre_id")
	public Attachment getGre() {
		return gre;
	}

	public void setGre(Attachment gre) {
		this.gre = gre;
	}

	@ManyToOne
	@JoinColumn (name="autobiography_id")
	public Attachment getAutobiography() {
		return autobiography;
	}

	public void setAutobiography(Attachment autobiography) {
		this.autobiography = autobiography;
	}

	@ManyToOne
	@JoinColumn (name="sat_id")
	public Attachment getSat() {
		return sat;
	}

	public void setSat(Attachment sat) {
		this.sat = sat;
	}

	@ManyToOne
	@JoinColumn (name="financial_id")
	public Attachment getFinancial() {
		return financial;
	}

	public void setFinancial(Attachment financial) {
		this.financial = financial;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="parents_date")
	public Date getParentsDate() {
		return parentsDate;
	}

	public void setParentsDate(Date parentsDate) {
		this.parentsDate = parentsDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="transcript_date")
	public Date getTranscriptDate() {
		return transcriptDate;
	}

	public void setTranscriptDate(Date transcriptDate) {
		this.transcriptDate = transcriptDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="school_diploma_date")
	public Date getSchoolDiplomaDate() {
		return schoolDiplomaDate;
	}

	public void setSchoolDiplomaDate(Date schoolDiplomaDate) {
		this.schoolDiplomaDate = schoolDiplomaDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="university_diploma_date")
	public Date getUniversityDiplomaDate() {
		return universityDiplomaDate;
	}

	public void setUniversityDiplomaDate(Date universityDiplomaDate) {
		this.universityDiplomaDate = universityDiplomaDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="motivation_date")
	public Date getMotivationDate() {
		return motivationDate;
	}

	public void setMotivationDate(Date motivationDate) {
		this.motivationDate = motivationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="recomendation_date")
	public Date getRecomendationDate() {
		return recomendationDate;
	}

	public void setRecomendationDate(Date recomendationDate) {
		this.recomendationDate = recomendationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="toefl_date")
	public Date getToeflDate() {
		return toeflDate;
	}

	public void setToeflDate(Date toeflDate) {
		this.toeflDate = toeflDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="health_date")
	public Date getHealthDate() {
		return healthDate;
	}

	public void setHealthDate(Date healthDate) {
		this.healthDate = healthDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="employment_date")
	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gre_date")
	public Date getGreDate() {
		return greDate;
	}

	public void setGreDate(Date greDate) {
		this.greDate = greDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="autobiography_date")
	public Date getAutobiographyDate() {
		return autobiographyDate;
	}

	public void setAutobiographyDate(Date autobiographyDate) {
		this.autobiographyDate = autobiographyDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sat_date")
	public Date getSatDate() {
		return satDate;
	}

	public void setSatDate(Date satDate) {
		this.satDate = satDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="financial_date")
	public Date getFinancialDate() {
		return financialDate;
	}

	public void setFinancialDate(Date financialDate) {
		this.financialDate = financialDate;
	}
}
