package org.infosystema.advance.controller.user;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.util.Krypto;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.web.HttpUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

 


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Named
@ApplicationScoped
@Logged
public class UtilController {
	
	public <T> List<T> getAsList(Set<T> set) {
		if(set == null) return Collections.emptyList();
		return new ArrayList<T>(set);
	}
	
	public String getDownloadURL(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		return HttpUtil.getContextUrl(request) + "download?key=" + key;
	}

	
	public String generateKeyAliveCurrentSession(Attachment attachment) {
		if(attachment == null) return null;
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			Integer id = attachment.getId();
			
			Krypto krypto = new Krypto();
		    krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 04});
		    String keyValue = krypto.doEncrypt((session.getId() + "@@@@@@@" + id.toString()).getBytes());
		    keyValue = URLEncoder.encode(keyValue, "UTF-8");
		    
		    return keyValue;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String generateKeyAliveCurrentSessionId(Long id) {
		if(id == null) return null;
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			
			Krypto krypto = new Krypto();
		    krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 04});
		    System.out.println(id);
		    String keyValue = krypto.doEncrypt((session.getId() + "@@@@@@@" + id.toString()).getBytes());
		    keyValue = URLEncoder.encode(keyValue, "UTF-8");
		    
		    return keyValue;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public StreamedContent downloadFile(AttachmentBinaryDTO attachment){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println("attachment.getAttachment().getData()====" + attachment.getAttachment().getData());
		StreamedContent file=new DefaultStreamedContent(new ByteArrayInputStream(attachment.getAttachment().getData()),
				externalContext.getMimeType(Translit.translit(attachment.getAttachment().getFileName())),
				Translit.translit(attachment.getAttachment().getFileName()));
		return file;
	}
	
	

	
}
