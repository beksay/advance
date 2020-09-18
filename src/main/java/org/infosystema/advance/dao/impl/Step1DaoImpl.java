package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step1Dao;
import org.infosystema.advance.domain.study_abroad.Step1;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step1DaoImpl extends GenericDaoImpl<Step1, Integer> implements Step1Dao {

	public Step1DaoImpl(EntityManager entityManager, Event<Step1> eventSource) {
		super(entityManager, eventSource);
	}

}
