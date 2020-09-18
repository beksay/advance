package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step10Dao;
import org.infosystema.advance.dao.impl.Step10DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step10;
import org.infosystema.advance.service.Step10Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step10Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step10ServiceImpl extends GenericServiceImpl<Step10, Integer, Step10Dao> implements Step10Service {

	@Override
	protected Step10Dao getDao() {
		return new Step10DaoImpl(em, se);
	}

}
