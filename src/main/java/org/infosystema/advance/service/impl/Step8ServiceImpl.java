package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step8Dao;
import org.infosystema.advance.dao.impl.Step8DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step8;
import org.infosystema.advance.service.Step8Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step8Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step8ServiceImpl extends GenericServiceImpl<Step8, Integer, Step8Dao> implements Step8Service {

	@Override
	protected Step8Dao getDao() {
		return new Step8DaoImpl(em, se);
	}

}
