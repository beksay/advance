package org.infosystema.advance.domain.study_abroad;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.infosystema.advance.domain.MoneySimulation;

@Entity
@Table(name="step3")
public class Step3 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Set<MoneySimulation> applicationFees;
	
	@OneToMany(mappedBy="appfee")
	public Set<MoneySimulation> getApplicationFees() {
		return applicationFees;
	}
	 
	public void setApplicationFees(Set<MoneySimulation> applicationFees) {
		this.applicationFees = applicationFees;
	}
	
}
