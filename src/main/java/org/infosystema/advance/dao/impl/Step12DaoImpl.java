package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step12Dao;
import org.infosystema.advance.domain.study_abroad.Step12;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step12DaoImpl extends GenericDaoImpl<Step12, Integer> implements Step12Dao {

	public Step12DaoImpl(EntityManager entityManager, Event<Step12> eventSource) {
		super(entityManager, eventSource);
	}

}
