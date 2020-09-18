package org.infosystema.advance.model;

import java.util.List;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.domain.User;
import org.infosystema.advance.service.UserService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class UserModel extends BaseModel<UserService, User, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public UserModel(List<FilterExample> filters, UserService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
