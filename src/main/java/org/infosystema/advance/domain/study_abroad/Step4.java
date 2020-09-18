package org.infosystema.advance.domain.study_abroad;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="step4")
public class Step4 extends Module {

	private static final long serialVersionUID = 1L;
	
    private Set<ApplicationSubmission> applicationSubmissions;
	
	@OneToMany(mappedBy="module")
	public Set<ApplicationSubmission> getApplicationSubmissions() {
		return applicationSubmissions;
	}
	
	public void setApplicationSubmissions(Set<ApplicationSubmission> applicationSubmissions) {
		this.applicationSubmissions = applicationSubmissions;
	}
	
}
