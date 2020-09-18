package org.infosystema.advance.model;

import java.util.List;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.domain.Programs;
import org.infosystema.advance.service.ProgramsService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ProgramsModel extends BaseModel<ProgramsService, Programs, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public ProgramsModel(List<FilterExample> filters, ProgramsService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
