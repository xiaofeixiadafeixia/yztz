package cxw.yztz.service;

import java.util.List;

import cxw.yztz.entity.Address;
import cxw.yztz.entity.Order;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.utils.PageModel;

public interface IOrderService {
	
	/**
	 * 保存订单
	 * @param user 下单者信息
	 * @param product_ids 商品id数组
	 * @return
	 * @throws Exception
	 */
	boolean saveOrder(User user,List<Product> list,Address Address) throws Exception;
	
	/**
	 * 查询用户所有的订单。
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getOrder(User user) throws Exception;
	
	/**
	 * 分页查询
	 * @param user
	 * @param pm
	 * @return
	 * @throws Exception
	 */
	PageModel getOrders(User user,Integer currentPage,Integer pageSize) throws Exception;
	
	/**
	 * 删除订单信息
	 * @param order  包含订单id
	 * @return
	 * @throws Exception
	 */
	boolean deleteOrder(Order order) throws Exception;
}
