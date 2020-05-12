package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.IBrowseHistoryDao;
import cxw.yztz.dao.daoImpl.BrowseHistoryDaoImpl;
import cxw.yztz.entity.BrowseHistory;
import cxw.yztz.entity.User;
import cxw.yztz.service.IBrowseHistoryService;
import cxw.yztz.utils.HibernateUtils;

public class BrowseHistoryServiceImpl implements IBrowseHistoryService{
	private IBrowseHistoryDao  iBrowseHistoryDao = new BrowseHistoryDaoImpl();
	
	@Override
	public boolean saveBrowseHistory(BrowseHistory bh) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			BrowseHistory browseHistory = iBrowseHistoryDao.getBrowseHistory(bh);
			if(browseHistory!=null) {//如果存在这条浏览记录
				browseHistory.setTime(bh.getTime());
				iBrowseHistoryDao.updateBrowseHistory(browseHistory);
			}else
				iBrowseHistoryDao.saveBrowseHistory(bh);
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
	public boolean deleteBrowseHistory(BrowseHistory bh) throws Exception {
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			return iBrowseHistoryDao.deleteBrowseHistory(bh);
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
	public List<?> getBrowseHistorys(User user) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iBrowseHistoryDao.getBrowseHistorys(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public List<?> getBrowseHistorysByCount(User user, Integer start, Integer count) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iBrowseHistoryDao.getBrowseHistorysByCount(user, start, count);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

}
