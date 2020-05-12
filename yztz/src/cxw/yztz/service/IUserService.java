package cxw.yztz.service;

import cxw.yztz.entity.User;

public interface IUserService {
	
	boolean userRegister(User u) throws Exception;
	
	User userLogin(User u) throws Exception;
	
	boolean userUpdate(User u) throws Exception;
	
	boolean sendEmail(String to,String title,String content) throws Exception;
	
	boolean findEmail(String email) throws Exception;
	
	boolean updatePassword(User user,String password) throws Exception;
}
