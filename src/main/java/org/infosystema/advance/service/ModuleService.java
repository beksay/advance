package org.infosystema.advance.service;

import javax.ejb.Local;

import org.infosystema.advance.domain.study_abroad.Module;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface ModuleService extends GenericService<Module, Integer> {
	
	//void checkForFilled(Person person);
	//void checkForValidated(Person person);

}
