package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step9Dao;
import org.infosystema.advance.domain.study_abroad.Step9;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step9DaoImpl extends GenericDaoImpl<Step9, Integer> implements Step9Dao {

	public Step9DaoImpl(EntityManager entityManager, Event<Step9> eventSource) {
		super(entityManager, eventSource);
	}

}
