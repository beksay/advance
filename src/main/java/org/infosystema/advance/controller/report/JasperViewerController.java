package org.infosystema.advance.controller.report;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.enums.ScopeConstants;
import org.infosystema.advance.util.web.FacesScopeQualifier;
import org.infosystema.advance.util.web.ScopeQualifier;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * 
 *
 */

@ManagedBean
@RequestScoped
@Logged
public class JasperViewerController extends BaseReportController {

	public static final String REPORT_NAME = "org.infosystema.advance.jasperprint";
	
	public void view(JasperPrint print) {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(REPORT_NAME, print, ScopeConstants.SESSION_SCOPE);
		
		Map<String,Object> options = new HashMap<String, Object>();
    	options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 1024); 
        options.put("contentHeight", 600);

        RequestContext.getCurrentInstance().openDialog("/view/jasper_viewer.xhtml", options, null);
    	System.out.println(RequestContext.getCurrentInstance() + " 88888888888888888888888888888");
	}
	
	@Override
	public StreamedContent getStream(JasperPrint jasperPrint) {
		return super.getStream(jasperPrint);
	}
	
	public JasperPrint getCurrentJasperPrint() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		return qualifier.getValue(REPORT_NAME, ScopeConstants.SESSION_SCOPE);
	}
	
	public String getContextPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		return request.getContextPath();
	}
	
}
