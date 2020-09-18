package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step14Dao;
import org.infosystema.advance.domain.study_abroad.Step14;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step14DaoImpl extends GenericDaoImpl<Step14, Integer> implements Step14Dao {

	public Step14DaoImpl(EntityManager entityManager, Event<Step14> eventSource) {
		super(entityManager, eventSource);
	}

}
