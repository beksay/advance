package org.infosystema.advance.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.infosystema.advance.enums.CityType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="city", uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class City extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	private CityType type;
	private Country country;
	
	@Enumerated(EnumType.ORDINAL)
	public CityType getType() {
		return type;
	}
	
	public void setType(CityType type) {
		this.type = type;
	}
	
	@ManyToOne
	@JoinColumn (name="country_id")
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}