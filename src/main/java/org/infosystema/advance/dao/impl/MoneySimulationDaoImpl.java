package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.MoneySimulationDao;
import org.infosystema.advance.domain.MoneySimulation;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class MoneySimulationDaoImpl extends GenericDaoImpl<MoneySimulation, Integer> implements MoneySimulationDao {

	public MoneySimulationDaoImpl(EntityManager entityManager, Event<MoneySimulation> eventSource) {
		super(entityManager, eventSource);
	}

}
