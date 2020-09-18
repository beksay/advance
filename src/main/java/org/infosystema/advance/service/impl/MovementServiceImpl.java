package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.MovementDao;
import org.infosystema.advance.dao.impl.MovementDaoImpl;
import org.infosystema.advance.domain.Movement;
import org.infosystema.advance.service.MovementService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(MovementService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MovementServiceImpl extends GenericServiceImpl<Movement, Integer, MovementDao> implements MovementService {

	@Override
	protected MovementDao getDao() {
		return new MovementDaoImpl(em, se);
	}

}
