package org.infosystema.advance.controller.study_abroad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.apache.commons.io.IOUtils;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.I20Service;
import org.infosystema.advance.service.Step7Service;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.study_abroad.ApplicationFee;
import org.infosystema.advance.domain.study_abroad.I20;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step7;
import org.infosystema.advance.enums.ModuleStatus;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class Step7Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private I20Service service;
	@EJB
	private Step7Service moduleService;
	@EJB
	AttachmentService atService;
	private Step7 module;
	private List<I20> list;
	private I20 i20;
	@Inject
	private LoginUtil loginUtil;
	
	private AttachmentBinaryDTO image;
	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	
	@PostConstruct
	public void init() {
		if (i20==null) i20= new I20();
		if (image==null) image= new AttachmentBinaryDTO();
	}
	
	public String edit(Module module) {
		this.module = (Step7)module;
		
		list = service.findByProperty("module", module);
		
		return "i_20.xhtml";
	}
	
	public List<I20> getList() {
		return list;
	}
	
	public String save() {
		if (i20.getId()==null) {
			if(image != null) {
	    		Attachment attachment = new Attachment();
				attachment = createAttachment(image);
				image.setAttachment(attachment);
				try {
					attachment = image.getAttachment().getId() == null ? atService.saveFromDTO(image) : image.getAttachment();
					i20.setI20Doc(attachment);
					attachment = new Attachment();
				} catch (IOException e) {e.printStackTrace();}			
			}
			i20.setUser(loginUtil.getCurrentUser());
			i20.setDateCreated(new Date());
			i20.setModule(module);
			i20 = service.persist(i20);
			
			list.add(i20);
					
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		} else {
			i20 = service.merge(i20);
			list = service.findByProperty("module", i20.getModule());
		}
		
		i20 = new I20();
		image = new AttachmentBinaryDTO();
		
		return "i_20.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "i_20.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "i_20.xhtml";
	}
    
    public String delete(I20 i20) {
		System.out.println(i20);
		service.remove(i20);
		list = service.findByProperty("module", i20.getModule());
		return "i_20.xhtml";
	}
    
    public String editData(I20 i20) {
		this.i20 = service.findById(i20.getId(), false);
		try {
			if (i20.getI20Doc() != null)
				image = Util.createAttachmentDTO(i20.getI20Doc());
			else
				image = null;
		} catch (Exception e) {
			image = null;
		}
		return "i_20.xhtml";
	}
	
	public void removeImage() {		
		if(image.getAttachment() != null && image.getAttachment().getId() != null) removedFiles.add(image.getAttachment());
		image = null;
		i20.setI20Doc(null);
	}
    
    public void assertRemovedFiles() {
		if(removedFiles.isEmpty()) return;
		
		for (Attachment attachment : removedFiles) {
			atService.remove(attachment);
		}
		
		removedFiles.clear();
	}
	
	// Загрузчик для рисунка
    public void handleFileUploadImage(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	image = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("fileSuccessfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    private AttachmentBinaryDTO createFileBinary(UploadedFile file) throws IOException {
    	AttachmentBinaryDTO binary = new AttachmentBinaryDTO();
		binary.setName(Translit.translit(file.getFileName()));
		binary.setMimeType(file.getContentType());
		binary.setBody(IOUtils.toByteArray(file.getInputstream()));
		
		return binary;
	}
    
    private Attachment createAttachment(AttachmentBinaryDTO binary) {
		if(binary.getAttachment() != null && binary.getAttachment().getId() != null) return binary.getAttachment();
		Attachment attachment = new Attachment();
		attachment.setFileName(binary.getName());
		attachment.setLocked(false);
		attachment.setPublicInfo(true);
		attachment.setData(binary.getBody());
		return attachment;
	}

	public AttachmentBinaryDTO getImage() {
		return image;
	}

	public void setImage(AttachmentBinaryDTO image) {
		this.image = image;
	}

	public I20 getI20() {
		return i20;
	}

	public void setI20(I20 i20) {
		this.i20 = i20;
	}
	
	public Step7 getModule() {
		return module;
	}
	
	public void setModule(Step7 module) {
		this.module = module;
	}
	
}
