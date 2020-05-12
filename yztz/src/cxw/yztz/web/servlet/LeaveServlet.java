package cxw.yztz.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cxw.yztz.entity.Leave;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.service.ILeaveService;
import cxw.yztz.service.serviceImpl.LeaveServiceImpl;
import cxw.yztz.utils.PageModelRelateToJson;
import cxw.yztz.web.base.BaseServlet;

public class LeaveServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5802356292360471914L;
	
	private ILeaveService iLeaveService = new LeaveServiceImpl();
	
	public String saveLeave(HttpServletRequest req,HttpServletResponse resp) {
		String goods_id = req.getParameter("goods_id");
		String content = req.getParameter("content");
		
		Object obj = req.getSession().getAttribute("user");
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		try {
			if(obj!=null) {//用户不为空
				if(content == null || "".equals(content)) {
					json.addProperty("errorMessage", "内容不能为空！");
				}else {
					User user = (User)obj;
					iLeaveService.saveLeave(new Leave(null, user, Integer.parseInt(goods_id), content, new Date(), 1));
					json.addProperty("state", 1);
				}
				
			}else {
				json.addProperty("errorMessage", "NoUser");
				req.getSession().setAttribute("lastRequestUrl", "productInfoUI&goods_id="+goods_id);
			}
			resp.getWriter().print(json.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public String getLeaves(HttpServletRequest req,HttpServletResponse resp) {
		String goods_id = req.getParameter("goods_id");
		String pageNum = req.getParameter("pageNum");
		String currentNum = req.getParameter("currentNum");
		Product product = new Product();
		product.setGoods_id(Integer.parseInt(goods_id));
		try {
			PageModelRelateToJson<Product> pm = new PageModelRelateToJson<Product>
				(iLeaveService.getLeaveCount(product), Integer.parseInt(pageNum), Integer.parseInt(currentNum));
			pm.setT(product);//这里必须要传一个包含商品id 的商品对象过去，否则将会出错
			
			Gson gson = new Gson();
			resp.getWriter().print(gson.toJson(iLeaveService.getLeaves(pm)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null ;
	}
}
