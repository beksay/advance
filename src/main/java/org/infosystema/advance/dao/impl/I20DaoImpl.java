package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.I20Dao;
import org.infosystema.advance.domain.study_abroad.I20;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class I20DaoImpl extends GenericDaoImpl<I20, Integer> implements I20Dao {

	public I20DaoImpl(EntityManager entityManager, Event<I20> eventSource) {
		super(entityManager, eventSource);
	}

}
