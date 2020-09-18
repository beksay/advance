package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ApplicationFeeDao;
import org.infosystema.advance.domain.study_abroad.ApplicationFee;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ApplicationFeeDaoImpl extends GenericDaoImpl<ApplicationFee, Integer> implements ApplicationFeeDao {

	public ApplicationFeeDaoImpl(EntityManager entityManager, Event<ApplicationFee> eventSource) {
		super(entityManager, eventSource);
	}

}
