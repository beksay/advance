package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.ShippingFeeDao;
import org.infosystema.advance.domain.study_abroad.ShippingFee;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ShippingFeeDaoImpl extends GenericDaoImpl<ShippingFee, Integer> implements ShippingFeeDao {

	public ShippingFeeDaoImpl(EntityManager entityManager, Event<ShippingFee> eventSource) {
		super(entityManager, eventSource);
	}

}
