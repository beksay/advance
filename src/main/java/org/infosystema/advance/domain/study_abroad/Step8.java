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
import org.infosystema.advance.domain.User;

@Entity
@Table(name="step8")
public class Step8 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Attachment dsFile;
	private Attachment dsFile2;
	private Date dateCreated;
	private Date dateCreated2;
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
	@JoinColumn (name="ds_file_id")
	public Attachment getDsFile() {
		return dsFile;
	}
	
	public void setDsFile(Attachment dsFile) {
		this.dsFile = dsFile;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@ManyToOne
	@JoinColumn (name="ds_file2_id")
	public Attachment getDsFile2() {
		return dsFile2;
	}

	public void setDsFile2(Attachment dsFile2) {
		this.dsFile2 = dsFile2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created2")
	public Date getDateCreated2() {
		return dateCreated2;
	}

	public void setDateCreated2(Date dateCreated2) {
		this.dateCreated2 = dateCreated2;
	}
	
}
