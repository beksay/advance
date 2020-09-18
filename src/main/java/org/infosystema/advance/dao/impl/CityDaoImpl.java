package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.CityDao;
import org.infosystema.advance.domain.City;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class CityDaoImpl extends GenericDaoImpl<City, Integer> implements CityDao {

	public CityDaoImpl(EntityManager entityManager, Event<City> eventSource) {
		super(entityManager, eventSource);
	}

}
