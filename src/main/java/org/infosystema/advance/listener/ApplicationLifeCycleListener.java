package org.infosystema.advance.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.infosystema.advance.singleton.Configuration;
import org.infosystema.advance.util.MailSender;

/***
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ApplicationLifeCycleListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		MailSender.destroy();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Configuration.getInstance();
	}

}
