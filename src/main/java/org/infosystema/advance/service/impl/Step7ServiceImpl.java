package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step7Dao;
import org.infosystema.advance.dao.impl.Step7DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step7;
import org.infosystema.advance.service.Step7Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step7Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step7ServiceImpl extends GenericServiceImpl<Step7, Integer, Step7Dao> implements Step7Service {

	@Override
	protected Step7Dao getDao() {
		return new Step7DaoImpl(em, se);
	}

}
