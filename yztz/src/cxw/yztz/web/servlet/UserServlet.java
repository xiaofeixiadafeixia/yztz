package cxw.yztz.web.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.JsonObject;

import cxw.yztz.entity.User;
import cxw.yztz.service.IUserService;
import cxw.yztz.service.serviceImpl.UserServiceImpl;
import cxw.yztz.utils.IsImage;
import cxw.yztz.utils.UUIDUtils;
import cxw.yztz.utils.UpLoadUtils;
import cxw.yztz.utils.VCode;
import cxw.yztz.web.base.BaseServlet;

public class UserServlet extends BaseServlet{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserService IUserService = new UserServiceImpl();
	
	
	/**
	 * 用户退出
	 * @param req
	 * @param resp
	 * @return
	 */
	public String userExit(HttpServletRequest req,HttpServletResponse resp) {
		Cookie c1 = new Cookie("yztzEmail",null);
		Cookie c2 = new Cookie("yztzPassword", null);
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		resp.addCookie(c1);
		resp.addCookie(c2);
		req.getSession().invalidate();
		return "/index.jsp";
	}
	
	/**
	 * 获取图片验证码
	 * @param req
	 * @param resp
	 * @return null
	 */
	public String getPicVerificationCode(HttpServletRequest req,HttpServletResponse resp) {
		VCode vd = new VCode(4);
		
		//获取验证码并存入session中
		String vCode = vd.generatorVCode();
		req.getSession().setAttribute("vCode", vCode);
		
		//根据验证码生成图片
		BufferedImage generatorRotateVCodeImage = vd.generatorRotateVCodeImage(vCode, true);
		
		//组装图片验证码的绝对路径
		String vCodeUrl =  req.getServletContext().getRealPath("/")+File.separator+"vCodeImg" + File.separator + UUID.randomUUID()+".jpg";
		File f = new File(vCodeUrl);
		if(!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		
		if(!f.exists()) {
			try {
				ImageIO.write(generatorRotateVCodeImage, "jpg", new FileOutputStream(f));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("state", 1);
		json.addProperty("imgUrl", req.getContextPath()+vCodeUrl.replace(req.getServletContext().getRealPath("/"),""));
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String userUpdate(HttpServletRequest req,HttpServletResponse resp) {
		Object verificationCode = req.getSession().getAttribute("verificationCode");//获取邮件验证码
		req.getSession().removeAttribute("verificationCode");//不管如何有没有都要先把邮件验证码删除。避免用户钻空子
		
		String name=null,email=null,sex=null,info=null,pic=null,code=null;
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);//默认为错误状态
		
		User user = (User)req.getSession().getAttribute("user");
	
		Iterator<?> it = UpLoadUtils.getIterator(req);
		byte b[] = new byte[1024];
		try{
			while(it.hasNext()) {
				FileItem fi = (FileItem)it.next();
				if(fi.isFormField()) {//普通字段
					switch(fi.getFieldName()) {
						case "name":name = fi.getString("utf-8");break;
						case "info":info = fi.getString("utf-8");break;
						case "sex":sex = fi.getString("utf-8");break;
						case "email":email= fi.getString("utf-8");break;
						case "code": code = fi.getString("utf-8");break;
					}
					
				}else {
					if(fi.getName()!=null&&!"".equals(fi.getName())) {
						//判断是否是图片格式
						if(!IsImage.TYPE_UNKNOWN.equals(IsImage.getPicType(fi.getInputStream()))) {
							String UUID = UUIDUtils.getId();
							String houzui = "."+fi.getName().split("\\.")[1];//图片后缀
							String path =this.getServletContext().getRealPath("/")+File.separator+"img"+File.separator+UUID+houzui;
							File file = new File(path);
							if(!file.getParentFile().exists()) {
								file.getParentFile().mkdirs();
							}
							file.createNewFile();
							InputStream is = fi.getInputStream();
							OutputStream os = new FileOutputStream(file);
							int a=0;
							while((a=is.read(b))!=-1) {
								os.write(b, 0, a);
							}
							pic = "http://" + req.getServerName()+ req.getContextPath()+"/img/"+UUID+houzui;
							is.close();
							os.close();
						}else {
							json.addProperty("errorMessage", "您上传头像的不是图片文件");
							resp.getWriter().print(json.toString());
							return null;
						}
					}
					
				}//else
			}//while
		}catch(Exception e) {
			json.addProperty("errorMessage", "系统出错，请联系站长");
			try {
				resp.getWriter().print(json.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
			
		if(email!=null && !email.equals(user.getEmail())) {//如果用户更改邮箱
			if(code == null || verificationCode == null || !code.equals(verificationCode.toString())) { //如果邮件验证码不一致
				json.addProperty("errorMessage", "邮件验证码不一致");
				try {
					resp.getWriter().print(json.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}else 
				user.setEmail(email);
		}
		user.setInfo(info);
		user.setSex(sex);
		user.setName(name);
		if(pic!=null) {
			user.setPic(pic);
		}
		try {
			IUserService.userUpdate(user);
			json.addProperty("state", 1);
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("errorMessage", "更改失败！");
		}
		
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		return "UIServlet?method=myCenterUI";
		return null;
	}
	
	
	
	
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 * @return
	 */
	public String userLogin(HttpServletRequest req,HttpServletResponse resp) {
		String code = req.getParameter("vCode");
		String isAutoLogin = req.getParameter("isAutoLogin");
		JsonObject json = new JsonObject();
		//默认状态码为0（表示错误）
		json.addProperty("state", 0);
		
		//传送来的验证码不为空，并且匹配
		if(code!=null && req.getSession().getAttribute("vCode").toString()!=null && code.equalsIgnoreCase(req.getSession().getAttribute("vCode").toString())) {
			User u = new User();
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			u.setEmail(email);
			u.setPassword(password);
			u.setLast_date(new Date());
			try {
				User user = IUserService.userLogin(u);
				if(user!=null) {
					//如果点了自动登录，则设置cookie
					if("on".equals(isAutoLogin)) {
						Cookie emailCookie = new Cookie("yztzEmail", URLEncoder.encode(user.getEmail(),"utf-8"));
						Cookie passWordCookie = new Cookie("yztzPassword", URLEncoder.encode(user.getPassword(),"utf-8"));
						emailCookie.setMaxAge(7*24*60*60);
						passWordCookie.setMaxAge(7*24*60*60);
						resp.addCookie(emailCookie);
						resp.addCookie(passWordCookie);
					}
					req.getSession().setAttribute("user", user);
					
					//如果有页面是通过这个验证身份则把回调地址发回。
					if(req.getSession().getAttribute("lastRequestUrl")!=null) {
						json.addProperty("state", 2);
					}else
						json.addProperty("state", 1);
					
				}else {
					json.addProperty("errorMessage", "账号或密码错误");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			json.addProperty("errorMessage", "验证码不正确");
		}
		try {
			resp.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @return
	 */
	public String userResgiter(HttpServletRequest req,HttpServletResponse resp) {
		String verificationCode = req.getParameter("verificationCode");//接收验证码
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);//默认为错误状态
		
		Object  verificationCodeLast = req.getSession().getAttribute("verificationCode");//获取本地生成的验证码
		req.getSession().removeAttribute("verificationCode");//这里把邮箱验证码清空。
		
		//判断验证码是否一致
		if(verificationCode!=null && verificationCodeLast!=null && verificationCode.equals(verificationCodeLast.toString())) {
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			Object email = req.getSession().getAttribute("email");//获得发送验证码到的邮箱
			
			//判断用户名与密码格式是否正确
			if(name!=null&&password!=null&&email!=null 
					&& name.length()<=10&&name.length()>0 && password.length()<=16&&password.length()>5) {
				User u = new User();
				u.setEmail(email.toString());
				u.setName(name);
				u.setPassword(password);
				u.setRecord_date(new Date());
				u.setSex("男");
				u.setInfo("很懒哦，没有设置");
				u.setRemind_type(39);
				u.setPic("http://www.cxw2.online/yztz/img/enheng.jpg");
				try {
					if(IUserService.userRegister(u)) {
						json.addProperty("state", 1);
					}else {
						json.addProperty("errorMessage", "邮箱已存在");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					json.addProperty("errorMessage", "系统出现异常！");
				}
				
			}else {
				json.addProperty("errorMessage", "昵称与密码格式不正确！");
			}
		}else {
			json.addProperty("errorMessage", "验证码不正确！");
		}
		
		try{
    		resp.getWriter().print(json.toString());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
		return null;
	}
	/**
	 * 用于用户更改邮件提醒
	 * @param req
	 * @param resp
	 * @return
	 */
	public String updateEmailNote(HttpServletRequest req,HttpServletResponse resp) {
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);
		Integer remind_type = Integer.parseInt(req.getParameter("remind_type"));
		User u = (User)req.getSession().getAttribute("user");
		try {
			u.setRemind_type(remind_type);
			IUserService.userUpdate(u);
			json.addProperty("state", 1);
			resp.getWriter().print(json.toString());
		}catch(Exception e) {
			
		}
		return null;
	}
	
	
	/**
	 * 用于判断邮件是否注册
	 * @param req
	 * @param resp
	 * @return 为true则存在，否则不存在
	 */
	public boolean findEmail(HttpServletRequest req,HttpServletResponse resp) {
		String email = req.getParameter("email").trim();
		JsonObject json = new JsonObject();
		try {
			boolean flag ;
			if(email!=null&&(flag=IUserService.findEmail(email))) {
				json.addProperty("state", 0);
				json.addProperty("errorMessage", "邮箱已存在，请仔细核对，或尝试找回密码！");
				resp.getWriter().print(json.toString());
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 用于发送邮件验证码
	 * @param req
	 * @param resp
	 * @return
	 */
    public String sendEmailVerificationCode(HttpServletRequest req,HttpServletResponse resp) {
    	if(!findEmail(req,resp)) {
    		req.getSession().setAttribute("email",req.getParameter("email").trim());
        	
        	StringBuffer code = new StringBuffer();
        	Random r = new Random();
        	for(int x=0;x<6;x++) {
        		code.append(r.nextInt(10));
        	}
        	
        	boolean flag = false;
    		try {
    			flag = IUserService.sendEmail(req.getParameter("email").trim(),"永职跳蚤注册验证码","您本次的验证码是："+code.toString()+"请尽快填写！");
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	
        	JsonObject json = new JsonObject();
        	if(flag) { 
        		json.addProperty("state", 1);
        		req.getSession().setAttribute("verificationCode",code );
        		req.getSession().setAttribute("email",req.getParameter("email").trim());
        	}else {
        		json.addProperty("state", 0);
        		json.addProperty("errorMessage", "邮件发送失败！");
        	}
        	
        	try{
        		resp.getWriter().print(json.toString());;
        	}catch(IOException e) {
        		e.printStackTrace();
        	}
    	}
    	
    	return null;
    }
    /**
     * 图方便就没有直接复制上面那个发邮件的方法了。这个是邮件判断存在则发送。
     * @param req
     * @param resp
     * @return
     */
    public String findPassSendEmail(HttpServletRequest req,HttpServletResponse resp) {
    	String email = req.getParameter("email").trim();
    	JsonObject json = new JsonObject();
		json.addProperty("state", 0);
    	try {
			if(IUserService.findEmail(email)) {
				req.getSession().setAttribute("email",req.getParameter("email").trim());
				StringBuffer code = new StringBuffer();
				Random r = new Random();
				for(int x=0;x<6;x++) {
					code.append(r.nextInt(10));
				}
				boolean flag = false;
				flag = IUserService.sendEmail(req.getParameter("email").trim(),"永职跳蚤注册验证码","您本次的验证码是："+code.toString()+"请尽快填写！");
				
				if(flag) { 
					json.addProperty("state", 1);
					req.getSession().setAttribute("verificationCode",code );
					req.getSession().setAttribute("email",req.getParameter("email").trim());
				}else {
					json.addProperty("errorMessage", "邮件发送失败！");
				}
				
			}else {
				json.addProperty("errorMessage", "该邮箱未注册");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	try{
    		resp.getWriter().print(json.toString());;
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public String findPassWord(HttpServletRequest req,HttpServletResponse resp) {
    	String verificationCode = req.getParameter("verificationCode");//接收验证码
		JsonObject json = new JsonObject();
		json.addProperty("state", 0);//默认为错误状态
		
		Object  verificationCodeLast = req.getSession().getAttribute("verificationCode");//获取本地生成的验证码
		req.getSession().removeAttribute("verificationCode");//这里把邮箱验证码清空。
    	try {
    		//判断验证码是否一致
    		if(verificationCode!=null && verificationCodeLast!=null && verificationCode.equals(verificationCodeLast.toString())) {
    			String password = req.getParameter("password");
    			Object email = req.getSession().getAttribute("email");//获得发送验证码到的邮箱
    			User u = new User();
    			u.setEmail(email.toString());
    			if(IUserService.updatePassword(u, password)) 
    				json.addProperty("state", 1);
    		}
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
