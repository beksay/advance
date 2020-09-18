package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step1Dao;
import org.infosystema.advance.dao.impl.Step1DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step1;
import org.infosystema.advance.service.Step1Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step1Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step1ServiceImpl extends GenericServiceImpl<Step1, Integer, Step1Dao> implements Step1Service {

	@Override
	protected Step1Dao getDao() {
		return new Step1DaoImpl(em, se);
	}

}
