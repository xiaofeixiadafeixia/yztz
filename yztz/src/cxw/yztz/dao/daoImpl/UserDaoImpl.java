package cxw.yztz.dao.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;

import cxw.yztz.dao.IUserDao;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class UserDaoImpl implements IUserDao {
	
	@Override
	public boolean userRegister(User u) throws Exception {
		// TODO Auto-generated method stub
		
		Session s=HibernateUtils.getSession();
		s.save(u);
		
		return true;
	}
	
	/**
	 * 根据邮箱和密码查询并返回
	 */
	@Override
	public User userLogin(User u) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		String hql = "from User where email = ? and password = ?";
		Query query = session.createQuery(hql);
		query.setString(0, u.getEmail());
		query.setString(1, u.getPassword());
		
		Object obj = query.uniqueResult();
		if(obj!=null)
			return (User)obj;
		return null;
	}

	@Override
	public boolean userUpdate(User u) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		session.update(u);
		return true;
	}

	/**
	 * 如果存在返回true
	 */
	@Override
	public boolean findEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Session s = HibernateUtils.getSession();
		String hql = "from User where email = ?";
		Query query = s.createQuery(hql);
		query.setString(0, email);
		Object obj = query.uniqueResult();
		return obj==null?false:true;
	}

	@Override
	public User findUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return (User)HibernateUtils.getSession().createQuery("from User where email = ?").setString(0, user.getEmail()).uniqueResult();
	}


}
