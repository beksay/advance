package org.infosystema.advance.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.advance.dao.AttachmentDao;
import org.infosystema.advance.domain.Attachment;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class AttachmentDaoImpl extends GenericDaoImpl<Attachment, Integer> implements AttachmentDao {

	public AttachmentDaoImpl(EntityManager entityManager, Event<Attachment> event) {
		super(entityManager, event);
	}

}
