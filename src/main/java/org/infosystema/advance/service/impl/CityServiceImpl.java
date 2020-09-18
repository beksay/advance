package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.CityDao;
import org.infosystema.advance.dao.impl.CityDaoImpl;
import org.infosystema.advance.domain.City;
import org.infosystema.advance.service.CityService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(CityService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CityServiceImpl extends GenericServiceImpl<City, Integer, CityDao> implements CityService {

	@Override
	protected CityDao getDao() {
		return new CityDaoImpl(em, se);
	}

}
