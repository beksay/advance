package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.ApplicationSubmissionDao;
import org.infosystema.advance.dao.impl.ApplicationSubmissionDaoImpl;
import org.infosystema.advance.domain.study_abroad.ApplicationSubmission;
import org.infosystema.advance.service.ApplicationSubmissionService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(ApplicationSubmissionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationSubmissionServiceImpl extends GenericServiceImpl<ApplicationSubmission, Integer, ApplicationSubmissionDao> implements ApplicationSubmissionService {

	@Override
	protected ApplicationSubmissionDao getDao() {
		return new ApplicationSubmissionDaoImpl(em, se);
	}

}
