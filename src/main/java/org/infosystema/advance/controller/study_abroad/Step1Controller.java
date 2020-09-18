package org.infosystema.advance.controller.study_abroad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.infosystema.advance.beans.SortEnum;
import org.infosystema.advance.conversation.ConversationPerson;
import org.infosystema.advance.conversation.Conversational;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step1;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.DiscountType;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.PersonService;
import org.infosystema.advance.service.Step1Service;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped
public class Step1Controller extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private Step1Service moduleService;
	@EJB
	private PersonService personService;
	@EJB
	private MoneySimulationService paymentsService;
	private Step1 module;
	private Person person;
	private MoneySimulation payments;
	@EJB
	AttachmentService atService;
	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationPerson conversationPerson;
	
	private AttachmentBinaryDTO image;
	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	
	@PostConstruct
	public void init() {
		if (module==null) module= new Step1();
		if (person==null) person= new Person();
		if (image==null) image= new AttachmentBinaryDTO();
		if (payments==null) payments= new MoneySimulation();
	}
	
	public String edit(Module module) {
		this.module = (Step1)module;
		this.person = personService.findById(module.getPerson().getId(), false);
		try {
			if (this.module.getContract() != null)
				image = Util.createAttachmentDTO(this.module.getContract());
			else
				image = null;
		} catch (Exception e) {
			image = null;
		}
		return "contract_signed.xhtml";
	}
	
	public String editProfile(Module module) {
		//conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries"}));
		
		return "main.xhtml";
	}
	
	public String save() {
		
		if(image != null) {
    		Attachment attachment = new Attachment();
			attachment = createAttachment(image);
			image.setAttachment(attachment);
			try {
				attachment = image.getAttachment().getId() == null ? atService.saveFromDTO(image) : image.getAttachment();
				module.setContract(attachment);
				module.setDateCreated(new Date());
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		}else {
			FacesMessage msg = new FacesMessage(Messages.getMessage("contractIsRequired").replaceAll("\\{0\\}", ""));  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
	        return null;
		}
		
		module.setUser(loginUtil.getCurrentUser());
		if(module.getId()==null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}
		
		person.setDeal(false);
		module.setPerson(person);
		personService.merge(person);
		
		module = moduleService.merge(module);
		
		
		
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullySaved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
			if (module.getContract() != null)
				image = Util.createAttachmentDTO(module.getContract());
			else
				image = null;
		} catch (Exception e) {
			image = null;
		}
		
		return "contract_signed.xhtml";
	}
	
    public String complete() {
		
		if(module.getId()!=null) {	
			module.setStatus(ModuleStatus.COMPLETED);
		}
		module = moduleService.merge(module);
		
		module.getPerson().setDeal(true);
		personService.merge(module.getPerson());
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("successfullyApproved").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
      
		return "contract_signed.xhtml";
	}
    
    public String sendToEdit() {
   		if(module.getId()!=null) {	
   			module.setStatus(ModuleStatus.FILLED);
   			module = moduleService.merge(module);
   			FacesMessage msg = new FacesMessage(Messages.getMessage("sendToEdit").replaceAll("\\{0\\}", ""));  
   	        FacesContext.getCurrentInstance().addMessage(null, msg); 
   		}
		return "contract_signed.xhtml";
	}
    
    public void removeImage() {		
		if(image.getAttachment() != null && image.getAttachment().getId() != null) removedFiles.add(image.getAttachment());
		image = null;
		module.setContract(null);
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
	
    public List<DiscountType> getDiscountTypeList() {
		return Arrays.asList(DiscountType.values());
	}
    
    public String savePayment() {
		
		payments.setPerson(conversationPerson.getPerson());
		payments.setUser(loginUtil.getCurrentUser());
		payments.setCenterType(CenterType.SAC);
		payments.setSimulationType(SimulationType.INCOME);
		if (payments.getId()==null) {
			payments.setDateCreated(new Date());
			payments = paymentsService.persist(payments);
		} else {
			payments.setDateModify(new Date());
          payments = paymentsService.merge(payments);
		}
		
		FacesMessage msg = new FacesMessage(Messages.getMessage("savedPayment").replaceAll("\\{0\\}", ""));  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        payments = new MoneySimulation();
      
		return "contract_signed.xhtml";
	}
    
     public String editPayment(MoneySimulation payments) {
		this.payments = paymentsService.findById(payments.getId(), false);
		return "contract_signed.xhtml";
	}
     
     public String delete(MoneySimulation payments) {
 		paymentsService.remove(payments);
 		//getPaymentsList();
 		return "contract_signed.xhtml";
 	}
    
    public List<MoneySimulation> getPaymentsList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("person",conversationPerson.getPerson() , InequalityConstants.EQUAL));
		examples.add(new FilterExample("simulationType",SimulationType.INCOME , InequalityConstants.EQUAL));
		return paymentsService.findByExample(0, 100, SortEnum.DESCENDING, examples, "id");
	}
   
    public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}
    
    public List<AccountType> getAccountTypeList() {
		return Arrays.asList(AccountType.values());
	}

	public Step1 getModule() {
		return module;
	}
	
	public void setModule(Step1 module) {
		this.module = module;
	}
	
	public AttachmentBinaryDTO getImage() {
		return image;
	}
	
	public void setImage(AttachmentBinaryDTO image) {
		this.image = image;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public MoneySimulation getPayments() {
		return payments;
	}

	public void setPayments(MoneySimulation payments) {
		this.payments = payments;
	}
}
