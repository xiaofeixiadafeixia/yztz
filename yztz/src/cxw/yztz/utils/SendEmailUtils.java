package cxw.yztz.utils;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

import cxw.yztz.entity.User;

public class SendEmailUtils {

	 private static String account="2478038920@qq.com";//登录用户名 chenxuwen@cxw2.online
     private static String pass="tejwbhttolqmecfj";        //登录密码 tejwbhttolqmecfj Cxw1314520
    private static String from="2478038920@qq.com";        //发件地址 2478038920@qq.com chenxuwen@cxw2.online
     private static String host="smtp.qq.com";        //服务器地址 smtp.exmail.qq.com
     private static String port="465";        //端口 25   465
    private static String protocol="stmp"; //协议
   
     static class MyAuthenricator extends Authenticator{  
         String u = null;  
         String p = null;  
         public MyAuthenricator(String u,String p){  
             this.u=u;  
             this.p=p;  
         }  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(u,p);  
      }  
     }
     
     
     
     
     
     
     
     
     private static SendEmailUtils seu = new SendEmailUtils();
     
     private Session session;
     
     private SendEmailUtils() {
    	init();
     }
     
     public static SendEmailUtils getEmailUtils() {
    	 return seu;
     }
     
     private void init(){
         Properties prop = new Properties();
         //协议
         prop.setProperty("mail.transport.protocol", protocol);
         //服务器
         prop.setProperty("mail.smtp.host", host);
         //端口
         prop.setProperty("mail.smtp.port", port);
         //使用smtp身份验证
         prop.setProperty("mail.smtp.auth", "true");
         
         //使用SSL，企业邮箱必需！
         //开启安全协议
         MailSSLSocketFactory sf = null;
         try {
             sf = new MailSSLSocketFactory();
             sf.setTrustAllHosts(true);
         } catch (GeneralSecurityException e1) {
             e1.printStackTrace();
         }
         prop.put("mail.smtp.ssl.enable", "true");
         prop.put("mail.smtp.ssl.socketFactory", sf);
         
         this.session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
//         session.setDebug(true);
         
        
        
     }
     
     /**
      * 
      * @param to 接收者
      * @param title 标题
      * @param content 内容
      * @return
      */
     public boolean send(String to,String title,String content) {
    	 
    	 try {
    		 MimeMessage mimeMessage = new MimeMessage(this.session);
             mimeMessage.setFrom(new InternetAddress(from,"永职跳蚤"));
             mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
             mimeMessage.setSubject(title);
             mimeMessage.setSentDate(new Date());
             mimeMessage.setText(content);
             mimeMessage.saveChanges();
            Transport.send(mimeMessage);
            mimeMessage = null;
            return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
     /**
      * 
      * @param user下单的用户
      * @param emailMap 包含下单的商品信息以及对应的卖家邮箱
      */
     public void sendEmails(User user,Map<String,List<String>> emailMap) {
    	
    	for(Entry<String, List<String>> en : emailMap.entrySet()) {
    		try {
    			 new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
		        			 MimeMessage mimeMessage = new MimeMessage(session);
		                     mimeMessage.setFrom(new InternetAddress(from,"永职跳蚤"));
		                     mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(en.getKey()));
		                     mimeMessage.setSubject("永职跳蚤——订单通知");
		                     mimeMessage.setSentDate(new Date());
		                     mimeMessage.setText("用户邮箱为："+user.getEmail()+"下单了你的商品如下："+
		                    		 en.getValue().toString()+"系统消息，请勿回复！");
		                     mimeMessage.saveChanges();
		                    Transport.send(mimeMessage);
		                    mimeMessage = null;
		        		 }catch(Exception e) {
		        			 e.printStackTrace();
		        			 System.out.println("发送邮件出错");
		        		 }
					}
				}).start();
    		}catch(Exception e) {
    			e.printStackTrace();
    			System.out.println("线程出错");
    		}
    	}
     }
}
