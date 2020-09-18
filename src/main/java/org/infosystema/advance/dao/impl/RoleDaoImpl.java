package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.RoleDao;
import org.infosystema.advance.domain.Role;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

	public RoleDaoImpl(EntityManager entityManager, Event<Role> eventSource) {
		super(entityManager, eventSource);
	}

}
