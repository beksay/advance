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
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="i_20")
public class I20 extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private Step7 module;
	private Attachment i20Doc;
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
	public Step7 getModule() {
		return module;
	}
	
	public void setModule(Step7 module) {
		this.module = module;
	}
	
	@ManyToOne
	@JoinColumn (name="i20_doc_id")
	public Attachment getI20Doc() {
		return i20Doc;
	}
	
	public void setI20Doc(Attachment i20Doc) {
		this.i20Doc = i20Doc;
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