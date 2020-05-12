package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.ITyepDao;
import cxw.yztz.dao.daoImpl.TypeDaoImpl;
import cxw.yztz.entity.Type;
import cxw.yztz.service.ITypeService;
import cxw.yztz.utils.HibernateUtils;

public class TypeServiceImpl implements ITypeService {
	private ITyepDao typeDao = new TypeDaoImpl();
	public TypeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<?> getTypes() throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return typeDao.getType();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public boolean saveType(Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try{
			return typeDao.saveType(type);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public boolean deleteType(Type type) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			return typeDao.deleteType(type);
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
	public boolean updateTye(Type type) throws Exception {
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			return typeDao.updateTye(type);
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
	public Type getType(Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return typeDao.getType(type);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

}
