package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step13Dao;
import org.infosystema.advance.dao.impl.Step13DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step13;
import org.infosystema.advance.service.Step13Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step13Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step13ServiceImpl extends GenericServiceImpl<Step13, Integer, Step13Dao> implements Step13Service {

	@Override
	protected Step13Dao getDao() {
		return new Step13DaoImpl(em, se);
	}

}
