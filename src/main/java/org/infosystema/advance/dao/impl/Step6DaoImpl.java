package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step6Dao;
import org.infosystema.advance.domain.study_abroad.Step6;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step6DaoImpl extends GenericDaoImpl<Step6, Integer> implements Step6Dao {

	public Step6DaoImpl(EntityManager entityManager, Event<Step6> eventSource) {
		super(entityManager, eventSource);
	}

}
