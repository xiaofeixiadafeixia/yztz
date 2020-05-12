package cxw.yztz.dao;

import java.util.List;

import cxw.yztz.entity.BrowseHistory;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;

public interface IBrowseHistoryDao {
	/**
	 * 保存浏览访问记录
	 * @param bh
	 * @return
	 * @throws Exception
	 */
	boolean saveBrowseHistory(BrowseHistory bh) throws Exception;
	
	/**
	 * 删除浏览访问记录
	 * @param bh
	 * @return
	 * @throws Exception
	 */
	boolean deleteBrowseHistory(BrowseHistory bh) throws Exception;
	
	/**
	 * 删除与这个商品相关的浏览记录
	 * @param product 
	 * @return
	 * @throws Exception
	 */
	boolean deleteBrowseHistoryByProdcut(Product product) throws Exception;
	
	/**
	 * 更改浏览记录
	 * @param bh
	 * @return
	 * @throws Exception
	 */
	boolean updateBrowseHistory(BrowseHistory bh) throws Exception;
	
	/**
	 * 根据商品id与用户id查询浏览记录
	 * @param bh 包含商品id 与用户id
	 * @return
	 * @throws Exception
	 */
	BrowseHistory getBrowseHistory(BrowseHistory bh) throws Exception;
	
	/**
	 * 查询用户的浏览记录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getBrowseHistorys(User user) throws Exception;
	
	/**
	 * 将会根据时间排序 查询用户的前几条记录
	 * @param user
	 * @param count 要查询的浏览记录个数
	 * @return
	 * @throws Exception
	 */
	List<?> getBrowseHistorysByCount(User user,Integer start,Integer count)throws Exception;
	
}
