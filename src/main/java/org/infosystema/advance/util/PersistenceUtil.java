package org.infosystema.advance.util;

import java.io.Serializable;

import javax.persistence.Persistence;

import org.infosystema.advance.domain.PersistentEntity;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class PersistenceUtil {
	
	public static<T extends PersistentEntity<ID>, ID extends Serializable> boolean isLoaded(T entity, String property) {
		javax.persistence.PersistenceUtil util = Persistence.getPersistenceUtil();
		return !util.isLoaded(entity) || util.isLoaded(entity, property);
	}

}
