package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.DictionaryTypeDao;
import org.infosystema.advance.dao.impl.DictionaryTypeDaoImpl;
import org.infosystema.advance.domain.DictionaryType;
import org.infosystema.advance.service.DictionaryTypeService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(DictionaryTypeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DictionaryTypeServiceImpl extends GenericServiceImpl<DictionaryType, Integer, DictionaryTypeDao> implements DictionaryTypeService {

	@Override
	protected DictionaryTypeDao getDao() {
		return new DictionaryTypeDaoImpl(em, se);
	}

}
