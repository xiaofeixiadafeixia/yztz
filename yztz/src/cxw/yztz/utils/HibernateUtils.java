package cxw.yztz.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration configuration ;
	
	private static SessionFactory buildSessionFactory ;
	
	static {
		configuration = new Configuration().configure();
		buildSessionFactory = configuration.buildSessionFactory();
	}
	
	public static void openSession() {
		if(ThreadLocalUtils.threadLocalOfSession.get()==null)
			ThreadLocalUtils.threadLocalOfSession.set(buildSessionFactory.openSession());
	}
	public static Session getSession() {
		
		return ThreadLocalUtils.threadLocalOfSession.get();
	}
	public static void closeSession() {
		if(ThreadLocalUtils.threadLocalOfSession.get()!=null) {
			ThreadLocalUtils.threadLocalOfSession.get().close();
			ThreadLocalUtils.threadLocalOfSession.remove();
		}
	}
	public static Transaction getTransactionAndOpenSession() {
		if(ThreadLocalUtils.threadLocalOfSession.get()==null)
			ThreadLocalUtils.threadLocalOfSession.set(buildSessionFactory.openSession());
		return ThreadLocalUtils.threadLocalOfSession.get().beginTransaction();
	}
	public static void hibernateShutdown() {
		buildSessionFactory.close();
	}
}
