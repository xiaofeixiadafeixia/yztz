package cxw.yztz.dao;

import java.util.List;

import cxw.yztz.entity.Order;
import cxw.yztz.entity.User;

public interface IOrderDao {
	/**
	 * 保存一个订单
	 * @param order
	 * @return
	 * @throws Exception
	 */
	boolean saveOrder(Order order) throws Exception;
	
	/**
	 * 保存多个订单
	 * @param orders
	 * @return
	 * @throws Exception
	 */
	boolean saveOrders(List<Order> orders) throws Exception;
	
	/**
	 * 获取这个用户的所有订单数量
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Integer getOrdersCount(User user) throws Exception;
	
	/**
	 * 查询用户的所有订单
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getOrdersByUser(User user ) throws Exception;
	
	/**
	 * 分页查询
	 * @param user
	 * @param start开始的索引
	 * @param max 查询记录数
	 * @return
	 * @throws Exception
	 */
	List<?> getOrdersByUserWithPage(User user,Integer start,Integer max) throws Exception;
	
	/**
	 * 删除一个订单
	 * @param order 包含订单号
	 * @return
	 * @throws Exception
	 */
	boolean deleteOrder(Order order) throws Exception;
	
	/**
	 * 删除多个订单
	 * @param orders
	 * @return
	 * @throws Exception
	 */
	boolean deleteOrders(Integer [] orders_id) throws Exception;
	
	
	
}
