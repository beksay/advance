package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step5Dao;
import org.infosystema.advance.domain.study_abroad.Step5;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step5DaoImpl extends GenericDaoImpl<Step5, Integer> implements Step5Dao {

	public Step5DaoImpl(EntityManager entityManager, Event<Step5> eventSource) {
		super(entityManager, eventSource);
	}

}
