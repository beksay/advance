package org.infosystema.advance.service;

import javax.ejb.Local;

import org.infosystema.advance.domain.Country;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface CountryService extends GenericService<Country, Integer> {

}
