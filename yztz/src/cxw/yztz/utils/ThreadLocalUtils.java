package cxw.yztz.utils;

import org.hibernate.Session;

public class ThreadLocalUtils {
	public static final ThreadLocal<Session> threadLocalOfSession = new ThreadLocal<Session>();
	private ThreadLocalUtils() {
		// TODO Auto-generated constructor stub
	}

}
