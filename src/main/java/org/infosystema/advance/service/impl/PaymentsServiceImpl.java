package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.PaymentsDao;
import org.infosystema.advance.dao.impl.PaymentsDaoImpl;
import org.infosystema.advance.domain.study_abroad.Payments;
import org.infosystema.advance.service.PaymentsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(PaymentsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PaymentsServiceImpl extends GenericServiceImpl<Payments, Integer, PaymentsDao> implements PaymentsService {

	@Override
	protected PaymentsDao getDao() {
		return new PaymentsDaoImpl(em, se);
	}

}
