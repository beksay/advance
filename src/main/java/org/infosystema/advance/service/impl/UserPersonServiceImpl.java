package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.UserPersonDao;
import org.infosystema.advance.dao.impl.UserPersonDaoImpl;
import org.infosystema.advance.domain.UserPerson;
import org.infosystema.advance.service.UserPersonService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(UserPersonService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserPersonServiceImpl extends GenericServiceImpl<UserPerson, Integer, UserPersonDao> implements UserPersonService {

	@Override
	protected UserPersonDao getDao() {
		return new UserPersonDaoImpl(em, se);
	}

}
