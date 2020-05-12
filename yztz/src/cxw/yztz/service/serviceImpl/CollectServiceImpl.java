package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.ICollectDao;
import cxw.yztz.dao.daoImpl.CollectDaoImpl;
import cxw.yztz.entity.Collect;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.service.ICollectService;
import cxw.yztz.utils.HibernateUtils;

public class CollectServiceImpl implements ICollectService{
	private ICollectDao collectDao = new CollectDaoImpl();
	
	@Override
	public boolean saveCollect(Collect collect) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			collectDao.saveCollect(collect);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public List<?> getCollectsByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return collectDao.getCollectsByUser(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public Collect getCollectBycollectUnionPKID(Collect collect) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return collectDao.getCollectBycollectUnionPKID(collect);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public Integer getCollectCountByProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return collectDao.getCollectCountByProduct(product);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean deleteCollectByProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			collectDao.deleteCollectByProduct(product);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean deleteCollectByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			collectDao.deleteCollectByUser(user);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean deleteCollectByProducts(Integer [] product_ids) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			collectDao.deleteCollectByProducts(product_ids);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean deleteCollectByProducts(User user, Integer[] product_ids) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			collectDao.deleteCollectByProducts(user, product_ids);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE)
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public List<?> getProductIdsBySort(Integer max) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return collectDao.getProductIdsBySort(max);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateUtils.closeSession();
		}
	}

	

}
