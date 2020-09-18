package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ProgramsDao;
import org.infosystema.advance.dao.impl.ProgramsDaoImpl;
import org.infosystema.advance.domain.Programs;
import org.infosystema.advance.service.ProgramsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ProgramsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProgramsServiceImpl extends GenericServiceImpl<Programs, Integer, ProgramsDao> implements ProgramsService {

	@Override
	protected ProgramsDao getDao() {
		return new ProgramsDaoImpl(em, se);
	}

}
