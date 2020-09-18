package org.infosystema.advance.domain.study_abroad;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="step15")
public class Step15 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Set<VisaQuestion> visaQuestions;
	
	@OneToMany(mappedBy="module")
	public Set<VisaQuestion> getVisaQuestions() {
		return visaQuestions;
	}
	 
	public void setVisaQuestions(Set<VisaQuestion> visaQuestions) {
		this.visaQuestions = visaQuestions;
	}
	
}
