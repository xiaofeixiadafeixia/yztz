package cxw.yztz.web.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cxw.yztz.entity.User;
import cxw.yztz.service.serviceImpl.UserServiceImpl;
import cxw.yztz.utils.CookieUtils;

public class AutoLoginFilter implements Filter{

	public AutoLoginFilter() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chian)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		if(request.getSession().getAttribute("user")==null) {
			Cookie cookies[] = request.getCookies();
			Cookie emailCookie = CookieUtils.getCookie("yztzEmail", cookies);
			Cookie passWordCookie = CookieUtils.getCookie("yztzPassword", cookies);
			if(emailCookie!=null && passWordCookie!=null) {
				User u = new User();
				u.setEmail(URLDecoder.decode(emailCookie.getValue(),"utf-8"));
				u.setPassword(URLDecoder.decode(passWordCookie.getValue(),"utf-8"));
				try{
					u = new UserServiceImpl().userLogin(u);
					if(u!=null) {
						request.getSession().setAttribute("user", u);
					}else {
						Cookie c1 = new Cookie("yztzEmail",null);
						Cookie c2 = new Cookie("yztzPassword", null);
						c1.setMaxAge(0);
						c2.setMaxAge(0);
						HttpServletResponse response = (HttpServletResponse)resp;
						response.addCookie(c1);
						response.addCookie(c2);
					}
				}catch(Exception e) {
					System.out.println("自动登录出错");
					e.printStackTrace();
				}
			}
		}
		chian.doFilter(req, resp);
	}

}
