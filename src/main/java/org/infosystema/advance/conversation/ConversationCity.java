package org.infosystema.advance.conversation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.City;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationCity extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	

	
}
