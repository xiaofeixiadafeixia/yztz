package cxw.yztz.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cxw.yztz.utils.HibernateUtils;

public class HibernateListener implements ServletContextListener{

	public HibernateListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("hibaernate启动");
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		HibernateUtils.closeSession();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("hibernate关闭");
		HibernateUtils.hibernateShutdown();
	}
}
