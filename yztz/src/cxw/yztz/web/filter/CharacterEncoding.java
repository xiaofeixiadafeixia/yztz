package cxw.yztz.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncoding implements Filter{

	public CharacterEncoding() {
		// TODO Auto-generated constructor stub
	}
	private String encoding = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
		this.encoding = filterConfig.getInitParameter("encoding");
		if(this.encoding == null)
			this.encoding = "utf-8";
		System.out.println("编码是-----：" + this.encoding);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chian)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest )req;
		HttpServletResponse response=(HttpServletResponse )resp;
		request.setCharacterEncoding(this.encoding);
		
		response.setCharacterEncoding(this.encoding);
		
		chian.doFilter(req,resp);
	}
}
