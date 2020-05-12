package cxw.yztz.dao.daoImpl;

import java.util.List;

import org.hibernate.Session;

import cxw.yztz.dao.IOrderDao;
import cxw.yztz.entity.Order;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public boolean saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().save(order);
		return true;
	}

	@Override
	public boolean saveOrders(List<Order> orders) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		for(Order o : orders) {
			session.save(o);
		}
		return true;
	}

	@Override
	public List<?> getOrdersByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Order where user_id = ? order by order_time desc").setInteger(0, user.getId()).list();
	}

	@Override
	public boolean deleteOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().delete(order);
		return true;
	}

	@Override
	public boolean deleteOrders(Integer [] orders_id) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().createQuery("delete from Order where orders_id in :ids").setParameter("ids", orders_id);
		return true;
	}

	@Override
	public Integer getOrdersCount(User user) throws Exception {
		// TODO Auto-generated method stub
		return ((Long)HibernateUtils.getSession().createQuery("select count(*) from Order where user_id = ?").
				setInteger(0, user.getId()).uniqueResult()).intValue();
		
	}

	@Override
	public List<?> getOrdersByUserWithPage(User user, Integer start, Integer max) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Order where user_id = ? order by order_time desc").setInteger(0, user.getId())
				.setFirstResult(start).setMaxResults(max).list();
	}

}
