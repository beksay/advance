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
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step2;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.PersonStatus;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.service.PersonService;
import org.infosystema.advance.service.Step2Service;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class Step2Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step2Service moduleService;
	private Step2 module;
	@EJB
	AttachmentService atService;
	@Inject
    private PersonService personService;
	@Inject
    private DictionaryService dictService;
	
	private AttachmentBinaryDTO passport;
	private AttachmentBinaryDTO picture;
	private AttachmentBinaryDTO bankStatement;
	private AttachmentBinaryDTO parents;
	private AttachmentBinaryDTO transcript;
	private AttachmentBinaryDTO schoolDiploma;
	private AttachmentBinaryDTO universityDiploma;
	private AttachmentBinaryDTO motivation;
	private AttachmentBinaryDTO recomendation;
	private AttachmentBinaryDTO toefl;
	private AttachmentBinaryDTO health;
	private AttachmentBinaryDTO employment;
	private AttachmentBinaryDTO gre;
	private AttachmentBinaryDTO autobiography;
	private AttachmentBinaryDTO sat;
	private AttachmentBinaryDTO financial;
	
	private List<Dictionary> masterDegree;
	
	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	
	@PostConstruct
	public void init() {
		if (module==null) module= new Step2();
		if (passport==null) passport= new AttachmentBinaryDTO();
		if (picture==null) picture= new AttachmentBinaryDTO();
		if (bankStatement==null) bankStatement= new AttachmentBinaryDTO();
		if (parents==null) parents= new AttachmentBinaryDTO();
		if (transcript==null) transcript= new AttachmentBinaryDTO();
		if (schoolDiploma==null) schoolDiploma= new AttachmentBinaryDTO();
		if (universityDiploma==null) universityDiploma= new AttachmentBinaryDTO();
		if (motivation==null) motivation= new AttachmentBinaryDTO();
		if (recomendation==null) recomendation= new AttachmentBinaryDTO();
		if (toefl==null) toefl= new AttachmentBinaryDTO();
		if (health==null) health= new AttachmentBinaryDTO();
		if (employment==null) employment= new AttachmentBinaryDTO();
		if (gre==null) gre= new AttachmentBinaryDTO();
		if (autobiography==null) autobiography= new AttachmentBinaryDTO();
		if (sat==null) sat= new AttachmentBinaryDTO();
		if (financial==null) financial= new AttachmentBinaryDTO();
	}
	
	public String edit(Module module) {
		this.module = (Step2)module;
		try {
			if (this.module.getPassport() != null) {
				passport = Util.createAttachmentDTO(this.module.getPassport());
			}else {
				passport = null;
			}
			if (this.module.getPicture() != null) {
				picture = Util.createAttachmentDTO(this.module.getPicture());
			}else {
				picture = null;
			}
			if (this.module.getBankStatement() != null) {
				bankStatement = Util.createAttachmentDTO(this.module.getBankStatement());
			}else {
				bankStatement = null;
			}
			if (this.module.getParents() != null) {
				parents = Util.createAttachmentDTO(this.module.getParents());
			}else {
				parents = null;
			}
			if (this.module.getTranscript() != null) {
				transcript = Util.createAttachmentDTO(this.module.getTranscript());
			}else {
				transcript = null;
			}
			if (this.module.getSchoolDiploma() != null) {
				schoolDiploma = Util.createAttachmentDTO(this.module.getSchoolDiploma());
			}else {
				schoolDiploma = null;
			}
			if (this.module.getUniversityDiploma() != null) {
				universityDiploma = Util.createAttachmentDTO(this.module.getUniversityDiploma());
			}else {
				universityDiploma = null;
			}
			if (this.module.getMotivation() != null) {
				motivation = Util.createAttachmentDTO(this.module.getMotivation());
			}else {
				motivation = null;
			}
			if (this.module.getRecomendation() != null) {
				recomendation = Util.createAttachmentDTO(this.module.getRecomendation());
			}else {
				recomendation = null;
			}
			if (this.module.getToefl() != null) {
				toefl = Util.createAttachmentDTO(this.module.getToefl());
			}else {
				toefl = null;
			}
			if (this.module.getHealth() != null) {
				health = Util.createAttachmentDTO(this.module.getHealth());
			}else {
				health = null;
			}
			if (this.module.getEmployment() != null) {
				employment = Util.createAttachmentDTO(this.module.getEmployment());
			}else {
				employment = null;
			}
			if (this.module.getGre() != null) {
				gre = Util.createAttachmentDTO(this.module.getGre());
			}else {
				gre = null;
			}
			if (this.module.getAutobiography() != null) {
				autobiography = Util.createAttachmentDTO(this.module.getAutobiography());
			}else {
				autobiography = null;
			}
			if (this.module.getSat() != null) {
				sat = Util.createAttachmentDTO(this.module.getSat());
			}else {
				sat = null;
			}
			if (this.module.getFinancial() != null) {
				financial = Util.createAttachmentDTO(this.module.getFinancial());
			}else {
				financial = null;
			}
		} catch (Exception e) {
			passport = null;
			picture = null;
			bankStatement = null;
			parents = null;
			transcript = null;
			schoolDiploma = null;
			universityDiploma = null;
			motivation = null;
			recomendation = null;
			toefl = null;
			health = null;
			employment = null;
			gre = null;
			autobiography = null;
			sat = null;
			financial = null;
		}
		return "document_list.xhtml";
	}
	
	public String save() {
		
		if(passport != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(passport);
			passport.setAttachment(attachment);
			try {
				attachment = passport.getAttachment().getId() == null ? atService.saveFromDTO(passport) : passport.getAttachment();
				module.setPassport(attachment);
				module.setPassportDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(picture != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(picture);
			picture.setAttachment(attachment);
			try {
				attachment = picture.getAttachment().getId() == null ? atService.saveFromDTO(picture) : picture.getAttachment();
				module.setPicture(attachment);
				module.setPictureDate(new Date());
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
				module.setBankStatementDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(parents != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(parents);
			parents.setAttachment(attachment);
			try {
				attachment = parents.getAttachment().getId() == null ? atService.saveFromDTO(parents) : parents.getAttachment();
				module.setParents(attachment);
				module.setParentsDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(transcript != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(transcript);
			transcript.setAttachment(attachment);
			try {
				attachment = transcript.getAttachment().getId() == null ? atService.saveFromDTO(transcript) : transcript.getAttachment();
				module.setTranscript(attachment);
				module.setTranscriptDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(schoolDiploma != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(schoolDiploma);
			schoolDiploma.setAttachment(attachment);
			try {
				attachment = schoolDiploma.getAttachment().getId() == null ? atService.saveFromDTO(schoolDiploma) : schoolDiploma.getAttachment();
				module.setSchoolDiploma(attachment);
				module.setSchoolDiplomaDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(universityDiploma != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(universityDiploma);
			universityDiploma.setAttachment(attachment);
			try {
				attachment = universityDiploma.getAttachment().getId() == null ? atService.saveFromDTO(universityDiploma) : universityDiploma.getAttachment();
				module.setUniversityDiploma(attachment);
				module.setUniversityDiplomaDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(motivation != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(motivation);
			motivation.setAttachment(attachment);
			try {
				attachment = motivation.getAttachment().getId() == null ? atService.saveFromDTO(motivation) : motivation.getAttachment();
				module.setMotivation(attachment);
				module.setMotivationDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(recomendation != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(recomendation);
			recomendation.setAttachment(attachment);
			try {
				attachment = recomendation.getAttachment().getId() == null ? atService.saveFromDTO(recomendation) : recomendation.getAttachment();
				module.setRecomendation(attachment);
				module.setRecomendationDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(toefl != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(toefl);
			toefl.setAttachment(attachment);
			try {
				attachment = toefl.getAttachment().getId() == null ? atService.saveFromDTO(toefl) : toefl.getAttachment();
				module.setToefl(attachment);
				module.setToeflDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(health != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(health);
			health.setAttachment(attachment);
			try {
				attachment = health.getAttachment().getId() == null ? atService.saveFromDTO(health) : health.getAttachment();
				module.setHealth(attachment);
				module.setHealthDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(employment != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(employment);
			employment.setAttachment(attachment);
			try {
				attachment = employment.getAttachment().getId() == null ? atService.saveFromDTO(employment) : employment.getAttachment();
				module.setEmployment(attachment);
				module.setEmploymentDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(gre != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(gre);
			gre.setAttachment(attachment);
			try {
				attachment = gre.getAttachment().getId() == null ? atService.saveFromDTO(gre) : gre.getAttachment();
				module.setGre(attachment);
				module.setGreDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(autobiography != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(autobiography);
			autobiography.setAttachment(attachment);
			try {
				attachment = autobiography.getAttachment().getId() == null ? atService.saveFromDTO(autobiography) : autobiography.getAttachment();
				module.setAutobiography(attachment);
				module.setAutobiographyDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(sat != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(sat);
			sat.setAttachment(attachment);
			try {
				attachment = sat.getAttachment().getId() == null ? atService.saveFromDTO(sat) : sat.getAttachment();
				module.setSat(attachment);
				module.setSatDate(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}
		
		if(financial != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(financial);
			financial.setAttachment(attachment);
			try {
				attachment = financial.getAttachment().getId() == null ? atService.saveFromDTO(financial) : financial.getAttachment();
				module.setFinancial(attachment);
				module.setFinancialDate(new Date());
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
			if (this.module.getPassport() != null) {
				passport = Util.createAttachmentDTO(this.module.getPassport());
			}else {
				passport = null;
			}
			if (this.module.getPicture() != null) {
				picture = Util.createAttachmentDTO(this.module.getPicture());
			}else {
				picture = null;
			}
			if (this.module.getBankStatement() != null) {
				bankStatement = Util.createAttachmentDTO(this.module.getBankStatement());
			}else {
				bankStatement = null;
			}
			if (this.module.getParents() != null) {
				parents = Util.createAttachmentDTO(this.module.getParents());
			}else {
				parents = null;
			}
			if (this.module.getTranscript() != null) {
				transcript = Util.createAttachmentDTO(this.module.getTranscript());
			}else {
				transcript = null;
			}
			if (this.module.getSchoolDiploma() != null) {
				schoolDiploma = Util.createAttachmentDTO(this.module.getSchoolDiploma());
			}else {
				schoolDiploma = null;
			}
			if (this.module.getUniversityDiploma() != null) {
				universityDiploma = Util.createAttachmentDTO(this.module.getUniversityDiploma());
			}else {
				universityDiploma = null;
			}
			if (this.module.getMotivation() != null) {
				motivation = Util.createAttachmentDTO(this.module.getMotivation());
			}else {
				motivation = null;
			}
			if (this.module.getRecomendation() != null) {
				recomendation = Util.createAttachmentDTO(this.module.getRecomendation());
			}else {
				recomendation = null;
			}
			if (this.module.getToefl() != null) {
				toefl = Util.createAttachmentDTO(this.module.getToefl());
			}else {
				toefl = null;
			}
			if (this.module.getHealth() != null) {
				health = Util.createAttachmentDTO(this.module.getHealth());
			}else {
				health = null;
			}
			if (this.module.getEmployment() != null) {
				employment = Util.createAttachmentDTO(this.module.getEmployment());
			}else {
				employment = null;
			}
			if (this.module.getGre() != null) {
				gre = Util.createAttachmentDTO(this.module.getGre());
			}else {
				gre = null;
			}
			if (this.module.getAutobiography() != null) {
				autobiography = Util.createAttachmentDTO(this.module.getAutobiography());
			}else {
				autobiography = null;
			}
			if (this.module.getSat() != null) {
				sat = Util.createAttachmentDTO(this.module.getSat());
			}else {
				sat = null;
			}
			if (this.module.getFinancial() != null) {
				financial = Util.createAttachmentDTO(this.module.getFinancial());
			}else {
				financial = null;
			}
		} catch (Exception e) {
			passport = null;
			picture = null;
			bankStatement = null;
			parents = null;
			transcript = null;
			schoolDiploma = null;
			universityDiploma = null;
			motivation = null;
			recomendation = null;
			toefl = null;
			health = null;
			employment = null;
			gre = null;
			autobiography = null;
			sat = null;
			financial = null;
		}
		
		return "document_list.xhtml";
	}
	
    public String complete() {
    	if (module.getPassport()!=null && module.getPicture()!=null && module.getBankStatement()!=null && module.getParents()!=null 
    			&& module.getTranscript()!=null && module.getSchoolDiploma()!=null) {
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

		return "document_list.xhtml";
	}
    
   public String completeMaster() {
		
	   if (module.getPassport()!=null && module.getPicture()!=null && module.getBankStatement()!=null && module.getParents()!=null 
   			&& module.getTranscript()!=null && module.getSchoolDiploma()!=null && module.getUniversityDiploma()!=null 
   			&& module.getMotivation()!=null && module.getRecomendation()!=null && module.getToefl()!=null) {
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
      
		return "document_list.xhtml";
	}
   
   public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "document_list.xhtml";
	}
    
    public Boolean isMasterStudent(Person person) {
    	
    	List<FilterExample> list=new ArrayList<>();
        list.add(new FilterExample("id",137,InequalityConstants.EQUAL));
        masterDegree = dictService.findByExample(0, 10, list);
    	
    	List<FilterExample> examples=new ArrayList<>();
    	examples.add(new FilterExample("id",person.getId(),InequalityConstants.EQUAL));
        examples.add(new FilterExample("status",PersonStatus.NEW,InequalityConstants.EQUAL));
        examples.add(new FilterExample("programs",masterDegree,InequalityConstants.MEMBER_OF));
        List<Person> personList=personService.findByExample(0, 100, examples);
        
        if (personList.size()>0) {
			return true;
		}else {
			return false;
		}
    }
    
    public void removePassport() {		
		if(passport.getAttachment() != null && passport.getAttachment().getId() != null) removedFiles.add(passport.getAttachment());
		passport = null;
		module.setPassport(null);
		module.setPassportDate(null);
	}
    
    public void removePicture() {		
		if(picture.getAttachment() != null && picture.getAttachment().getId() != null) removedFiles.add(picture.getAttachment());
		picture = null;
		module.setPicture(null);
		module.setPictureDate(null);
	}
    
    public void removeBankStatement() {		
		if(bankStatement.getAttachment() != null && bankStatement.getAttachment().getId() != null) removedFiles.add(bankStatement.getAttachment());
		bankStatement = null;
		module.setBankStatement(null);
		module.setBankStatementDate(null);
	}
    
    public void removeParents() {		
		if(parents.getAttachment() != null && parents.getAttachment().getId() != null) removedFiles.add(parents.getAttachment());
		parents = null;
		module.setParents(null);
		module.setParentsDate(null);
	}
    
    public void removeTranscript() {		
		if(transcript.getAttachment() != null && transcript.getAttachment().getId() != null) removedFiles.add(transcript.getAttachment());
		transcript = null;
		module.setTranscript(null);
		module.setTranscriptDate(null);
	}
    
    public void removeSchoolDiploma() {		
		if(schoolDiploma.getAttachment() != null && schoolDiploma.getAttachment().getId() != null) removedFiles.add(schoolDiploma.getAttachment());
		schoolDiploma = null;
		module.setSchoolDiploma(null);
		module.setSchoolDiplomaDate(null);
	}
    
    public void removeUniversityDiploma() {		
		if(universityDiploma.getAttachment() != null && universityDiploma.getAttachment().getId() != null) removedFiles.add(universityDiploma.getAttachment());
		universityDiploma = null;
		module.setUniversityDiploma(null);
		module.setUniversityDiplomaDate(null);
	}
    
    public void removeMotivation() {		
		if(motivation.getAttachment() != null && motivation.getAttachment().getId() != null) removedFiles.add(motivation.getAttachment());
		motivation = null;
		module.setMotivation(null);
		module.setMotivationDate(null);
	}
    
    public void removeRecomendation() {		
		if(recomendation.getAttachment() != null && recomendation.getAttachment().getId() != null) removedFiles.add(recomendation.getAttachment());
		recomendation = null;
		module.setRecomendation(null);
		module.setRecomendationDate(null);
	}
    
    public void removeToefl() {		
		if(toefl.getAttachment() != null && toefl.getAttachment().getId() != null) removedFiles.add(toefl.getAttachment());
		toefl = null;
		module.setToefl(null);
		module.setToeflDate(null);
	}
    
    public void removeHealth() {		
		if(health.getAttachment() != null && health.getAttachment().getId() != null) removedFiles.add(health.getAttachment());
		health = null;
		module.setHealth(null);
		module.setHealthDate(null);
	}
    
    public void removeEmployment() {		
		if(employment.getAttachment() != null && employment.getAttachment().getId() != null) removedFiles.add(employment.getAttachment());
		employment = null;
		module.setEmployment(null);
		module.setEmploymentDate(null);
	}
    
    public void removeGre() {		
		if(gre.getAttachment() != null && gre.getAttachment().getId() != null) removedFiles.add(gre.getAttachment());
		gre = null;
		module.setGre(null);
		module.setGreDate(null);
	}
    
    public void removeAutobiography() {		
		if(autobiography.getAttachment() != null && autobiography.getAttachment().getId() != null) removedFiles.add(autobiography.getAttachment());
		autobiography = null;
		module.setAutobiography(null);
		module.setAutobiographyDate(null);
	}
    
    public void removeSat() {		
		if(sat.getAttachment() != null && sat.getAttachment().getId() != null) removedFiles.add(sat.getAttachment());
		sat = null;
		module.setSat(null);
		module.setSatDate(null);
	}
    
    public void removeFinancial() {		
		if(financial.getAttachment() != null && financial.getAttachment().getId() != null) removedFiles.add(financial.getAttachment());
		financial = null;
		module.setFinancial(null);
		module.setFinancialDate(null);
	}
    
    public void assertRemovedFiles() {
		if(removedFiles.isEmpty()) return;
		
		for (Attachment attachment : removedFiles) {
			atService.remove(attachment);
		}
		
		removedFiles.clear();
	}
	
    public void handleFileUploadPassport(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	passport = createFileBinary(event.getFile());
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    public void handleFileUploadPicture(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	picture = createFileBinary(event.getFile());
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadBankStatement(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	bankStatement = createFileBinary(event.getFile());
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadParents(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	parents = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadTranscript(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	transcript = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadSchoolDiploma(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	schoolDiploma = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadUniversityDiploma(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	universityDiploma = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadMotivation(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	motivation = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadRecomendation(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	recomendation = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadToefl(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	toefl = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadHealth(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	health = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadEmployment(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	employment = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadGre(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	gre = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadAutobiography(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	autobiography = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadSat(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	sat = createFileBinary(event.getFile());	
        FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void handleFileUploadFinancial(FileUploadEvent event) throws IOException { 
    	String fileName = Translit.translit(event.getFile().getFileName());
    	financial = createFileBinary(event.getFile());	
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
	

	public Step2 getModule() {
		return module;
	}
	
	public void setModule(Step2 module) {
		this.module = module;
	}
	
	public AttachmentBinaryDTO getPassport() {
		return passport;
	}
	
	public void setPassport(AttachmentBinaryDTO passport) {
		this.passport = passport;
	}

	public AttachmentBinaryDTO getPicture() {
		return picture;
	}

	public void setPicture(AttachmentBinaryDTO picture) {
		this.picture = picture;
	}

	public AttachmentBinaryDTO getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(AttachmentBinaryDTO bankStatement) {
		this.bankStatement = bankStatement;
	}

	public AttachmentBinaryDTO getParents() {
		return parents;
	}

	public void setParents(AttachmentBinaryDTO parents) {
		this.parents = parents;
	}

	public AttachmentBinaryDTO getTranscript() {
		return transcript;
	}

	public void setTranscript(AttachmentBinaryDTO transcript) {
		this.transcript = transcript;
	}

	public AttachmentBinaryDTO getSchoolDiploma() {
		return schoolDiploma;
	}

	public void setSchoolDiploma(AttachmentBinaryDTO schoolDiploma) {
		this.schoolDiploma = schoolDiploma;
	}

	public AttachmentBinaryDTO getUniversityDiploma() {
		return universityDiploma;
	}

	public void setUniversityDiploma(AttachmentBinaryDTO universityDiploma) {
		this.universityDiploma = universityDiploma;
	}

	public AttachmentBinaryDTO getMotivation() {
		return motivation;
	}

	public void setMotivation(AttachmentBinaryDTO motivation) {
		this.motivation = motivation;
	}

	public AttachmentBinaryDTO getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(AttachmentBinaryDTO recomendation) {
		this.recomendation = recomendation;
	}

	public AttachmentBinaryDTO getToefl() {
		return toefl;
	}

	public void setToefl(AttachmentBinaryDTO toefl) {
		this.toefl = toefl;
	}

	public AttachmentBinaryDTO getHealth() {
		return health;
	}

	public void setHealth(AttachmentBinaryDTO health) {
		this.health = health;
	}

	public AttachmentBinaryDTO getEmployment() {
		return employment;
	}

	public void setEmployment(AttachmentBinaryDTO employment) {
		this.employment = employment;
	}

	public AttachmentBinaryDTO getGre() {
		return gre;
	}

	public void setGre(AttachmentBinaryDTO gre) {
		this.gre = gre;
	}

	public AttachmentBinaryDTO getAutobiography() {
		return autobiography;
	}

	public void setAutobiography(AttachmentBinaryDTO autobiography) {
		this.autobiography = autobiography;
	}

	public AttachmentBinaryDTO getSat() {
		return sat;
	}

	public void setSat(AttachmentBinaryDTO sat) {
		this.sat = sat;
	}

	public AttachmentBinaryDTO getFinancial() {
		return financial;
	}

	public void setFinancial(AttachmentBinaryDTO financial) {
		this.financial = financial;
	}

	public List<Dictionary> getMasterDegree() {
		return masterDegree;
	}

	public void setMasterDegree(List<Dictionary> masterDegree) {
		this.masterDegree = masterDegree;
	}
}
