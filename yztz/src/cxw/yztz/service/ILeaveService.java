package cxw.yztz.service;

import java.util.List;

import cxw.yztz.entity.Leave;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.utils.PageModelRelateToJson;

public interface ILeaveService {
	/**
	 * 保存留言
	 * @param leave
	 * @return
	 * @throws Exception
	 */
	boolean saveLeave(Leave leave) throws Exception;
	
	/**
	 * 删除留言
	 * @param leave
	 * @return
	 * @throws Exception
	 */
	boolean deleteLeave(Leave leave) throws Exception;
	
	/**
	 * 删除关于这个商品的所有留言信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean deleteLeaves(Product product)throws Exception;
	
	/**
	 * 查询这个商品的留言总数
	 * @param product
	 * @return
	 * @throws Exception
	 */
	Integer getLeaveCount(Product product) throws Exception;
	
	/**
	 * 查询用户的所有留言信息
	 * @param user 包含用户id
	 * @return
	 * @throws Exception
	 */
	List<?> getLeaves(User user) throws Exception;
	
	/**
	 * 查询关于这个商品的所有留言
	 * @param product 包含商品id
	 * @return
	 * @throws Exception
	 */
	List<?> getLeaves(Product product) throws Exception;
	
	/**
	 * 分页 查询关于这个商品的所有留言
	 * @param product 包含商品id
	 * @param start 从第几条开始
	 * @param count 要返回的条数
	 * @return
	 * @throws Exception
	 */
	PageModelRelateToJson<Product> getLeaves(PageModelRelateToJson<Product> pm) throws Exception;
}
