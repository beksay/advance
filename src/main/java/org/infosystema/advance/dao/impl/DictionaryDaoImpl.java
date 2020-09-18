package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.DictionaryDao;
import org.infosystema.advance.domain.Dictionary;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary, Integer> implements DictionaryDao {

	public DictionaryDaoImpl(EntityManager entityManager, Event<Dictionary> eventSource) {
		super(entityManager, eventSource);
	}

}
