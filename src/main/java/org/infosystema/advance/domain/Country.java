package org.infosystema.advance.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="country", uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Country extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}