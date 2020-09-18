package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step15Dao;
import org.infosystema.advance.domain.study_abroad.Step15;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step15DaoImpl extends GenericDaoImpl<Step15, Integer> implements Step15Dao {

	public Step15DaoImpl(EntityManager entityManager, Event<Step15> eventSource) {
		super(entityManager, eventSource);
	}

}
