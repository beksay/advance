package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step10Dao;
import org.infosystema.advance.domain.study_abroad.Step10;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step10DaoImpl extends GenericDaoImpl<Step10, Integer> implements Step10Dao {

	public Step10DaoImpl(EntityManager entityManager, Event<Step10> eventSource) {
		super(entityManager, eventSource);
	}

}
