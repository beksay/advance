package org.infosystema.advance.controller.base;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.advance.annotation.Logged;
import org.infosystema.advance.domain.Attachment;
import org.infosystema.advance.singleton.Configuration;
import org.infosystema.advance.util.Krypto;
import org.infosystema.advance.util.web.HttpUtil;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Named
@ApplicationScoped
@Logged
public class UtilPersonController {
	
	public String getSubReportPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext= (ServletContext) context.getExternalContext().getContext();
	    return servletContext.getRealPath("/WEB-INF/reports");
	}
	
	public boolean isEdit(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, dayOfMonth + 1, 15, 0, 0);
		date = calendar.getTime();
		if(date.getTime() < System.currentTimeMillis()) return false;
		
		return true;
	}
	
	public <T> List<T> getAsList(Set<T> set) {
		if(set == null) return Collections.emptyList();
		return new ArrayList<T>(set);
	}
	
	public String getAddress() {
		return Configuration.getInstance().getProperty("address");
	}
	
	public String getDownloadURL(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		return HttpUtil.getContextUrl(request) + "download?key=" + key;
	}
	
	public String getPublicDownloadURL(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		return HttpUtil.getContextUrl(request) + "download_public?key=" + key;
	}
	
	public String generateKeyAliveCurrentSession(Attachment attachment) {
		if(attachment == null) return null;
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			Integer id = attachment.getId();
			
			Krypto krypto = new Krypto();
		    krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 04});
		    String keyValue = krypto.doEncrypt((session.getId() + "@@@@@@@" + id.toString()).getBytes());
		    keyValue = URLEncoder.encode(keyValue, "UTF-8");
		    
		    return keyValue;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
