package org.infosystema.advance.domain.study_abroad;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.infosystema.advance.domain.MoneySimulation;

@Entity
@Table(name="step6")
public class Step6 extends Module {

	private static final long serialVersionUID = 1L;
	
	private Set<MoneySimulation> shippingFees;
	
	@OneToMany(mappedBy="shipfee")
	public Set<MoneySimulation> getShippingFees() {
		return shippingFees;
	}
	 
	public void setShippingFees(Set<MoneySimulation> shippingFees) {
		this.shippingFees = shippingFees;
	}
	
}
