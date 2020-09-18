package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.UserPersonDao;
import org.infosystema.advance.domain.UserPerson;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class UserPersonDaoImpl extends GenericDaoImpl<UserPerson, Integer> implements UserPersonDao {

	public UserPersonDaoImpl(EntityManager entityManager, Event<UserPerson> eventSource) {
		super(entityManager, eventSource);
	}

}
