package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step16Dao;
import org.infosystema.advance.dao.impl.Step16DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step16;
import org.infosystema.advance.service.Step16Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step16Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step16ServiceImpl extends GenericServiceImpl<Step16, Integer, Step16Dao> implements Step16Service {

	@Override
	protected Step16Dao getDao() {
		return new Step16DaoImpl(em, se);
	}

}
