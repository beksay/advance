package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.DictionaryDao;
import org.infosystema.advance.dao.impl.DictionaryDaoImpl;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.service.DictionaryService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(DictionaryService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary, Integer, DictionaryDao> implements DictionaryService {

	@Override
	protected DictionaryDao getDao() {
		return new DictionaryDaoImpl(em, se);
	}

}
