package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.MoneySimulationDao;
import org.infosystema.advance.dao.impl.MoneySimulationDaoImpl;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.service.MoneySimulationService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(MoneySimulationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MoneySimulationServiceImpl extends GenericServiceImpl<MoneySimulation, Integer, MoneySimulationDao> implements MoneySimulationService {

	@Override
	protected MoneySimulationDao getDao() {
		return new MoneySimulationDaoImpl(em, se);
	}

}
