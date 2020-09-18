package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ParentsDao;
import org.infosystema.advance.domain.study_abroad.Parents;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ParentsDaoImpl extends GenericDaoImpl<Parents, Integer> implements ParentsDao {

	public ParentsDaoImpl(EntityManager entityManager, Event<Parents> eventSource) {
		super(entityManager, eventSource);
	}

}
