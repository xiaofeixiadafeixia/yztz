package cxw.yztz.service.serviceImpl;

import java.util.Date;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.IUserDao;
import cxw.yztz.dao.daoImpl.UserDaoImpl;
import cxw.yztz.entity.User;
import cxw.yztz.service.IUserService;
import cxw.yztz.utils.HibernateUtils;
import cxw.yztz.utils.SendEmailUtils;

public class UserServiceImpl implements IUserService {

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	private IUserDao IUserDao = new UserDaoImpl();

	
	
	@Override
	public boolean userRegister(User u) throws Exception {
		// TODO Auto-generated method stub
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		try {
			boolean flag = IUserDao.userRegister(u);
			beginTransaction.commit();
			return flag;
		}catch(Exception e){
			beginTransaction.rollback();
			throw e;
		}finally {
			HibernateUtils.closeSession();
		}
	}
	
	/**
	 * 验证账号密码是否正确，
	 * 是则返回user
	 * 否则返回null
	 */
	@Override
	public User userLogin(User u) throws Exception {
		// TODO Auto-generated method stub
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		User user=null;
		try {
			user =  IUserDao.userLogin(u);
			if(user!=null) {
				HibernateUtils.getSession().clear();
				User userUpdate = (User)user.clone();
				userUpdate.setLast_date(new Date());
				IUserDao.userUpdate(userUpdate);
			}
			
			beginTransaction.commit();
		}catch(Exception e){
			beginTransaction.rollback();
		}finally {
			HibernateUtils.closeSession();
		}
		return user;
	}

	@Override
	public boolean userUpdate(User u) throws Exception {
		// TODO Auto-generated method stub
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		try {
			IUserDao.userUpdate(u);
			beginTransaction.commit();
			return true;
		}catch(Exception e) {
			beginTransaction.rollback();
			return false;
		}finally{
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public boolean sendEmail(String to, String title, String content) throws Exception {
		return SendEmailUtils.getEmailUtils().send(to,title, content);
		
	}
	
	/**查找邮箱是否存在
	 * 存在返回true
	 */
	@Override
	public boolean findEmail(String email) throws Exception {
		HibernateUtils.openSession();
		boolean flag;
		try {
			flag = IUserDao.findEmail(email);
		}finally {
			HibernateUtils.closeSession();
		}
		
		return flag;
	}

	/**
	 * 根据邮箱号修改密码
	 */
	@Override
	public boolean updatePassword(User user,String password) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			User u = IUserDao.findUser(user);
			u.setPassword(password);
			return IUserDao.userUpdate(u);
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus() == TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

}
