package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.I20Dao;
import org.infosystema.advance.dao.impl.I20DaoImpl;
import org.infosystema.advance.domain.study_abroad.I20;
import org.infosystema.advance.service.I20Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(I20Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class I20ServiceImpl extends GenericServiceImpl<I20, Integer, I20Dao> implements I20Service {

	@Override
	protected I20Dao getDao() {
		return new I20DaoImpl(em, se);
	}

}
