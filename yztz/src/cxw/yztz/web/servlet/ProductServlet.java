package cxw.yztz.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cxw.yztz.entity.College;
import cxw.yztz.entity.Picture;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;
import cxw.yztz.service.IProductService;
import cxw.yztz.service.ITypeService;
import cxw.yztz.service.serviceImpl.ProductServiceImpl;
import cxw.yztz.service.serviceImpl.TypeServiceImpl;
import cxw.yztz.utils.IsImage;
import cxw.yztz.utils.UUIDUtils;
import cxw.yztz.utils.UpLoadUtils;
import cxw.yztz.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7939868419535483455L;
	
	private ITypeService iType = new TypeServiceImpl();
	private IProductService iProductService = new ProductServiceImpl();

	public ProductServlet() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 得到所有的商品类别
	 * @param req
	 * @param resp
	 * @return
	 */
	public String getAllTypeOfProduct(HttpServletRequest req,HttpServletResponse resp) {
		try {
			List<?> types = iType.getTypes();
			resp.getWriter().print(new Gson().toJson(types));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String saveProduct(HttpServletRequest req,HttpServletResponse resp) {
		if(req.getSession().getAttribute("user")!=null) {
			JsonObject json = new JsonObject();
			json.addProperty("state", 0);
			
			String name=null,info=null;    double fineness=0,price=0;  int type_id=0,college_id=0;
			Set<Picture> picsUrl = new HashSet<Picture>();
			byte b[] = new byte[2048];
			boolean flag = true;
			Iterator<?> it = UpLoadUtils.getIterator(req);
			try {
				int index = 1;//图片顺序
				while(it.hasNext()) {
					FileItem fi = (FileItem)it.next();
					if(fi.isFormField()) {
						switch(fi.getFieldName()) {
						case "pname":name = fi.getString("utf-8");break;
						case "pdetails":info = fi.getString("utf-8");break;
						case "pfineness":fineness = Double.parseDouble(fi.getString("utf-8"));break;
						case "pprice":price = Double.parseDouble(fi.getString("utf-8"));break;
						case "type_id":type_id = Integer.parseInt(fi.getString("utf-8"));break;
						case "college_id":college_id = Integer.parseInt(fi.getString("utf-8"));break;
						}
					}else {
						if(IsImage.getPicType(fi.getInputStream())!=IsImage.TYPE_UNKNOWN) {
							String picNameRandom = UUIDUtils.getId();
							//图片生成路径
							String imgPath = req.getServletContext().getRealPath("/")+File.separator
									+"ProductImg"+File.separator+picNameRandom+"."+fi.getName().split("\\.")[1];
							File file = new File(imgPath);
							if(!file.getParentFile().exists()) {
								file.getParentFile().mkdirs();
							}
							InputStream inputFile = fi.getInputStream();//获得图片输入流
							OutputStream outFile = new FileOutputStream(file);//创建图片输出流
							int a = 0;
							//图片循环从输入流读取从输出流写出
							while((a=inputFile.read(b))!=-1) {
								outFile.write(b,0,a);
							}
							inputFile.close();
							outFile.close();
							//图片存储路径
							String imgRealPath = "http://"+req.getServerName()+req.getContextPath()+"/ProductImg/"+
							picNameRandom+"."+fi.getName().split("\\.")[1];
							//加入图片列表
							Picture picture = new Picture(null, index++, null, imgRealPath, "网页上传");
							picsUrl.add(picture);
							flag = false;
						}else {
							//进入到这里说明不是图片文件
							json.addProperty("errorMessage", "您上传文件中的藏进了一个非图片文件");
							resp.getWriter().print(json.toString());
							return null;
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				json.addProperty("errorMessage", "系统出错，请联系站长");
				try {
					resp.getWriter().print(json.toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return null;
			}
			if(flag) {
				json.addProperty("errorMessage", "请上传至少一张图片！");
			
			//以上都是接收传送过来的信息。 下面开始组装商品类并且存进数据库
			}else if(name!=null&&!"".equals(name) && fineness!=0 && price!=0) {
				Product product = new Product(UUIDUtils.getIntId(), (User)req.getSession().getAttribute("user"), 
						new Type(type_id,null), name, info, fineness, price, new Date(), 1,
						new College(college_id), picsUrl);
				
				try {
					if(iProductService.saveProduct(product))
						json.addProperty("state", 1);
					else
						json.addProperty("errorMessage", "上传失败！");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}else {
				json.addProperty("errorMessage", "商品名称，成色或价格范围不符合");
			}
			try {
				resp.getWriter().print(json.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
//	public String deleteProduct(HttpServletRequest req,HttpServletResponse resp) {
//		String id = req.getParameter("id");
//		JsonObject json = new JsonObject();
//		json.addProperty("state", 0);
//		try {
//			iProductService.deleteProductByIdWithUser(Integer.parseInt(id), (User)req.getSession().getAttribute("user"));
//			json.addProperty("state", 1);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			resp.getWriter().print(json.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 更改商品的状态 如  1.上架  2.下架
	 * @return
	 */
	public String updateProductState(HttpServletRequest req,HttpServletResponse resp) {
		String id = req.getParameter("id");
		String state = req.getParameter("state");//当前商品状态
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		try {
			Product p = new Product();
			p.setGoods_id(Integer.parseInt(id));
			if("4".equals(state)) {
				p.setState(4);
				json.addProperty("nowState", 4);
			}else if("2".equals(state)) {
				p.setState(1);
				json.addProperty("nowState", 1);
			}else {
				p.setState(2);
				json.addProperty("nowState", 2);
			}
			iProductService.updatePoductState(p, (User)req.getSession().getAttribute("user"));
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
