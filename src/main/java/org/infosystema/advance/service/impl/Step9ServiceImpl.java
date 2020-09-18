package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step9Dao;
import org.infosystema.advance.dao.impl.Step9DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step9;
import org.infosystema.advance.service.Step9Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step9Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step9ServiceImpl extends GenericServiceImpl<Step9, Integer, Step9Dao> implements Step9Service {

	@Override
	protected Step9Dao getDao() {
		return new Step9DaoImpl(em, se);
	}

}
