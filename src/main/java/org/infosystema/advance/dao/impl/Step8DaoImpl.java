package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step8Dao;
import org.infosystema.advance.domain.study_abroad.Step8;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step8DaoImpl extends GenericDaoImpl<Step8, Integer> implements Step8Dao {

	public Step8DaoImpl(EntityManager entityManager, Event<Step8> eventSource) {
		super(entityManager, eventSource);
	}

}
