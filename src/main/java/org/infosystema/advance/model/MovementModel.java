package org.infosystema.advance.model;

import java.util.List;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.domain.Movement;
import org.infosystema.advance.service.MovementService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class MovementModel extends BaseModel<MovementService, Movement, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public MovementModel(List<FilterExample> filters, MovementService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
