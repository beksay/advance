package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ApplicationSubmissionDao;
import org.infosystema.advance.domain.study_abroad.ApplicationSubmission;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ApplicationSubmissionDaoImpl extends GenericDaoImpl<ApplicationSubmission, Integer> implements ApplicationSubmissionDao {

	public ApplicationSubmissionDaoImpl(EntityManager entityManager, Event<ApplicationSubmission> eventSource) {
		super(entityManager, eventSource);
	}

}
