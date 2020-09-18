package org.infosystema.advance.conversation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.MoneySimulation;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationMoney extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private MoneySimulation moneySimulation;

	public MoneySimulation getMoneySimulation() {
		return moneySimulation;
	}

	public void setMoneySimulation(MoneySimulation moneySimulation) {
		this.moneySimulation = moneySimulation;
	}

	

	
}
