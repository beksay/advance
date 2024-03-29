package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.CountryDao;
import org.infosystema.advance.dao.impl.CountryDaoImpl;
import org.infosystema.advance.domain.Country;
import org.infosystema.advance.service.CountryService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(CountryService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CountryServiceImpl extends GenericServiceImpl<Country, Integer, CountryDao> implements CountryService {

	@Override
	protected CountryDao getDao() {
		return new CountryDaoImpl(em, se);
	}

}
