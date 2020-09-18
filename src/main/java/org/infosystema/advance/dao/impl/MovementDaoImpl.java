package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.MovementDao;
import org.infosystema.advance.domain.Movement;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class MovementDaoImpl extends GenericDaoImpl<Movement, Integer> implements MovementDao {

	public MovementDaoImpl(EntityManager entityManager, Event<Movement> eventSource) {
		super(entityManager, eventSource);
	}

}
