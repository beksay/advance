package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.PaymentsDao;
import org.infosystema.advance.domain.study_abroad.Payments;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class PaymentsDaoImpl extends GenericDaoImpl<Payments, Integer> implements PaymentsDao {

	public PaymentsDaoImpl(EntityManager entityManager, Event<Payments> eventSource) {
		super(entityManager, eventSource);
	}

}
