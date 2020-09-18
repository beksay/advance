package org.infosystema.advance.controller.study_abroad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Step12;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.Step12Service;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class Step12Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step12Service moduleService;
	private Step12 module;
	@EJB
	AttachmentService atService;
	
	private AttachmentBinaryDTO appointment;
	private AttachmentBinaryDTO sevisFee;
	private AttachmentBinaryDTO bankStatement;
	private AttachmentBinaryDTO sponsors;
	private AttachmentBinaryDTO portfolio;
	private AttachmentBinaryDTO certificate;
	private AttachmentBinaryDTO ticket;
	private AttachmentBinaryDTO realEstate;
	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	
	@PostConstruct
	public void init() {
		if (module==null) module= new Step12();
		if (appointment==null) appointment= new AttachmentBinaryDTO();
		if (sevisFee==null) sevisFee= new AttachmentBinaryDTO();
		if (bankStatement==null) bankStatement= new AttachmentBinaryDTO();
		if (sponsors==null) sponsors= new AttachmentBinaryDTO();
		if (portfolio==null) portfolio= new AttachmentBinaryDTO();
		if (certificate==null) certificate= new AttachmentBinaryDTO();
		if (ticket==null) ticket= new AttachmentBinaryDTO();
		if (realEstate==null) realEstate= new AttachmentBinaryDTO();
	}
	
	public String edit(Module module) {
		this.module = (Step12)module;
		try {
			if (this.module.getAppointment() != null) {
				appointment = Util.createAttachmentDTO(this.module.getAppointment());
			}else {
				appointment = null;
			}
			if (this.module.getSevisFee() != null) {
				sevisFee = Util.createAttachmentDTO(this.module.getSevisFee());
			}else {
				sevisFee = null;
			}
			if (this.module.getBankStatement() != null) {
				bankStatement = Util.createAttachmentDTO(this.module.getBankStatement());
			}else {
				bankStatement = null;
			}
			if (this.module.getSponsors() != null) {
				sponsors = Util.createAttachmentDTO(this.module.getSponsors());
			}else {
				sponsors = null;
			}
			if (this.module.getPortfolio() != null) {
				portfolio = Util.createAttachmentDTO(this.module.getPortfolio());
			}else {
				portfolio = null;
			}
			if (this.module.getCertificate() != null) {
				certificate = Util.createAttachmentDTO(this.module.getCertificate());
			}else {
				certificate = null;
			}
			if (this.module.getTicket() != null) {
				ticket = Util.createAttachmentDTO(this.module.getTicket());
			}else {
				ticket = null;
			}
			if (this.module.getRealEstate() != null) {
				realEstate = Util.createAttachmentDTO(this.module.getRealEstate());
			}else {
				realEstate = null;
			}
		} catch (Exception e) {
			appointment=null;
			sevisFee=null;
			bankStatement=null;
			sponsors=null;
			portfolio=null;
			certificate=null;
			ticket=null;
			realEstate=null;
		}
		return "visa_document.xhtml";
	}
	
	public String save() {
		
		if(appointment != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(appointment);
			appointment.setAttachment(attachment);
			try {
				attachment = appointment.getAttachment().getId() == null ? atService.saveFromDTO(appointment) : appointment.getAttachment();
				module.setAppointment(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(sevisFee != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(sevisFee);
			sevisFee.setAttachment(attachment);
			try {
				attachment = sevisFee.getAttachment().getId() == null ? atService.saveFromDTO(sevisFee) : sevisFee.getAttachment();
				module.setSevisFee(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(bankStatement != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(bankStatement);
			bankStatement.setAttachment(attachment);
			try {
				attachment = bankStatement.getAttachment().getId() == null ? atService.saveFromDTO(bankStatement) : bankStatement.getAttachment();
				module.setBankStatement(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(sponsors != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(sponsors);
			sponsors.setAttachment(attachment);
			try {
				attachment = sponsors.getAttachment().getId() == null ? atService.saveFromDTO(sponsors) : sponsors.getAttachment();
				module.setSponsors(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(portfolio != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(portfolio);
			portfolio.setAttachment(attachment);
			try {
				attachment = portfolio.getAttachment().getId() == null ? atService.saveFromDTO(portfolio) : portfolio.getAttachment();
				module.setPortfolio(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(certificate != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(certificate);
			certificate.setAttachment(attachment);
			try {
				attachment = certificate.getAttachment().getId() == null ? atService.saveFromDTO(certificate) : certificate.getAttachment();
				module.setCertificate(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(ticket != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(ticket);
			ticket.setAttachment(attachment);
			try {
				attachment = ticket.getAttachment().getId() == null ? atService.saveFromDTO(ticket) : ticket.getAttachment();
				module.setTicket(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(realEstate != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(realEstate);
			realEstate.setAttachment(attachment);
			try {
				attachment = realEstate.getAttachment().getId() == null ? atService.saveFromDTO(realEstate) : realEstate.getAttachment();
				module.setRealEstate(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}

		if(module.getId()==null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}
		
		module = moduleService.merge(module);
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
			if (this.module.getAppointment() != null) {
				appointment = Util.createAttachmentDTO(this.module.getAppointment());
			}else {
				appointment = null;
			}
			if (this.module.getSevisFee() != null) {
				sevisFee = Util.createAttachmentDTO(this.module.getSevisFee());
			}else {
				sevisFee = null;
			}
			if (this.module.getBankStatement() != null) {
				bankStatement = Util.createAttachmentDTO(this.module.getBankStatement());
			}else {
				bankStatement = null;
			}
			if (this.module.getSponsors() != null) {
				sponsors = Util.createAttachmentDTO(this.module.getSponsors());
			}else {
				sponsors = null;
			}
			if (this.module.getPortfolio() != null) {
				portfolio = Util.createAttachmentDTO(this.module.getPortfolio());
			}else {
				portfolio = null;
			}
			if (this.module.getCertificate() != null) {
				certificate = Util.createAttachmentDTO(this.module.getCertificate());
			}else {
				certificate = null;
			}
			if (this.module.getTicket() != null) {
				ticket = Util.createAttachmentDTO(this.module.getTicket());
			}else {
				ticket = null;
			}
			if (this.module.getRealEstate() != null) {
				realEstate = Util.createAttachmentDTO(this.module.getRealEstate());
			}else {
				realEstate = null;
			}
		} catch (Exception e) {
			appointment=null;
			sevisFee=null;
			bankStatement=null;
			sponsors=null;
			portfolio=null;
			certificate=null;
			ticket=null;
			realEstate=null;
		}
		
		return "visa_document.xhtml";
	}
	
    public String complete() {
    	if (module.getAppointment()!=null && module.getSevisFee()!=null && module.getBankStatement()!=null && module.getSponsors()!=null ) {
    		if(module.getId()!=null) {	
    			module.setStatus(ModuleStatus.COMPLETED);
    		}
    		module = moduleService.merge(module);
    		
    		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
            FacesContext.getCurrentInstance().addMessage(null, msg); 
          
    	}else {
    		FacesMessage msg = new FacesMessage(Messages.getMessage("uploadRequiredDocuments").replaceAll("\\{0\\}", ""));  
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return "visa_document.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "visa_document.xhtml";
	}

    public void removeAppointment() {		
		if(appointment.getAttachment() != null && appointment.getAttachment().getId() != null) removedFiles.add(appointment.getAttachment());
		appointment = null;
		module.setAppointment(null);
	}
    
    public void removeSevisFee() {		
		if(sevisFee.getAttachment() != null && sevisFee.getAttachment().getId() != null) removedFiles.add(sevisFee.getAttachment());
		sevisFee = null;
		module.setSevisFee(null);
	}
    
    public void removeBankStatement() {		
		if(bankStatement.getAttachment() != null && bankStatement.getAttachment().getId() != null) removedFiles.add(bankStatement.getAttachment());
		bankStatement = null;
		module.setBankStatement(null);
	}
    
    public void removeSponsors() {		
		if(sponsors.getAttachment() != null && sponsors.getAttachment().getId() != null) removedFiles.add(sponsors.getAttachment());
		sponsors = null;
		module.setSponsors(null);
	}
    
    public void removePortfolio() {		
		if(portfolio.getAttachment() != null && portfolio.getAttachment().getId() != null) removedFiles.add(portfolio.getAttachment());
		portfolio = null;
		module.setPortfolio(null);
	}
    
    public void removeCertificate() {		
		if(certificate.getAttachment() != null && certificate.getAttachment().getId() != null) removedFiles.add(certificate.getAttachment());
		certificate = null;
		module.setCertificate(null);
	}
    
    public void removeTicket() {		
		if(ticket.getAttachment() != null && ticket.getAttachment().getId() != null) removedFiles.add(ticket.getAttachment());
		ticket = null;
		module.setTicket(null);
	}
    
    public void removeRealEstate() {		
		if(realEstate.getAttachment() != null && realEstate.getAttachment().getId() != null) removedFiles.add(realEstate.getAttachment());
		realEstate = null;
		module.setRealEstate(null);
	}
    
    public void assertRemovedFiles() {
		if(removedFiles.isEmpty()) return;
		
		for (Attachment attachment : removedFiles) {
			atService.remove(attachment);
		}
		
		removedFiles.clear();
	}
	
    public void handleFileUploadAppointment(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	appointment = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    public void handleFileUploadSevisFee(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	sevisFee = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadBankStatement(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	bankStatement = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadSponsors(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	sponsors = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadPortfolio(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	portfolio = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadCertificate(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	certificate = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadTicket(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	ticket = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadRealEstate(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	realEstate = createFileBinary(event.getFile());
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
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
	

	public Step12 getModule() {
		return module;
	}
	
	public void setModule(Step12 module) {
		this.module = module;
	}

	public AttachmentBinaryDTO getAppointment() {
		return appointment;
	}

	public void setAppointment(AttachmentBinaryDTO appointment) {
		this.appointment = appointment;
	}

	public AttachmentBinaryDTO getSevisFee() {
		return sevisFee;
	}

	public void setSevisFee(AttachmentBinaryDTO sevisFee) {
		this.sevisFee = sevisFee;
	}

	public AttachmentBinaryDTO getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(AttachmentBinaryDTO bankStatement) {
		this.bankStatement = bankStatement;
	}

	public AttachmentBinaryDTO getSponsors() {
		return sponsors;
	}

	public void setSponsors(AttachmentBinaryDTO sponsors) {
		this.sponsors = sponsors;
	}

	public AttachmentBinaryDTO getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(AttachmentBinaryDTO portfolio) {
		this.portfolio = portfolio;
	}

	public AttachmentBinaryDTO getCertificate() {
		return certificate;
	}

	public void setCertificate(AttachmentBinaryDTO certificate) {
		this.certificate = certificate;
	}

	public AttachmentBinaryDTO getTicket() {
		return ticket;
	}

	public void setTicket(AttachmentBinaryDTO ticket) {
		this.ticket = ticket;
	}

	public AttachmentBinaryDTO getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(AttachmentBinaryDTO realEstate) {
		this.realEstate = realEstate;
	}
}
