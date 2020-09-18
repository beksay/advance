package org.infosystema.advance.domain.study_abroad;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="step7")
public class Step7 extends Module {

	private static final long serialVersionUID = 1L;
	
    private Set<I20> i20s;
	
	@OneToMany(mappedBy="module")
	public Set<I20> getI20s() {
		return i20s;
	} 
	
	public void setI20s(Set<I20> i20s) {
		this.i20s = i20s;
	}
	
}
