package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step4Dao;
import org.infosystema.advance.dao.impl.Step4DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step4;
import org.infosystema.advance.service.Step4Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step4Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step4ServiceImpl extends GenericServiceImpl<Step4, Integer, Step4Dao> implements Step4Service {

	@Override
	protected Step4Dao getDao() {
		return new Step4DaoImpl(em, se);
	}

}
