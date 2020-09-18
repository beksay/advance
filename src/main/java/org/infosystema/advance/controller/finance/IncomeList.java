package org.infosystema.advance.controller.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.beans.FilterExample;
import org.infosystema.advance.beans.InequalityConstants;
import org.infosystema.advance.controller.base.BaseController;
import org.infosystema.advance.domain.MoneySimulation;
import org.infosystema.advance.enums.AccountType;
import org.infosystema.advance.enums.CenterType;
import org.infosystema.advance.enums.SimulationType;
import org.infosystema.advance.model.MoneySimulationModel;
import org.infosystema.advance.service.MoneySimulationService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@ManagedBean
@ViewScoped
public class IncomeList extends BaseController implements Serializable {
	
	private static final long serialVersionUID = -6100072166946495229L;
	@EJB
	private MoneySimulationService service;
	private MoneySimulationModel model;
	
	private Integer first;
	private MoneySimulation moneySimulation;
	
	private BigDecimal amount;
	private AccountType accountType;
	private CenterType centerType;
	private Date dateFrom;
	private Date dateTo;
	private BigDecimal totalAmount;
	
	@PostConstruct
	private void init() {
		restoreState();
		filterData();
	}
	
	public void filterData() {
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("simulationType",SimulationType.INCOME, InequalityConstants.EQUAL));
		if(amount != null) filters.add(new FilterExample("amount",amount, InequalityConstants.EQUAL));
		if(accountType != null) filters.add(new FilterExample("accountType",accountType, InequalityConstants.EQUAL));
		if(centerType != null) filters.add(new FilterExample("centerType",centerType, InequalityConstants.EQUAL));
		if(dateFrom!=null)
			filters.add(new FilterExample("dateCreated",dateFrom , InequalityConstants.GREATER_OR_EQUAL));
		if(dateTo!=null)
			filters.add(new FilterExample("dateCreated",dateTo , InequalityConstants.LESSER_OR_EQUAL));
		model = new MoneySimulationModel(filters, service);
		
		totalAmount=service.sumByExample("amount", filters);
	}
	
	public String clearData() {
		amount = null;
		accountType = null;
		centerType = null;
		dateFrom=null;
		dateTo=null;
		init();	
		return null;
	}
	
	public Integer getFirst() {
		return first;
	}
	
	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public void saveState() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", model);
		session.setAttribute("first", first);
	}
	
	public void restoreState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		model = (MoneySimulationModel) session.getAttribute("model");
		first = (Integer) session.getAttribute("first");
	}
	
	public void removeState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", null);
		session.setAttribute("first", null);
		
		model = null;
		first = null;
	}
	
	public void onPageChange(PageEvent event) {  
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		setFirst(((DataTable) event.getSource()).getRows() * event.getPage());
		session.setAttribute("first", first);
	}

	
    public MoneySimulationModel getModel() {
		return model;
	}
    
    public void setModel(MoneySimulationModel model) {
		this.model = model;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public CenterType getCenterType() {
		return centerType;
	}

	public void setCenterType(CenterType centerType) {
		this.centerType = centerType;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public MoneySimulation getMoneySimulation() {
		return moneySimulation;
	}

	public void setMoneySimulation(MoneySimulation moneySimulation) {
		this.moneySimulation = moneySimulation;
	}

    
}
