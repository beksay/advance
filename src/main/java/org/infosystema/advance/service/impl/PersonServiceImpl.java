package org.infosystema.advance.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import org.infosystema.advance.dao.PersonDao;
import org.infosystema.advance.dao.impl.PersonDaoImpl;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.domain.study_abroad.Module;
import org.infosystema.advance.domain.study_abroad.Person;
import org.infosystema.advance.domain.study_abroad.Step1;
import org.infosystema.advance.domain.study_abroad.Step10;
import org.infosystema.advance.domain.study_abroad.Step11;
import org.infosystema.advance.domain.study_abroad.Step12;
import org.infosystema.advance.domain.study_abroad.Step13;
import org.infosystema.advance.domain.study_abroad.Step14;
import org.infosystema.advance.domain.study_abroad.Step15;
import org.infosystema.advance.domain.study_abroad.Step16;
import org.infosystema.advance.domain.study_abroad.Step2;
import org.infosystema.advance.domain.study_abroad.Step3;
import org.infosystema.advance.domain.study_abroad.Step4;
import org.infosystema.advance.domain.study_abroad.Step5;
import org.infosystema.advance.domain.study_abroad.Step6;
import org.infosystema.advance.domain.study_abroad.Step7;
import org.infosystema.advance.domain.study_abroad.Step8;
import org.infosystema.advance.domain.study_abroad.Step9;
import org.infosystema.advance.enums.ModuleStatus;
import org.infosystema.advance.enums.PersonStatus;
import org.infosystema.advance.service.PersonService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(PersonService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PersonServiceImpl extends GenericServiceImpl<Person, Integer, PersonDao> implements PersonService {

	@Override
	protected PersonDao getDao() {
		return new PersonDaoImpl(em, se);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Person initialize(User user,Person person) {
		person.setStatus(PersonStatus.NEW);

		person = getDao().persist(person);
		
		Module module = new Step1();
		
		createModule(person, module, 1);
		
		module = new Step2();
		createModule(person, module, 2);
		
		module = new Step3();
		createModule(person, module, 3);
		
		module = new Step4();
		createModule(person, module, 4);
		
		module = new Step5();
		createModule(person, module, 5);
		
		module = new Step6();
		createModule(person, module, 6);
		
		module = new Step7();
		createModule(person, module, 7);
		
		module = new Step8();
		createModule(person, module, 8);
		
		module = new Step9();
		createModule(person, module, 9);
		
		module = new Step10();
		createModule(person, module, 10);
		
		module = new Step11();
		createModule(person, module, 11);
		
		module = new Step12();
		createModule(person, module, 12);
		
		module = new Step13();
		createModule(person, module, 13);
		
		module = new Step14();
		createModule(person, module, 14);
		
		module = new Step15();
		createModule(person, module, 15);
		
		module = new Step16();
		createModule(person, module, 16);
	
		return person;
	}

	private void createModule(Person person, Module module, int index) {
		module.setPerson(person);
		module.setIndex(index);
		module.setStatus(ModuleStatus.NEW);
		
		em.persist(module);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String removeFully(Person person) {
		System.out.println("remove person " + person);
		Person entityManaged = getDao().findById(person.getId(), false);
		getDao().refresh(entityManaged);
		
		if(PersonStatus.NEW.equals(entityManaged.getStatus())) {
			int count = em.createNativeQuery("DELETE FROM application_fee USING step3 s WHERE module_id = s.id AND s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed application_fee = " + count);
			count = em.createNativeQuery("DELETE FROM application_submission USING step4 s WHERE module_id = s.id AND s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed application_submission = " + count);
			count = em.createNativeQuery("DELETE FROM i_20 USING step7 s WHERE module_id = s.id AND s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed i_20 = " + count);
			count = em.createNativeQuery("DELETE FROM shipping_fee USING step6 s WHERE module_id = s.id AND s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed shipping_fee = " + count);
			count = em.createNativeQuery("DELETE FROM visa_question USING step15 s WHERE module_id = s.id AND s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed visa_question = " + count);
			count = em.createNativeQuery("DELETE FROM payments p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed payments = " + count);
			count = em.createNativeQuery("DELETE FROM step1 s WHERE s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step1 = " + count);
			count = em.createNativeQuery("DELETE FROM step2 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step2 = " + count);
			count = em.createNativeQuery("DELETE FROM step3 s WHERE s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step3 = " + count);
			count = em.createNativeQuery("DELETE FROM step5 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step5 = " + count);
			count = em.createNativeQuery("DELETE FROM step7 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step7 = " + count);
			count = em.createNativeQuery("DELETE FROM step6 s WHERE s.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step6 = " + count);
			count = em.createNativeQuery("DELETE FROM step8 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step8 = " + count);
			count = em.createNativeQuery("DELETE FROM step9 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step9 = " + count);
			count = em.createNativeQuery("DELETE FROM step10 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step10 = " + count);
			count = em.createNativeQuery("DELETE FROM step11 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step11 = " + count);
			count = em.createNativeQuery("DELETE FROM step12 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step12 = " + count);
			count = em.createNativeQuery("DELETE FROM step13 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step13 = " + count);
			count = em.createNativeQuery("DELETE FROM step14 p WHERE p.person_id = :personId").setParameter("personId", person.getId()).executeUpdate();
			System.out.println("removed step14 = " + count);
			count = em.createQuery("DELETE FROM Module entity WHERE entity.person = :person").setParameter("person", person).executeUpdate();
			System.out.println("removed Module = " + count);
			getDao().remove(entityManaged);
			
			/*FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
			fullTextEntityManager.remove(entityManaged);*/
			
			return "Student deleted successfully";
		}
		else {
			return "You can not delete this student";
		}
		
	}

}
