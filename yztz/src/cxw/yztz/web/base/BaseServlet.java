package cxw.yztz.web.base;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.*;
public class BaseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207258073887659129L;

	public BaseServlet() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Class<?> clazz = this.getClass();
		String path = "";
		
		String md = req.getParameter("method");
		if (null == md || "".equals(md) || md.trim().equals("")) {
			md = "isEmpty";
		}
		
		
		try {
			Method method = clazz.getMethod(md, HttpServletRequest.class,HttpServletResponse.class);
			if(method!=null) {
				path = (String)method.invoke(this, req,resp);
				if(path!=null) {
					req.getRequestDispatcher(path).forward(req, resp);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("反射出错");
		}
	}
	
	public String isEmpty(HttpServletRequest req, HttpServletResponse resp) {
		return null;
	}
}
