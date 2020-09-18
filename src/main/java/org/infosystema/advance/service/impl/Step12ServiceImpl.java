package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.Step12Dao;
import org.infosystema.advance.dao.impl.Step12DaoImpl;
import org.infosystema.advance.domain.study_abroad.Step12;
import org.infosystema.advance.service.Step12Service;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(Step12Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Step12ServiceImpl extends GenericServiceImpl<Step12, Integer, Step12Dao> implements Step12Service {

	@Override
	protected Step12Dao getDao() {
		return new Step12DaoImpl(em, se);
	}

}
