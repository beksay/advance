package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.UserDao;
import org.infosystema.advance.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl(EntityManager entityManager, Event<User> eventSource) {
		super(entityManager, eventSource);
	}

}
