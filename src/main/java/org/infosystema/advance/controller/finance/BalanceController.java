package org.infosystema.advance.controller.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.infosystema.advance.enums.CurrencyType;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.service.MoneySimulationService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class BalanceController extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private MoneySimulationService service;
	
	private AccountType accountType;
	private CurrencyType currencyType;
	private BigDecimal totalIncome;
	private BigDecimal totalOutcome;
	private Date dateFrom;
	private Date dateTo;
	
	@PostConstruct
	private void init() {
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		if(accountType != null) filters.add(new FilterExample("accountType",accountType, InequalityConstants.EQUAL));
		if(currencyType != null) filters.add(new FilterExample("currencyType",currencyType, InequalityConstants.EQUAL));
		if(dateFrom!=null)
			filters.add(new FilterExample("dateCreated",dateFrom , InequalityConstants.GREATER_OR_EQUAL));
		if(dateTo!=null)
			filters.add(new FilterExample("dateCreated",dateTo , InequalityConstants.LESSER_OR_EQUAL));
		filters.add(new FilterExample("simulationType",SimulationType.INCOME, InequalityConstants.EQUAL));	
		totalIncome=service.sumByExample("amount", filters);
		filters = new ArrayList<>();
		if(accountType != null) filters.add(new FilterExample("accountType",accountType, InequalityConstants.EQUAL));
		if(currencyType != null) filters.add(new FilterExample("currencyType",currencyType, InequalityConstants.EQUAL));
		if(dateFrom!=null)
			filters.add(new FilterExample("dateCreated",dateFrom , InequalityConstants.GREATER_OR_EQUAL));
		if(dateTo!=null)
			filters.add(new FilterExample("dateCreated",dateTo , InequalityConstants.LESSER_OR_EQUAL));
		filters.add(new FilterExample("simulationType",SimulationType.OUTCOUME, InequalityConstants.EQUAL));	
		totalOutcome=service.sumByExample("amount", filters);
	}
	
	public String clearData() {
		currencyType = null;
		accountType = null;
		dateFrom=null;
		dateTo=null;
		init();	
		return null;
	}
	
	public List<AccountType> getAccountTypeList() {
		return Arrays.asList(AccountType.values());
	}
	
	public List<CurrencyType> getCurrencyTypeList() {
		return Arrays.asList(CurrencyType.values());
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalOutcome() {
		return totalOutcome;
	}

	public void setTotalOutcome(BigDecimal totalOutcome) {
		this.totalOutcome = totalOutcome;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
    
}
