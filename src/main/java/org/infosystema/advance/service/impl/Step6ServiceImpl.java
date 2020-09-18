package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step6Dao;
import org.infosystema.advance.dao.impl.Step6DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step6;
import org.infosystema.advance.service.Step6Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step6Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step6ServiceImpl extends GenericServiceImpl<Step6, Integer, Step6Dao> implements Step6Service {

	@Override
	protected Step6Dao getDao() {
		return new Step6DaoImpl(em, se);
	}

}
