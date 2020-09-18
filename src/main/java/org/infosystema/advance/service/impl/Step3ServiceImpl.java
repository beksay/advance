package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step3Dao;
import org.infosystema.advance.dao.impl.Step3DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step3;
import org.infosystema.advance.service.Step3Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step3Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step3ServiceImpl extends GenericServiceImpl<Step3, Integer, Step3Dao> implements Step3Service {

	@Override
	protected Step3Dao getDao() {
		return new Step3DaoImpl(em, se);
	}

}
