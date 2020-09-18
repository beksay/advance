package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.Step11Dao;
import org.infosystema.advance.domain.study_abroad.Step11;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class Step11DaoImpl extends GenericDaoImpl<Step11, Integer> implements Step11Dao {

	public Step11DaoImpl(EntityManager entityManager, Event<Step11> eventSource) {
		super(entityManager, eventSource);
	}

}
