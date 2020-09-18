package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.DictionaryTypeDao;
import org.infosystema.advance.domain.DictionaryType;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class DictionaryTypeDaoImpl extends GenericDaoImpl<DictionaryType, Integer> implements DictionaryTypeDao {

	public DictionaryTypeDaoImpl(EntityManager entityManager, Event<DictionaryType> eventSource) {
		super(entityManager, eventSource);
	}

}
