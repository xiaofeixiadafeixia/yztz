package cxw.yztz.dao;

import java.util.List;

import cxw.yztz.entity.Collect;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;

public interface ICollectDao {
	/**
	 * 保存收藏记录
	 * @param collect 收藏记录
	 * @return
	 * @throws Exception
	 */
	boolean saveCollect(Collect collect) throws Exception ;
	
	/**
	 * 获得用户的收藏记录
	 * @param user 包含用户id
	 * @return
	 * @throws Exception
	 */
	List<?> getCollectsByUser(User user) throws Exception ;
	
	/**
	 * 根据 collectUnionPKID 查询是否存在这条收藏记录
	 * @param collect 包含collectUnionPKID
	 * @return 存在则返回 collect对象 否则返回null
	 * @throws Exception
	 */
	Collect getCollectBycollectUnionPKID(Collect collect) throws Exception;
	
	/**
	 * 按照收藏次数获取 指定最大数量 的商品id
	 * @param max 商品个数的最大数量
	 * @return
	 * @throws Exception
	 */
	List<?> getProductIdsBySort(Integer max) throws Exception;
	
	/**
	 * 获得当前商品的被收藏记录数
	 * @param product
	 * @return
	 * @throws Exception
	 */
	Integer getCollectCountByProduct(Product product) throws Exception;
	
	/**
	 * 删除商品的相关收藏记录
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean deleteCollectByProduct(Product product) throws Exception;
	
	/**
	 * 删除用户相关的收藏记录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean deleteCollectByUser(User user )throws Exception;
	
	/**
	 * 删除一批商品的相关收藏纪录
	 * @param product_ids
	 * @return
	 * @throws Exception
	 */
	boolean deleteCollectByProducts(Integer[] product_ids) throws Exception;
	
	/**
	 * 删除这个用户的一批商品收藏记录
	 * @param user 包含用户id
	 * @param product_ids 商品的id数组
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCollectByProducts(User user,Integer[] product_ids) throws Exception;
	
}
