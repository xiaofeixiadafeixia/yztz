package cxw.yztz.dao.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cxw.yztz.dao.IAddressDao;
import cxw.yztz.entity.Address;
import cxw.yztz.entity.College;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class AddressDaoImpl implements IAddressDao {

	public AddressDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<?> getColleges() throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();	
		String hql = "from College";
		return session.createQuery(hql).list();
	}

	@Override
	public List<?> getAddressByUserWithCollege(User u,College college) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		String hql = "from Address where college_id=? and user_id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0,college.getCollege_id());
		query.setInteger(1,u.getId() );
		
		return query.list();
	}

	@Override
	public boolean updateAddressByAddress_id(Address address) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		session.update(address);
		return true;
	}

	@Override
	public boolean saveAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		session.save(address);
		return true;
	}

	@Override
	public Address getAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		return (Address)session.get(Address.class,address.getAddress_id());
	}
	
	
}
