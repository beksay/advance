package org.infosystema.advance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="dict")
public class Dictionary extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	private String shortName;
	private Boolean active;
	private DictionaryType dictionaryType;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="short_name")
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne
	@JoinColumn (name="dict_type_id")
	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	@Override
	public String toString() {
		return "Dictionary [name=" + name + ", shortName=" + shortName + ", active=" + active + ", dictionaryType="
				+ dictionaryType + "]";
	}
	
}