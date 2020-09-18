package org.infosystema.advance.model;

import java.util.List;

import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.service.DictionaryService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class DictionaryModel extends BaseModel<DictionaryService, Dictionary, Integer> {

	private static final long serialVersionUID = -4871106869905562775L;

	public DictionaryModel(List<FilterExample> filters, DictionaryService service) {
		super(filters, service);
	}
	
	@Override
	protected Integer getKey(String key) {
		return Integer.parseInt(key);
	}
	
}
