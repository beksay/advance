package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step7Dao;
import org.infosystema.advance.domain.study_abroad.Step7;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step7DaoImpl extends GenericDaoImpl<Step7, Integer> implements Step7Dao {

	public Step7DaoImpl(EntityManager entityManager, Event<Step7> eventSource) {
		super(entityManager, eventSource);
	}

}
