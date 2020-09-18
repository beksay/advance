package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.EmergencyDao;
import org.infosystema.advance.domain.study_abroad.Emergency;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class EmergencyDaoImpl extends GenericDaoImpl<Emergency, Integer> implements EmergencyDao {

	public EmergencyDaoImpl(EntityManager entityManager, Event<Emergency> eventSource) {
		super(entityManager, eventSource);
	}

}
