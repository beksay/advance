package org.infosystema.advance.controller.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.controller.base.BaseController;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.model.MovementModel;
import org.infosystema.advance.service.MovementService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class MovementList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private MovementService service;
	private MovementModel model;
	
	private BigDecimal amount;
	private AccountType accountFrom;
	private AccountType accountTo;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		if(amount != null) filters.add(new FilterExample("amount",amount, InequalityConstants.EQUAL));
		if(accountFrom != null) filters.add(new FilterExample("accountFrom",accountFrom, InequalityConstants.EQUAL));
		if(accountTo != null) filters.add(new FilterExample("accountTo",accountTo, InequalityConstants.EQUAL));
		model = new MovementModel(filters, service);
	}
	
	public String clearData() {
		amount = null;
		accountFrom = null;
		accountTo = null;
		init();	
		return null;
	}

	
    public MovementModel getModel() {
		return model;
	}
    
    public void setModel(MovementModel model) {
		this.model = model;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AccountType getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(AccountType accountFrom) {
		this.accountFrom = accountFrom;
	}

	public AccountType getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(AccountType accountTo) {
		this.accountTo = accountTo;
	}

    
}
