package cxw.yztz.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import cxw.yztz.entity.BrowseHistory;
import cxw.yztz.entity.BrowseHistoryUionPKID;
import cxw.yztz.entity.Collect;
import cxw.yztz.entity.CollectUionPKID;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;
import cxw.yztz.service.IAddressService;
import cxw.yztz.service.IBrowseHistoryService;
import cxw.yztz.service.ICollectService;
import cxw.yztz.service.IOrderService;
import cxw.yztz.service.IProductService;
import cxw.yztz.service.ITypeService;
import cxw.yztz.service.serviceImpl.AddressServiceImpl;
import cxw.yztz.service.serviceImpl.BrowseHistoryServiceImpl;
import cxw.yztz.service.serviceImpl.CollectServiceImpl;
import cxw.yztz.service.serviceImpl.OrderServiceImpl;
import cxw.yztz.service.serviceImpl.ProductServiceImpl;
import cxw.yztz.service.serviceImpl.TypeServiceImpl;
import cxw.yztz.utils.PageModel;
import cxw.yztz.web.base.BaseServlet;

/**
 * 负责页面的跳转逻辑
 * @author 24780
 *
 */
public class UIServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5931535433021013268L;
	
	private IAddressService iAddressService = new AddressServiceImpl();
	private IProductService iProductService = new ProductServiceImpl();
	private ICollectService iCollectService = new CollectServiceImpl();
	private IBrowseHistoryService iBrowseHistoryService = new BrowseHistoryServiceImpl();
	private IOrderService iOrderService = new OrderServiceImpl();
	private ITypeService iTypeService = new TypeServiceImpl();
	
	public UIServlet() {
		// TODO Auto-generated constructor stub
	}
	public String loginUI(HttpServletRequest req,HttpServletResponse resp) {
		return "/login.html";
	}
	public String registerUI(HttpServletRequest req,HttpServletResponse resp) {
		return "/register.html";
	}
	public String findPasswordUI(HttpServletRequest req,HttpServletResponse resp) {
		return "/findPass.html";
	}
	/**
	 * 回到上次要访问的地址
	 * @param req
	 * @param resp
	 * @return
	 */
	public String lastRequestUrl(HttpServletRequest req,HttpServletResponse resp) {
		Object obj = req.getSession().getAttribute("lastRequestUrl");
		return obj==null?mainUI(req,resp):"UIServlet?method="+obj.toString();
	}
	
	public String myCenterUI(HttpServletRequest req,HttpServletResponse resp) {
		Object obj = req.getSession().getAttribute("user");
		if(obj!=null) {
			User user = (User)obj;
			try {
				req.setAttribute("colleges", this.iAddressService.getColleges());
				req.setAttribute("browseHistorys", this.iBrowseHistoryService.getBrowseHistorysByCount(user, 0, 8));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/jsp/my_center.jsp";
		}
		return this.skipUrl(false,req, resp, "/login.html","myCenterUI");
	}
	
	public String mainUI(HttpServletRequest req,HttpServletResponse resp) {
		String currentNum = req.getParameter("currentPageNum");
		if(currentNum==null)
			currentNum="1";
		try {
			req.setAttribute("newProducts", iProductService.getProductsBySort(12, "time"));
			PageModel pm = iProductService.getAllProductsByCurrentPageNum(Integer.valueOf(currentNum));
			pm.setUrl("UIServlet?method=mainUI");
			req.setAttribute("pm", pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/jsp/index.jsp";
	}
	
	public String productListUI(HttpServletRequest req,HttpServletResponse resp) {
		String num = req.getParameter("num");
		String type_id = req.getParameter("type_id");
		String str = req.getParameter("str");
		PageModel pm =null;
		try {
			if(num==null)
				num = "1";
			if(type_id!=null) {
				Type type = iTypeService.getType(new Type(Integer.valueOf(type_id), null));
				req.setAttribute("type", type);
				pm = iProductService.getProductsByCurrentPageNumWithType(Integer.parseInt(num), type);
				pm.setUrl("UIServlet?method=productListUI&type_id="+type_id);
			}else {
				req.setAttribute("str", str);
				pm = iProductService.getProductsByFuzzyQueryWhitCurrentPage(str, Integer.valueOf(num), 18);
				pm.setUrl("UIServlet?method=productListUI&str="+str);
			}
			
			req.setAttribute("pm",pm );
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "jsp/product_list.jsp";
	}
	
	/**
	 * 如果要访问A页面，需要跳转一个页面验证后，在返回A页面，则可用此方法。
	 * 但在验证页面验证完毕后调用本类的lastRequestUrl方法进行判断
	 * 
	 * 例如：   所在页面为A  ，要访问页面B ，验证页面为 C
	 * 	则在 A -> B 过程中调用本方法，然后 到 C，C页面验证完毕后调用本类的lastRequestUrl（）方法返回B页面
	 * @param req
	 * @param resp
	 * @param url 验证页面 的路径(一般为登录页面) （使用重定向 response）
	 * @param lastRequestUrl 需要访问的页面 
	 * @param flag 如果为真则用客户端跳转，否则用服务端跳转
	 * @return  返回需要跳转路径（服务端跳转 request）
	 */
	
	private String skipUrl(boolean flag,HttpServletRequest req,HttpServletResponse resp,String url,String lastRequestUrl) {
		
		
		if(flag) {
			//设置上次要访问的路径为这个，登录后可以直接取出这个地址并访问
			req.getSession().setAttribute("lastRequestUrl", lastRequestUrl);
			try {
				resp.sendRedirect(req.getContextPath()+url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else{

			//禁止页面缓存
			resp.setHeader("Pragma","No-cache"); 
			resp.setHeader("Cache-Control","no-cache"); 
			resp.setDateHeader("Expires", 0);  
			//设置上次要访问的路径为这个，登录后可以直接取出这个地址并访问
			req.getSession().setAttribute("lastRequestUrl", lastRequestUrl);
			return url;
		}
		
	}
	
	
	/**
	 * 跳转到 “我的收藏”页面 如果有商品id顺带着添加收藏记录
	 * @param req
	 * @param resp
	 * @return
	 */
	public String myCollectionUI(HttpServletRequest req,HttpServletResponse resp) {
		User user = (User)req.getSession().getAttribute("user");
		if(user!=null) {
			
			//取出用户所有的收藏记录
			try {
				req.setAttribute("collects", iCollectService.getCollectsByUser(user));
				//取出所有学院的地址
				req.setAttribute("colleges", iAddressService.getColleges());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return "jsp/my_collection.jsp";
		}
		return this.skipUrl(false,req, resp, "/login.html","myCollectionUI");
		
	}
	
	public String myProductAddUI(HttpServletRequest req,HttpServletResponse resp){
		if(req.getSession().getAttribute("user")!=null) {
			return "jsp/my_product_add.jsp";
		}
		return this.skipUrl(false,req, resp, "/login.html","myProductAddUI");
		
	}
	
	public String myProductUI(HttpServletRequest req,HttpServletResponse resp) {
		Object user = req.getSession().getAttribute("user");
		if(user!=null) {
			String currentPageNum = req.getParameter("currentPageNum");
			if(currentPageNum==null)
				currentPageNum="1";
			try {
				PageModel pm = iProductService.getProductsByCurrentPageNumWithUser((User)user, Integer.parseInt(currentPageNum));
				pm.setUrl("UIServlet?method=myProductUI");
				req.setAttribute("pm", pm);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "jsp/my_product.jsp";
		}
		return this.skipUrl(false,req, resp, "/login.html","myProductUI");
	}
	
	/**
	 * 跳转到商品详情页
	 * @param req
	 * @param resp
	 * @return
	 */
	public String productInfoUI(HttpServletRequest req,HttpServletResponse resp) {
		String product_id = req.getParameter("goods_id");
		User user = (User)req.getSession().getAttribute("user");
		if(product_id!=null) {
			try {
				//获取到 商品信息 
				Product product = iProductService.getProductById(Integer.parseInt(product_id));
				req.setAttribute("product", product);

				
				//获取收藏次数
				int count = iCollectService.getCollectCountByProduct(product);
				req.setAttribute("collectCount", count);
				
				
				req.setAttribute("isCanCollect", "1");
				Product p = iProductService.getProductById(Integer.parseInt(product_id));
				if(p.getState()==4) {//为空说明用户已删除
					req.setAttribute("isCanCollect", "4");
				}else if(user!=null) {
					//添加浏览记录
					iBrowseHistoryService.saveBrowseHistory(new BrowseHistory(new BrowseHistoryUionPKID(user.getId(), product), new Date()));
					
					//判断这个商品是否在收藏记录里
					Collect collect = iCollectService.getCollectBycollectUnionPKID(
							new Collect(new CollectUionPKID(user.getId(), product), null));
					if(collect!=null) {
						req.setAttribute("isCanCollect", "2");
					}else {
					//判断是否自己上传
						if(p.getUser().getId()==user.getId()) {
							req.setAttribute("isCanCollect", "3");
						}
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "jsp/product_info.jsp";
	}
	
	
	public String collectProduct(HttpServletRequest req,HttpServletResponse resp) {
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		
		User user = (User)req.getSession().getAttribute("user");
		String product_id = req.getParameter("product_id");
		if(user!=null) {
			if(product_id!=null) {
					try {
						Product p = new Product();
						p.setGoods_id(Integer.parseInt(product_id));
						Collect collect = new Collect(new CollectUionPKID(user.getId(),p), new Date());
						iCollectService.saveCollect(collect);
						json.addProperty("state", 1);
					}catch(Exception e) {
						e.printStackTrace();
					}
				
			}
			try {
				resp.getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			json.addProperty("errorMessage", "NoUser");
			try {
				resp.getWriter().print(json.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return this.skipUrl(false,req, resp, null,"productInfoUI&"+req.getQueryString().split("&")[1]);
		}
		return null;
	}
	
	public String orderUI(HttpServletRequest req, HttpServletResponse resp) {
		Object obj = (User)req.getSession().getAttribute("user");
		String currentPageNum = req.getParameter("currentPageNum");
		if(currentPageNum==null)
			currentPageNum = "1";
		if(obj!=null) {
			try {
				User user = (User)obj;
				PageModel pm = iOrderService.getOrders(user, Integer.parseInt(currentPageNum), 5);
				pm.setUrl("UIServlet?method=orderUI");
				req.setAttribute("pm",pm);
			}catch(Exception e) {
				e.printStackTrace();
				return "UIServlet?method=mainUI";
			}
			return "jsp/my_order.jsp";
		}
		return this.skipUrl(false,req, resp, "/login.html","orderUI");
	}
}
