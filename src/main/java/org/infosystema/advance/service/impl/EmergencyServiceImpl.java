package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.EmergencyDao;
import org.infosystema.advance.dao.impl.EmergencyDaoImpl;
import org.infosystema.advance.domain.study_abroad.Emergency;
import org.infosystema.advance.service.EmergencyService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(EmergencyService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmergencyServiceImpl extends GenericServiceImpl<Emergency, Integer, EmergencyDao> implements EmergencyService {

	@Override
	protected EmergencyDao getDao() {
		return new EmergencyDaoImpl(em, se);
	}

}
