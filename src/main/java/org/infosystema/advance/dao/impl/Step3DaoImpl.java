package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step3Dao;
import org.infosystema.advance.domain.study_abroad.Step3;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step3DaoImpl extends GenericDaoImpl<Step3, Integer> implements Step3Dao {

	public Step3DaoImpl(EntityManager entityManager, Event<Step3> eventSource) {
		super(entityManager, eventSource);
	}

}
