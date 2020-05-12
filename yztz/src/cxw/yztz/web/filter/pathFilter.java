package cxw.yztz.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pathFilter implements Filter {

	public pathFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse resp = (HttpServletResponse)arg1;
		HttpServletRequest req = (HttpServletRequest)arg0;
		
		String path = req.getRequestURL().toString();
		System.out.println(path);
		resp.sendRedirect(req.getContextPath()+"/UIServlet?method=mainUI");
	}

}
