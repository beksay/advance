package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step15Dao;
import org.infosystema.advance.dao.impl.Step15DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step15;
import org.infosystema.advance.service.Step15Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step15Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step15ServiceImpl extends GenericServiceImpl<Step15, Integer, Step15Dao> implements Step15Service {

	@Override
	protected Step15Dao getDao() {
		return new Step15DaoImpl(em, se);
	}

}
