package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step11Dao;
import org.infosystema.advance.dao.impl.Step11DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step11;
import org.infosystema.advance.service.Step11Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step11Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step11ServiceImpl extends GenericServiceImpl<Step11, Integer, Step11Dao> implements Step11Service {

	@Override
	protected Step11Dao getDao() {
		return new Step11DaoImpl(em, se);
	}

}
