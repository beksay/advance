package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.VisaQuestionDao;
import org.infosystema.advance.dao.impl.VisaQuestionDaoImpl;
import org.infosystema.advance.domain.study_abroad.VisaQuestion;
import org.infosystema.advance.service.VisaQuestionService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(VisaQuestionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaQuestionServiceImpl extends GenericServiceImpl<VisaQuestion, Integer, VisaQuestionDao> implements VisaQuestionService {

	@Override
	protected VisaQuestionDao getDao() {
		return new VisaQuestionDaoImpl(em, se);
	}

}
