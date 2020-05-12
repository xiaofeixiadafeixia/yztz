package cxw.yztz.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cxw.yztz.entity.Address;
import cxw.yztz.entity.College;
import cxw.yztz.entity.User;
import cxw.yztz.service.IAddressService;
import cxw.yztz.service.serviceImpl.AddressServiceImpl;
import cxw.yztz.web.base.BaseServlet;

public class AddressServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 34013466916736296L;
	
	private IAddressService addressService = new AddressServiceImpl();

	public AddressServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public String getColleges(HttpServletRequest req,HttpServletResponse resp) {
		try {
			String json = new Gson().toJson(addressService.getColleges());
			resp.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户和学院来查询地址
	 * @param req
	 * @param resp
	 * @return
	 */
	public String getAddressByUserWithCollege(HttpServletRequest req,HttpServletResponse resp) {
		Gson gson = new Gson();
		String college_id = req.getParameter("college_id");
		Object obj = req.getSession().getAttribute("user");
		if(obj!=null && college_id !=null) {
			User user = (User)obj;
			College college = new College();
			try {
				college.setCollege_id(Integer.parseInt(college_id));
				List<?> list = addressService.getAddressByUserWithCollege(user,college);
				resp.getWriter().print(gson.toJson(list));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 更新或者添加地址
	 * @param req
	 * @param resp
	 * @return
	 */
	public String addressSubmitOrUpdate(HttpServletRequest req,HttpServletResponse resp) {
		String address_id = req.getParameter("address_id");
		String address_name = req.getParameter("address_name");
		String address_phone = req.getParameter("address_phone");
		String address_addr = req.getParameter("address_addr");
		String address_college_id = req.getParameter("address_college_id");
		
		
		Address address = new Address();
		address.setPseudonym(address_name);
		address.setPhone(address_phone);
		address.setAddress(address_addr);
		address.setCollege_id(Integer.parseInt(address_college_id));
		address.setUser_id(((User)req.getSession().getAttribute("user")).getId());
		
		
		boolean flag = false;
		try {
			if(address_id == null ||"".equals(address_id)) {//没有id 就是添加地址信息
				flag = addressService.saveAddress(address);
			}else {
				address.setAddress_id(Integer.parseInt(address_id));
				flag = addressService.updateAddressByAddress_id(address);
			}
			
			JsonObject json = new JsonObject();
			if(flag)
				json.addProperty("state", 1);
			else
				json.addProperty("state", 0);
				
			resp.getWriter().print(json.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 根据地址id来   解除地址与用户的 关联(不是真正意义上的删除)
	 * @param req
	 * @param resp
	 * @return
	 */
	public String deleteAddressByAddress_id(HttpServletRequest req,HttpServletResponse resp) {
		String address_id = req.getParameter("address_id");
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		
		try {
			if(address_id != null && !"".equals(address_id)) {
				Address address = new Address();
				address.setAddress_id(Integer.parseInt(address_id));
				if(addressService.relieveAddressWithUser(address,(User)req.getSession().getAttribute("user") ))
					json.addProperty("state", 1);
			}
			resp.getWriter().print(json.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
