package org.infosystema.advance.service;

import java.io.IOException;

import javax.ejb.Local;

import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.dto.AttachmentBinaryDTO;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface AttachmentService extends GenericService<Attachment, Integer> {

	Attachment saveFromDTO(AttachmentBinaryDTO binary) throws IOException;

}
