package cxw.yztz.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

	public CookieUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static Cookie getCookie(String name,Cookie cookies[]) {
		if(name!=null && cookies!=null && !"".equals(name)) {
			for(Cookie c : cookies) {
				if(name.equals(c.getName())) {
					return c;
				}
			}
		}
		return null ;
	}
	
}
