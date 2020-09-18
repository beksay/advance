package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step13Dao;
import org.infosystema.advance.domain.study_abroad.Step13;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step13DaoImpl extends GenericDaoImpl<Step13, Integer> implements Step13Dao {

	public Step13DaoImpl(EntityManager entityManager, Event<Step13> eventSource) {
		super(entityManager, eventSource);
	}

}
