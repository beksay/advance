package org.infosystema.advance.util.web;

import javax.servlet.http.HttpServletRequest;

import org.infosystema.advance.singleton.Configuration;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class HttpUtil {
	
	public static String getContextUrl(HttpServletRequest req) {
		return Configuration.getInstance().getProperty("address");		
	}

}
