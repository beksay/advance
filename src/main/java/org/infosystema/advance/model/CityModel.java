package org.infosystema.advance.model;

import java.util.List;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.domain.City;
import org.infosystema.advance.service.CityService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class CityModel extends BaseModel<CityService, City, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public CityModel(List<FilterExample> filters, CityService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
