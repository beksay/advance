package org.infosystema.advance.controller.study_abroad;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.beans.SortEnum;
import org.infosystema.advance.controller.base.BasePersonController;
import org.infosystema.advance.conversation.ConversationPerson;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Parents;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.dto.AttachmentBinaryDTO;
import org.infosystema.advance.enums.ScopeConstants;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.AttachmentService;
import org.infosystema.advance.service.DictionaryService;
import org.infosystema.advance.service.ModuleService;
import org.infosystema.advance.service.MoneySimulationService;
import org.infosystema.advance.service.ParentsService;
import org.infosystema.advance.service.PersonService;
import org.infosystema.advance.util.Translit;
import org.infosystema.advance.util.Util;
import org.infosystema.advance.util.web.FacesMessages;
import org.infosystema.advance.util.web.FacesScopeQualifier;
import org.infosystema.advance.util.web.LoginUtil;
import org.infosystema.advance.util.web.Messages;
import org.infosystema.advance.util.web.ScopeQualifier;
import org.infosystema.advance.validator.EntityValidator;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


@Named
@RequestScoped
public class PersonController extends BasePersonController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PersonService personService;
	@EJB
	private ParentsService parentsService;
	@EJB
	private ModuleService moduleService;
	@EJB
	private MoneySimulationService paymentsService;
	@EJB
	private AttachmentService atService;
	@EJB
	private DictionaryService dictService;
	
	@Inject
	private EntityValidator validator;
	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationPerson conversation;	

	private List<Attachment> removedFiles = new ArrayList<Attachment>();
	private List<Module> moduleList;
	private List<MoneySimulation> paymentsList;
	private List<Dictionary> usaStudent;
		
	public String add() {
		//conversation.initialize();
		conversation.setPerson(new Person());
		conversation.setProfile(null);
		conversation.setCountries(new HashSet<Dictionary>());
		conversation.setPrograms(new HashSet<Dictionary>());
		conversation.setMajors(new HashSet<Dictionary>());
		conversation.setSemesters(new HashSet<Dictionary>());
		conversation.setLanguages(new HashSet<Dictionary>());
		conversation.setCountryBirth(null);
		conversation.setEnglish(null);
		conversation.setMentor(null);
		conversation.setCitizenship(null);
		return form();
	}
	
	public String save() {
		if(conversation.getPerson().getId()==null) {
			Person person = personService.initialize(loginUtil.getCurrentUser(),conversation.getPerson());
			ScopeQualifier qualifier = new FacesScopeQualifier();
			qualifier.setValue(PERSON_KEY, person.getId(), ScopeConstants.SESSION_SCOPE);
		}
		
		System.out.println(conversation.getPerson());
		if(conversation.getPerson() == null){
			FacesMessages.addMessage(Messages.getMessage("invalidData"), Messages.getMessage("invalidData"), null);
			return null;
		}
		
		conversation.getPerson().setDateCreated(new Date());
		conversation.getPerson().setCountries(conversation.getCountries());
		conversation.getPerson().setPrograms(conversation.getPrograms());
		conversation.getPerson().setMajors(conversation.getMajors());
		conversation.getPerson().setSemesters(conversation.getSemesters());
		conversation.getPerson().setLanguages(conversation.getLanguages());
		conversation.getPerson().setCountryBirth(conversation.getCountryBirth());
		conversation.getPerson().setEnglish(conversation.getEnglish());
		conversation.getPerson().setMentor(conversation.getMentor());
		conversation.getPerson().setCitizenship(conversation.getCitizenship());
    	if(conversation.getProfile() != null) {
    		System.out.println("PROFILE======" + conversation.getProfile());
    		Attachment attachment = new Attachment();
			attachment = createAttachment(conversation.getProfile());
			conversation.getProfile().setAttachment(attachment);
			try {
				attachment = conversation.getProfile().getAttachment().getId() == null ? atService.saveFromDTO(conversation.getProfile()) : conversation.getProfile().getAttachment();
				System.out.println("ATTACHMENT===" + attachment);
				conversation.getPerson().setProfile(attachment);
				attachment = new Attachment();
			} catch (IOException e) {e.printStackTrace();}			
		} 
    	
    	conversation.getPerson().setUser(loginUtil.getCurrentUser());
		validator.validate(conversation.getPerson());
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		
		conversation.setPerson(conversation.getPerson().getId() == null ? personService.persist(conversation.getPerson()) : personService.merge(conversation.getPerson()));
		
		conversation.setPerson(null);
		
	    return cancel();  
		
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		try {
			if (conversation.getPerson().getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(conversation.getPerson().getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		conversation.setPerson(conversation.getPerson());
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/person/details/main.xhtml?cid="+conversation.getId());
        
    }
	
	public void onRowSelectFailed(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		try {
			if (conversation.getPerson().getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(conversation.getPerson().getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		conversation.setPerson(conversation.getPerson());
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/failed/details/main.xhtml?cid="+conversation.getId());
        
    }
	
	public void onRowSelectAlumni(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		try {
			if (conversation.getPerson().getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(conversation.getPerson().getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		conversation.setPerson(conversation.getPerson());
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/alumni/details/main.xhtml?cid="+conversation.getId());
        
    }
	
	public void onRowSelectAllStudents(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		try {
			if (conversation.getPerson().getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(conversation.getPerson().getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		conversation.setPerson(conversation.getPerson());
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/advance/view/allStudents/details/main.xhtml?cid="+conversation.getId());
        
    }
	
	public void onRowToggle(ToggleEvent event) throws NamingException {
		conversation.setPerson((Person) event.getData());		
	}
	
	public List<Module> getModules() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer id = qualifier.getValue(PERSON_KEY, ScopeConstants.SESSION_SCOPE);
		List<Module> modules = qualifier.getValue(id + "_", ScopeConstants.REQUEST_SCOPE);
		
		if(modules == null) {
			List<FilterExample> list = new ArrayList<>();
			list.add(new FilterExample("person.id", id, InequalityConstants.EQUAL));
			modules = moduleService.findByExample(0, 200, SortEnum.ASCENDING, list, "index");
			
			qualifier.setValue(id + "_", modules, ScopeConstants.REQUEST_SCOPE);
		}
		
		System.out.println("modules = " + modules + " id = " + id);
		
		return modules;
	}
	
	public List<Module> getModulesList(Person person) {
		List<FilterExample> list = new ArrayList<>();
		list.add(new FilterExample("person", person, InequalityConstants.EQUAL));
		moduleList = moduleService.findByExample(0, 200, SortEnum.ASCENDING, list, "index");
		return moduleList;
	}
	
	public List<MoneySimulation> getPaymentsList(Person person) {
		List<FilterExample> list = new ArrayList<>();
		list.add(new FilterExample("person", person, InequalityConstants.EQUAL));
		list.add(new FilterExample("simulationType", SimulationType.INCOME, InequalityConstants.EQUAL));
		paymentsList = paymentsService.findByExample(0, 200, SortEnum.ASCENDING, list, "id");
		return paymentsList;
	}
	
	 public String edit(Person pp) {
		    System.out.println("person.getFirstname()===" + pp.getFirstname());
		    /*//conversation.initialize();
		    conversation.setPerson(new Person());
		    conversation.getPerson().setFirstname(person.getFirstname());
            conversation.getPerson().setLastname(person.getLastname());
            conversation.getPerson().setPatronymic(person.getPatronymic());
            conversation.getPerson().setPin(person.getPin());
            conversation.getPerson().setBirthDate(person.getBirthDate());
            //conversation.getPerson().setMentor(person.getMentor());
           // conversation.getPerson().setCountryBirth(person.getCountryBirth());
            conversation.getPerson().setCity(person.getCity());
            conversation.getPerson().setAddress(person.getAddress());
           // conversation.getPerson().setEnglish(person.getEnglish());
            conversation.getPerson().setToefl(person.getToefl());
            conversation.getPerson().setIelts(person.getIelts());
           // conversation.getPerson().setCitizenship(person.getCitizenship());
            conversation.getPerson().setAbroad(person.getAbroad());
            conversation.getPerson().setCu(person.getCu());
            conversation.getPerson().setEmail(person.getEmail());
            conversation.getPerson().setPhone(person.getPhone());
            conversation.getPerson().setFinance(person.getFinance());*/ 
		    
		    conversation.setPerson(service.getByIdWithFields(pp.getId(), new String[] {"programs","majors","countries","semesters","languages"}));
            
            conversation.setCitizenship(pp.getCitizenship());
            conversation.setEnglish(pp.getEnglish());
            
            System.out.println("pp.getEnglish() = " + pp.getEnglish());
            
            conversation.setMentor(pp.getMentor());
            conversation.setCountryBirth(pp.getCountryBirth());
            
			conversation.setCountries(new HashSet<Dictionary>());
			conversation.getCountries().addAll(pp.getCountries());
		
			conversation.setPrograms(new HashSet<Dictionary>());
			conversation.getPrograms().addAll(pp.getPrograms());

			conversation.setMajors(new HashSet<Dictionary>());
			conversation.getMajors().addAll(pp.getMajors());

			conversation.setSemesters(new HashSet<Dictionary>());
			conversation.getSemesters().addAll(pp.getSemesters());
			
			conversation.setLanguages(new HashSet<Dictionary>());
			conversation.getLanguages().addAll(pp.getLanguages());
		try {
			if (pp.getProfile() != null)
				conversation.setProfile(Util.createAttachmentDTO(pp.getProfile()));
			else
				conversation.setProfile(null);
		} catch (Exception e) {
			conversation.setProfile(null);
		}
		
		System.out.println("conversation.getId()=============" + conversation.getId());
		//conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries","semesters"}));
		return "/view/person/person_form.xhtml?faces-redirect=true&cid="+conversation.getId();
	}
	
	public String parentForm(Person person) {
		System.out.println("paaaaarent ===" + person);
		conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		if(person.getFather() !=null) {
			conversation.setFather(parentsService.findById(person.getFather().getId(), false));
		}else {
			conversation.setFather(new Parents());
		}
		if (conversation.getPerson().getFather() ==null) {
			conversation.setDontHaveFather(true);
		}
		if(person.getMother() !=null) {
			conversation.setMother(parentsService.findById(person.getMother().getId(), false));
		}else {
			conversation.setMother(new Parents());
		}
		if (conversation.getPerson().getMother() ==null) {
			conversation.setDontHaveMother(true);
		}
		return "/view/person/parents_info.xhtml";
	}
	
	public String parentAdminForm(Person person) {
		System.out.println("paaaaarent ===" + person);
		conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		if(person.getFather() !=null) {
			conversation.setFather(parentsService.findById(person.getFather().getId(), false));
		}else {
			conversation.setFather(new Parents());
		}
		if (conversation.getPerson().getFather() ==null) {
			conversation.setDontHaveFather(true);
		}
		if(person.getMother() !=null) {
			conversation.setMother(parentsService.findById(person.getMother().getId(), false));
		}else {
			conversation.setMother(new Parents());
		}
		if (conversation.getPerson().getMother() ==null) {
			conversation.setDontHaveMother(true);
		}
		return "/view/allStudents/parents_info.xhtml";
	}
	
	public String emergencyForm(Person person) {
		System.out.println("paaaaarent ===" + person);
		conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		return "/view/person/emergency_contact.xhtml";
	}
	
	public String emergencyAdminForm(Person person) {
		System.out.println("paaaaarent ===" + person);
		conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		return "/view/allStudents/emergency_contact.xhtml";
	}
	
	public String mainProfile(Module module) {
		conversation.setPerson(service.getByIdWithFields(module.getPerson().getId(), new String[] {"programs","majors","countries","semesters","languages"}));
		conversation.setCountries(new HashSet<Dictionary>()); conversation.getCountries().addAll(conversation.getPerson().getCountries());
		conversation.setPrograms(new HashSet<Dictionary>()); conversation.getPrograms().addAll(conversation.getPerson().getPrograms());
		conversation.setMajors(new HashSet<Dictionary>()); conversation.getMajors().addAll(conversation.getPerson().getMajors());
		conversation.setSemesters(new HashSet<Dictionary>()); conversation.getSemesters().addAll(conversation.getPerson().getSemesters());
		return "/view/person/details/main.xhtml?faces-redirect=true";
	}
	
	public String delete(Person c) {
		System.out.println(c);
		try {
			
			personService.removeFully(c);
			
			FacesMessage msg = new FacesMessage(Messages.getMessage("studentDeleted").replaceAll("\\{0\\}", ""));  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(Messages.getMessage("youCantDeleteStudent").replaceAll("\\{0\\}", ""));  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}
		
		return cancel();
	}
	
	public String cancel() {
		conversation.setPerson(null);
		return list();
	}
	
	private String list() {
		return "/view/person/person_journal.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/person/person_form.xhtml";
	}
	
	public void removeImage() {		
		if(conversation.getProfile().getAttachment() != null && conversation.getProfile().getAttachment().getId() != null) removedFiles.add(conversation.getProfile().getAttachment());
		conversation.setProfile(null);
	}
    
 // Загрузчик для рисунка PROFILE
    public void handleFileUploadImageProfile(FileUploadEvent event) throws IOException { 
    	
    	String fileName = Translit.translit(event.getFile().getFileName());
    	conversation.setProfile(createFileBinaryProfile(event.getFile()));
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("fileSuccessfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
    private AttachmentBinaryDTO createFileBinaryProfile(UploadedFile file) throws IOException {
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
    
    public List<Dictionary> getCountryList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getMentorList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 6, InequalityConstants.EQUAL));
		examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getCitizenshipList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 3, InequalityConstants.EQUAL));
		examples.add(new FilterExample("name", '%' + query.toLowerCase() + '%', InequalityConstants.LIKE));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getEnglishList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 4, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getCountryList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 1, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getProgramList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 5, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getMajorList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 7, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getSemesterList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 8, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getLanguagesList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("dictionaryType.id", 9, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 10, examples);
	}
    
    public List<Dictionary> getDict(Integer dictType){
		List<FilterExample> filter = new ArrayList<>();
		filter.add(new FilterExample("dictionaryType.id", dictType, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 1000, SortEnum.ASCENDING, filter, "name");
	}
    
    public Boolean isUSAStudent() {
    	List<FilterExample> examples=new ArrayList<>();
    	examples.add(new FilterExample("id",conversation.getCountries(),InequalityConstants.EQUAL));
        List<Dictionary> personList=dictService.findByExample(0, 100, examples);
        
        if (personList.size()>0) {
			return true;
		}else {
			return false;
		}
    }
    
    public String saveParentData() {
		
		if(conversation.getDontHaveFather()){
			conversation.setFather(null);
		}else {
			conversation.setFather(conversation.getFather().getId() == null ? parentsService.persist(conversation.getFather()) : parentsService.merge(conversation.getFather()));
			conversation.getPerson().setFather(conversation.getFather());
			personService.merge(conversation.getPerson());
		}
		
		if(conversation.getDontHaveMother()){
			conversation.setMother(null);
		}else {
			conversation.setMother(conversation.getMother().getId() == null ? parentsService.persist(conversation.getMother()) : parentsService.merge(conversation.getMother()));
			conversation.getPerson().setMother(conversation.getMother());
			personService.merge(conversation.getPerson());
		}
		
		conversation.setPerson(null);
		conversation.setFather(null);
		conversation.setMother(null);
		return cancel();  
	}
    
    public StreamedContent viewPersonProfile(Attachment attachment){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		StreamedContent file=new DefaultStreamedContent(new ByteArrayInputStream(attachment.getData()),
				externalContext.getMimeType(Translit.translit(attachment.getFileName())),
				Translit.translit(attachment.getFileName()));
		return file;
	}
	
	public ConversationPerson getConversation() {
		return conversation;
	}
	
	public void setConversation(ConversationPerson conversation) {
		this.conversation = conversation;
	}

	public List<Dictionary> getUsaStudent() {
		return usaStudent;
	}

	public void setUsaStudent(List<Dictionary> usaStudent) {
		this.usaStudent = usaStudent;
	}


}
