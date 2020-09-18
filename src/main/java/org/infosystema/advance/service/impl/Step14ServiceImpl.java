package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step14Dao;
import org.infosystema.advance.dao.impl.Step14DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step14;
import org.infosystema.advance.service.Step14Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step14Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step14ServiceImpl extends GenericServiceImpl<Step14, Integer, Step14Dao> implements Step14Service {

	@Override
	protected Step14Dao getDao() {
		return new Step14DaoImpl(em, se);
	}

}
