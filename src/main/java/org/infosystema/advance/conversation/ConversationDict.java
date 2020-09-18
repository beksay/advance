package org.infosystema.advance.conversation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Dictionary;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationDict extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Dictionary dictionary;

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	

	
}
