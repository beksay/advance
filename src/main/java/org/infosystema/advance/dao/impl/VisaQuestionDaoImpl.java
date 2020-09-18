package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.VisaQuestionDao;
import org.infosystema.advance.domain.study_abroad.VisaQuestion;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class VisaQuestionDaoImpl extends GenericDaoImpl<VisaQuestion, Integer> implements VisaQuestionDao {

	public VisaQuestionDaoImpl(EntityManager entityManager, Event<VisaQuestion> eventSource) {
		super(entityManager, eventSource);
	}

}
