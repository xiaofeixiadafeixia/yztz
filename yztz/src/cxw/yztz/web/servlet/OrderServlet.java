package cxw.yztz.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cxw.yztz.entity.Address;
import cxw.yztz.entity.Order;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.service.IOrderService;
import cxw.yztz.service.IProductService;
import cxw.yztz.service.serviceImpl.OrderServiceImpl;
import cxw.yztz.service.serviceImpl.ProductServiceImpl;
import cxw.yztz.utils.JudgeAuthorityEmail;
import cxw.yztz.utils.SendEmailUtils;
import cxw.yztz.web.base.BaseServlet;

public class OrderServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7756364612654894201L;
	
	private IProductService iProductService = new ProductServiceImpl();
	private IOrderService iOrderService = new OrderServiceImpl();
	
	@SuppressWarnings("unchecked")
	public String saveOrder(HttpServletRequest req,HttpServletResponse resp) {
		Object obj = (User)req.getSession().getAttribute("user");
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		if(obj!=null) {
			String goods_id = req.getParameter("isCheck");
			String address_id = req.getParameter("address_id");
			if(goods_id!=null && address_id != null) {
				User user = (User)obj;
				JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = jsonParser.parse(goods_id).getAsJsonArray();
				Integer[] goods_ids = new Integer[jsonArray.size()];
				//把商品id转换为整形封装数组
				for(int i=0;i< jsonArray.size();i++) {
					goods_ids[i] = Integer.valueOf(jsonArray.get(i).getAsString());
				}
				
				try {
					//查询这批里状态为1（在售）的商品
					List<?> list = iProductService.getProductsById(goods_ids,1);
					//吧要下单的商品id从收藏记录删除并且保存在订单表
					Address addr = new Address();
					addr.setAddress_id(Integer.parseInt(address_id));
					if(list.size()>0) {
						if(iOrderService.saveOrder(user, (List<Product>)list, addr)) {
							json.addProperty("state", 1);
							//发送通知邮件
							Map<String,List<String>> map = new HashMap<String,List<String>>();
							Set<String> emails = new HashSet<String>();
							//去掉重复的邮箱和 用户设置了“下单不提醒”的邮箱
							for(int i=0;i<list.size();i++) {
								Product p = (Product)list.get(i);
								//如果商品的主人允许接收邮件
								if(JudgeAuthorityEmail.getAuthority(p.getUser().getRemind_type(), JudgeAuthorityEmail.ORDER_MESSAGE)) {
									emails.add(p.getUser().getEmail());
								}
							}
							//把邮箱加入map集合
							for(String email : emails) {
								map.put(email, new ArrayList<String>());
							}
							//根据商品对应的邮箱去加入发送的相应的商品信息
							for(int i=0;i<list.size();i++) {
								Product p = (Product)list.get(i);
								String emailContent = "商品id：" +p.getGoods_id()+"商品名称："+p.getName() ;
								if(map.get(p.getUser().getEmail())!=null) {
									map.get(p.getUser().getEmail()).add(emailContent);
								}
							}
						
							SendEmailUtils.getEmailUtils().sendEmails(user, map);
						}
					}else {
						json.addProperty("errorMessage", "过滤后没有能下单的商品");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String deleteOrder(HttpServletRequest req,HttpServletResponse resp){
		String oid = req.getParameter("oid");
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		try {
			Order order = new Order();
			order.setOrders_id(Integer.valueOf(oid));
			if(iOrderService.deleteOrder(order))
				json.addProperty("state", 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
