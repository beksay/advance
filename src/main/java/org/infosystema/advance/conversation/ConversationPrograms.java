package org.infosystema.advance.conversation;

import java.util.Set;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Dictionary;
import org.infosystema.advance.domain.Programs;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationPrograms extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Programs program;
	private Set<Dictionary> programs;
	private Set<Dictionary> majors;
	private Set<Dictionary> contacts;
	
	public Programs getProgram() {
		return program;
	}
	
	public void setProgram(Programs program) {
		this.program = program;
	}
	
	public Set<Dictionary> getPrograms() {
		return programs;
	}
	
	public void setPrograms(Set<Dictionary> programs) {
		this.programs = programs;
	}

	public Set<Dictionary> getMajors() {
		return majors;
	}

	public void setMajors(Set<Dictionary> majors) {
		this.majors = majors;
	}

	public Set<Dictionary> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Dictionary> contacts) {
		this.contacts = contacts;
	}
	
}
