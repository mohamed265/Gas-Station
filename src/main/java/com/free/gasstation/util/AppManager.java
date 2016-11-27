package com.free.gasstation.util;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.free.gasstation.util.constant.Constants;

public class AppManager implements ServletContextListener, ServletContextAttributeListener {
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		DBConnectionManager.cleanUp();
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		DBConnectionManager.init();
		e.getServletContext().setAttribute("Constants", new Constants());
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent e) {
		// TODO Auto-generated method stub

	}

}
