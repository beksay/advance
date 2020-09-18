package org.infosystema.advance.service.impl;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.advance.dao.AttachmentDao;
import org.infosystema.advance.dao.impl.AttachmentDaoImpl;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.dto.AttachmentDataSource;
import org.infosystema.advance.dto.IdentifyResponse;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.soa.RepositoryService;
import org.infosystema.advance.soa.RepositoryServiceFactory;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(AttachmentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AttachmentServiceImpl extends GenericServiceImpl<Attachment, Integer, AttachmentDao> implements AttachmentService {

	private RepositoryService service;
	
	@PostConstruct
	private void init(){
		try {
			service = RepositoryServiceFactory.getInstance().getService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected AttachmentDao getDao() {
		return new AttachmentDaoImpl(em,se);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Attachment saveFromDTO(AttachmentBinaryDTO binary) throws IOException {
		binary.setName(binary.getAttachment().getRepositoryName());
		DataHandler handler = new DataHandler(new AttachmentDataSource(binary));
		
		System.out.println(service);
		
		IdentifyResponse response = service.save(binary.getAttachment().getFileName(), handler);
		binary.getAttachment().setRepositoryName(response.getChecksum());
		
		return persist(binary.getAttachment());
	}

}
