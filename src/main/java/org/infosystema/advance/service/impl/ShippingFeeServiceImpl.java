package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ShippingFeeDao;
import org.infosystema.advance.dao.impl.ShippingFeeDaoImpl;
import org.infosystema.advance.domain.study_abroad.ShippingFee;
import org.infosystema.advance.service.ShippingFeeService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ShippingFeeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ShippingFeeServiceImpl extends GenericServiceImpl<ShippingFee, Integer, ShippingFeeDao> implements ShippingFeeService {

	@Override
	protected ShippingFeeDao getDao() {
		return new ShippingFeeDaoImpl(em, se);
	}

}
