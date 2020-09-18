package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step2Dao;
import org.infosystema.advance.dao.impl.Step2DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step2;
import org.infosystema.advance.service.Step2Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step2Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step2ServiceImpl extends GenericServiceImpl<Step2, Integer, Step2Dao> implements Step2Service {

	@Override
	protected Step2Dao getDao() {
		return new Step2DaoImpl(em, se);
	}

}
