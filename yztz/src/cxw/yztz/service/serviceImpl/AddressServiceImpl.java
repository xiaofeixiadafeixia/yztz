package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;

import cxw.yztz.dao.IAddressDao;
import cxw.yztz.dao.daoImpl.AddressDaoImpl;
import cxw.yztz.entity.Address;
import cxw.yztz.entity.College;
import cxw.yztz.entity.User;
import cxw.yztz.service.IAddressService;
import cxw.yztz.utils.HibernateUtils;

public class AddressServiceImpl implements IAddressService{

	private IAddressDao IAddressDao = new AddressDaoImpl();
	public AddressServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<?> getColleges() throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		List<?> list;
		try {
			list = IAddressDao.getColleges();
		}finally {
			HibernateUtils.closeSession();
		}
		return list;
	}

	@Override
	public List<?> getAddressByUserWithCollege(User u,College college) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		List<?> list = IAddressDao.getAddressByUserWithCollege(u,college);
		
		HibernateUtils.closeSession();
		return list;
	}

	@Override
	public boolean updateAddressByAddress_id(Address address) throws Exception {
		// TODO Auto-generated method stub
		
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		try {
			boolean flag = IAddressDao.updateAddressByAddress_id(address);
			beginTransaction.commit();
			return flag;
		}catch(Exception e) {
			beginTransaction.rollback();
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public boolean saveAddress(Address address) throws Exception {
		// TODO Auto-generated method stub
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		try {
			IAddressDao.saveAddress(address);
			beginTransaction.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
			return false;
		}finally{
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public boolean relieveAddressWithUser(Address address, User user) throws Exception {
		// TODO Auto-generated method stub
		Transaction beginTransaction = HibernateUtils.getTransactionAndOpenSession();
		try {
			address = IAddressDao.getAddress(address);
			if(address.getUser_id()==user.getId()) {
				address.setUser_id(null);
			}
			beginTransaction.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
			return false;
		}finally {
			HibernateUtils.closeSession();
		}
		
		
	}
	
}
