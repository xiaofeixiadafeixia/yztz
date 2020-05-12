package cxw.yztz.dao;

import cxw.yztz.entity.User;

public interface IUserDao {
	
	boolean userRegister(User u) throws Exception;
	
	User userLogin(User u) throws Exception;
	
	boolean userUpdate(User u) throws Exception;
	
	boolean findEmail(String email) throws Exception ;
	
	User findUser(User user) throws Exception;
}
