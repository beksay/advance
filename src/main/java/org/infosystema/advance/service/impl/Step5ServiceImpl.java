package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step5Dao;
import org.infosystema.advance.dao.impl.Step5DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step5;
import org.infosystema.advance.service.Step5Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step5Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step5ServiceImpl extends GenericServiceImpl<Step5, Integer, Step5Dao> implements Step5Service {

	@Override
	protected Step5Dao getDao() {
		return new Step5DaoImpl(em, se);
	}

}
