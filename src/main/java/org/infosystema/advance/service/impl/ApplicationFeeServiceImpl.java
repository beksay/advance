package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ApplicationFeeDao;
import org.infosystema.advance.dao.impl.ApplicationFeeDaoImpl;
import org.infosystema.advance.domain.study_abroad.ApplicationFee;
import org.infosystema.advance.service.ApplicationFeeService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ApplicationFeeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationFeeServiceImpl extends GenericServiceImpl<ApplicationFee, Integer, ApplicationFeeDao> implements ApplicationFeeService {

	@Override
	protected ApplicationFeeDao getDao() {
		return new ApplicationFeeDaoImpl(em, se);
	}

}
