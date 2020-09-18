package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step16Dao;
import org.infosystema.advance.domain.study_abroad.Step16;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step16DaoImpl extends GenericDaoImpl<Step16, Integer> implements Step16Dao {

	public Step16DaoImpl(EntityManager entityManager, Event<Step16> eventSource) {
		super(entityManager, eventSource);
	}

}
