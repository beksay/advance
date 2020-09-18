package org.infosystema.advance.service;

import javax.ejb.Local;

import org.infosystema.advance.domain.User;
import org.infosystema.advance.domain.study_abroad.Person;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface PersonService extends GenericService<Person, Integer> {

	Person initialize(User user, Person person);
	String removeFully(Person person);

}
