package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ProgramsDao;
import org.infosystema.advance.domain.Programs;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ProgramsDaoImpl extends GenericDaoImpl<Programs, Integer> implements ProgramsDao {

	public ProgramsDaoImpl(EntityManager entityManager, Event<Programs> eventSource) {
		super(entityManager, eventSource);
	}

}
