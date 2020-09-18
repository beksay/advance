package org.infosystema.advance.domain.study_abroad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.advance.domain.Attachment;

@Entity
@Table(name="step12")
public class Step12 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Attachment appointment;
	private Attachment sevisFee;
	private Attachment bankStatement;
	private Attachment sponsors;
	private Attachment portfolio;
	private Attachment certificate;
	private Attachment ticket;
	private Attachment realEstate;
	
    private Boolean passport;
    private Boolean i20;
    private Boolean ds160;
    private Boolean schoolDiploma;
    private Boolean schoolTranscript;
    private Boolean visaFee;
    private Boolean photo;
    private Boolean toefl;
    private Boolean parents;

	public Boolean getPassport() {
		return passport;
	}

	public void setPassport(Boolean passport) {
		this.passport = passport;
	}

	@ManyToOne
	@JoinColumn (name="appointment_id")
	public Attachment getAppointment() {
		return appointment;
	}

	public void setAppointment(Attachment appointment) {
		this.appointment = appointment;
	}

	@ManyToOne
	@JoinColumn (name="sevis_fee_id")
	public Attachment getSevisFee() {
		return sevisFee;
	}

	public void setSevisFee(Attachment sevisFee) {
		this.sevisFee = sevisFee;
	}

	@ManyToOne
	@JoinColumn (name="sponsors_id")
	public Attachment getSponsors() {
		return sponsors;
	}

	public void setSponsors(Attachment sponsors) {
		this.sponsors = sponsors;
	}

	@ManyToOne
	@JoinColumn (name="bank_id")
	public Attachment getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(Attachment bankStatement) {
		this.bankStatement = bankStatement;
	}

	@ManyToOne
	@JoinColumn (name="portfolio_id")
	public Attachment getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Attachment portfolio) {
		this.portfolio = portfolio;
	}

	@ManyToOne
	@JoinColumn (name="certificate_id")
	public Attachment getCertificate() {
		return certificate;
	}

	public void setCertificate(Attachment certificate) {
		this.certificate = certificate;
	}

	@ManyToOne
	@JoinColumn (name="ticket_id")
	public Attachment getTicket() {
		return ticket;
	}

	public void setTicket(Attachment ticket) {
		this.ticket = ticket;
	}

	@ManyToOne
	@JoinColumn (name="real_estate_id")
	public Attachment getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(Attachment realEstate) {
		this.realEstate = realEstate;
	}

	public Boolean getI20() {
		return i20;
	}

	public void setI20(Boolean i20) {
		this.i20 = i20;
	}

	public Boolean getDs160() {
		return ds160;
	}

	public void setDs160(Boolean ds160) {
		this.ds160 = ds160;
	}

	@Column(name="school_diploma")
	public Boolean getSchoolDiploma() {
		return schoolDiploma;
	}

	public void setSchoolDiploma(Boolean schoolDiploma) {
		this.schoolDiploma = schoolDiploma;
	}

	@Column(name="school_transcript")
	public Boolean getSchoolTranscript() {
		return schoolTranscript;
	}

	public void setSchoolTranscript(Boolean schoolTranscript) {
		this.schoolTranscript = schoolTranscript;
	}

	@Column(name="visa_fee")
	public Boolean getVisaFee() {
		return visaFee;
	}

	public void setVisaFee(Boolean visaFee) {
		this.visaFee = visaFee;
	}

	public Boolean getPhoto() {
		return photo;
	}

	public void setPhoto(Boolean photo) {
		this.photo = photo;
	}

	public Boolean getToefl() {
		return toefl;
	}

	public void setToefl(Boolean toefl) {
		this.toefl = toefl;
	}

	public Boolean getParents() {
		return parents;
	}

	public void setParents(Boolean parents) {
		this.parents = parents;
	}
}
