package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step2Dao;
import org.infosystema.advance.domain.study_abroad.Step2;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step2DaoImpl extends GenericDaoImpl<Step2, Integer> implements Step2Dao {

	public Step2DaoImpl(EntityManager entityManager, Event<Step2> eventSource) {
		super(entityManager, eventSource);
	}

}
