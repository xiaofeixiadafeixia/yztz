package cxw.yztz.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cxw.yztz.entity.User;
import cxw.yztz.service.ICollectService;
import cxw.yztz.service.serviceImpl.CollectServiceImpl;
import cxw.yztz.web.base.BaseServlet;

public class CollectServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7846976779080782376L;
	
	private ICollectService iCollectService = new CollectServiceImpl();
	
	
	public String deleteCollect(HttpServletRequest req,HttpServletResponse resp) {
		Object obj = req.getSession().getAttribute("user");
		if(obj!=null) {
			String goods_ids[] = req.getParameterValues("isCheck");
			if(goods_ids!=null) {
				User user = (User)obj;
				Integer product_ids[] = new Integer[goods_ids.length];
				for(int x=0;x<goods_ids.length;x++) {
					product_ids[x] = Integer.valueOf(goods_ids[x]);
				}
				try {
					iCollectService.deleteCollectByProducts(user, product_ids);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			return "UIServlet?method=myCollectionUI";
		}
		return "UIServlet?method=loginUI";
	}
	
}
