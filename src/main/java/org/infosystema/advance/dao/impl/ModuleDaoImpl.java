package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ModuleDao;
import org.infosystema.advance.domain.study_abroad.Module;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ModuleDaoImpl extends GenericDaoImpl<Module, Integer> implements ModuleDao {

	public ModuleDaoImpl(EntityManager entityManager, Event<Module> eventSource) {
		super(entityManager, eventSource);
	}

}
