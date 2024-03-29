package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.PersonDao;
import org.infosystema.advance.domain.study_abroad.Person;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class PersonDaoImpl extends GenericDaoImpl<Person, Integer> implements PersonDao {

	public PersonDaoImpl(EntityManager entityManager, Event<Person> eventSource) {
		super(entityManager, eventSource);
	}

}
