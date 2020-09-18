package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ParentsDao;
import org.infosystema.advance.dao.impl.ParentsDaoImpl;
import org.infosystema.advance.domain.study_abroad.Parents;
import org.infosystema.advance.service.ParentsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ParentsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ParentsServiceImpl extends GenericServiceImpl<Parents, Integer, ParentsDao> implements ParentsService {

	@Override
	protected ParentsDao getDao() {
		return new ParentsDaoImpl(em, se);
	}

}
