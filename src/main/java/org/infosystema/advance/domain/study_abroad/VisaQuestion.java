package org.infosystema.advance.domain.study_abroad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.advance.domain.AbstractEntity;
import org.infosystema.advance.domain.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="visa_question")
public class VisaQuestion extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private Step15 module;
	private String question;
	private String answer;
	private Date dateCreated;
	private Date dateModify;
	private User user;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn (name="module_id")
	public Step15 getModule() {
		return module;
	}
	
	public void setModule(Step15 module) {
		this.module = module;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
	
}