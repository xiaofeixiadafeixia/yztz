package cxw.yztz.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.ICollectDao;
import cxw.yztz.dao.IOrderDao;
import cxw.yztz.dao.daoImpl.CollectDaoImpl;
import cxw.yztz.dao.daoImpl.OrderDaoImpl;
import cxw.yztz.entity.Address;
import cxw.yztz.entity.Order;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.service.IOrderService;
import cxw.yztz.utils.HibernateUtils;
import cxw.yztz.utils.PageModel;
import cxw.yztz.utils.UUIDUtils;

public class OrderServiceImpl implements IOrderService{
	private ICollectDao iCollectDao = new CollectDaoImpl();
	private IOrderDao iOrderDao = new OrderDaoImpl();
	
	@Override
	public boolean saveOrder(User user, List<Product> list,Address address) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			Integer pids[] = new Integer[list.size()];//存储要下单的商品id
			for(int i=0;i<list.size();i++) {
				pids[i] = list.get(i).getGoods_id();
			}
			iCollectDao.deleteCollectByProducts(pids);
			//组装订单
			List<Order> orders = new ArrayList<Order>();
			for(int i=0;i<list.size();i++) {
				orders.add(new Order(UUIDUtils.getIntId(), user.getId(), list.get(i), address, list.get(i).getPrice(), new Date(),
						null, 1));
			}
			iOrderDao.saveOrders(orders);
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
	public List<?> getOrder(User user) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return iOrderDao.getOrdersByUser(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean deleteOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			iOrderDao.deleteOrder(order);
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
	public PageModel getOrders(User user, Integer currentPage, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			PageModel pm = new PageModel(currentPage, iOrderDao.getOrdersCount(user), pageSize);
			pm.setRecords(iOrderDao.getOrdersByUserWithPage(user, pm.getStartIndex(), pm.getPageSize()));
			return pm;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	
	
}
