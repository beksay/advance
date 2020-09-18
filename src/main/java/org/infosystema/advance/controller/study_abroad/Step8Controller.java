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

import org.apache.commons.io.IOUtils;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step8;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.Step8Service;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class Step8Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step8Service moduleService;
	private Step8 module;
	@EJB
	AttachmentService atService;
	@Inject
	private LoginUtil loginUtil;
	
	private AttachmentBinaryDTO dsFile;
	private AttachmentBinaryDTO dsFile2;
	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	
	@PostConstruct
	public void init() {
		if (module==null) module= new Step8();
		if (dsFile==null) dsFile= new AttachmentBinaryDTO();
		if (dsFile2==null) dsFile2= new AttachmentBinaryDTO();
	}
	
	public String edit(Module module) {
		this.module = (Step8)module;
		try {
			if (this.module.getDsFile() != null)
				dsFile = Util.createAttachmentDTO(this.module.getDsFile());
			else
				dsFile = null;
		} catch (Exception e) {
			dsFile = null;
		}
		try {
			if (this.module.getDsFile() != null)
				dsFile2 = Util.createAttachmentDTO(this.module.getDsFile2());
			else
				dsFile2 = null;
		} catch (Exception e) {
			dsFile2 = null;
		}
		return "ds_160.xhtml";
	}
	
	public String save() {
		
		if(dsFile != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(dsFile);
			dsFile.setAttachment(attachment);
			try {
				attachment = dsFile.getAttachment().getId() == null ? atService.saveFromDTO(dsFile) : dsFile.getAttachment();
				module.setDsFile(attachment);
				module.setDateCreated(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(dsFile2 != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(dsFile2);
			dsFile2.setAttachment(attachment);
			try {
				attachment = dsFile2.getAttachment().getId() == null ? atService.saveFromDTO(dsFile2) : dsFile2.getAttachment();
				module.setDsFile2(attachment);
				module.setDateCreated2(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		module.setUser(loginUtil.getCurrentUser());
		if(module.getId()==null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}
		
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
			if (module.getDsFile() != null)
				dsFile = Util.createAttachmentDTO(module.getDsFile());
			else
				dsFile = null;
		} catch (Exception e) {
			dsFile = null;
		}
        
        try {
			if (module.getDsFile() != null)
				dsFile2 = Util.createAttachmentDTO(module.getDsFile2());
			else
				dsFile2 = null;
		} catch (Exception e) {
			dsFile2 = null;
		}
		
		return "ds_160.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "ds_160.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "ds_160.xhtml";
	}
    
    public void removeDsFile() {		
		if(dsFile.getAttachment() != null && dsFile.getAttachment().getId() != null) removedFiles.add(dsFile.getAttachment());
		dsFile = null;
		module.setDsFile(null);
	}
    
    public void removeDsFile2() {		
		if(dsFile2.getAttachment() != null && dsFile2.getAttachment().getId() != null) removedFiles.add(dsFile2.getAttachment());
		dsFile2 = null;
		module.setDsFile(null);
	}
    
    public void assertRemovedFiles() {
		if(removedFiles.isEmpty()) return;
		
		for (Attachment attachment : removedFiles) {
			atService.remove(attachment);
		}
		
		removedFiles.clear();
	}
	
	// Загрузчик для рисунка
    public void handleFileUploadDsFile(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	dsFile = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("fileSuccessfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    // Загрузчик для рисунка
    public void handleFileUploadDsFile2(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	dsFile2 = createFileBinary(event.getFile());
    	
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
    
	

	public Step8 getModule() {
		return module;
	}
	
	public void setModule(Step8 module) {
		this.module = module;
	}
	
	public AttachmentBinaryDTO getDsFile() {
		return dsFile;
	}

	public void setDsFile(AttachmentBinaryDTO dsFile) {
		this.dsFile = dsFile;
	}

	public AttachmentBinaryDTO getDsFile2() {
		return dsFile2;
	}

	public void setDsFile2(AttachmentBinaryDTO dsFile2) {
		this.dsFile2 = dsFile2;
	}
}
