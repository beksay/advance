package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step4Dao;
import org.infosystema.advance.domain.study_abroad.Step4;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step4DaoImpl extends GenericDaoImpl<Step4, Integer> implements Step4Dao {

	public Step4DaoImpl(EntityManager entityManager, Event<Step4> eventSource) {
		super(entityManager, eventSource);
	}

}
