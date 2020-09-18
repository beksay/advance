package org.infosystema.advance.service;

import javax.ejb.Local;

import org.infosystema.advance.domain.User;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface UserService extends GenericService<User, Integer> {

}
