package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.CountryDao;
import org.infosystema.advance.domain.Country;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class CountryDaoImpl extends GenericDaoImpl<Country, Integer> implements CountryDao {

	public CountryDaoImpl(EntityManager entityManager, Event<Country> eventSource) {
		super(entityManager, eventSource);
	}

}
