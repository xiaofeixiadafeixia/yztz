package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.ILeaveDao;
import cxw.yztz.dao.daoImpl.LeaveDaoImpl;
import cxw.yztz.entity.Leave;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.service.ILeaveService;
import cxw.yztz.utils.HibernateUtils;
import cxw.yztz.utils.PageModelRelateToJson;

public class LeaveServiceImpl implements ILeaveService{
	private ILeaveDao iLeaveDao = new LeaveDaoImpl();
	
	@Override
	public boolean saveLeave(Leave leave) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			iLeaveDao.saveLeave(leave);
			return true;
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

	@Override
	public boolean deleteLeave(Leave leave) throws Exception {
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			iLeaveDao.deleteLeave(leave);
			return true;
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

	@Override
	public boolean deleteLeaves(Product product) throws Exception {
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			iLeaveDao.deleteLeaves(product);
			return true;
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

	@Override
	public List<?> getLeaves(User user) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iLeaveDao.getLeaves(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public List<?> getLeaves(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iLeaveDao.getLeaves(product);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageModelRelateToJson<Product> getLeaves(PageModelRelateToJson<Product> pm) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			pm.setRecord((List<Product>)iLeaveDao.getLeaves(pm.getT(), pm.getStartIndex(), pm.getPageNum()));
			return pm; 
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public Integer getLeaveCount(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iLeaveDao.getLeaveCount(product);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

}
