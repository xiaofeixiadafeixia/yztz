package cxw.yztz.dao;

import java.util.List;

import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;

public interface IProductDao {
	/**
	 * 保存商品
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean saveProduct(Product product) throws Exception;
	
	/**
	 * 根据商品id 查询出商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Product getProductById(Integer id) throws Exception;
	
	/**
	 * 查询出用户下的所有商品
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getProuctsByUser(User user) throws Exception;
	
	/**
	 * 根据商品id 删除商品
	 * @return
	 * @throws Exception
	 */
	boolean deleteProductById(Product product) throws Exception;
	
	/**
	 * 更改商品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean updateProduct(Product product) throws Exception;
	
	/**
	 * 根据用户查询其所拥有商品数量
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Integer getProductsCountByUser(User user) throws Exception;
	
	/**
	 * 模糊查询出来的商品的数量
	 * @param str
	 * @return
	 * @throws Exception
	 */
	Integer getProductsCountByFuzzyQueryWhitCurrentPage(String str) throws Exception;
	/**
	 * 根据类别查询其所拥有的商品数量
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Integer getProductsCountByType(Type type) throws Exception;
	
	/**
	 * 查询全部商品数量
	 * @return
	 * @throws Exception
	 */
	Integer getAllProductsCount() throws Exception;
	
	/**
	 * 模糊查询并进行分页
	 * @param str 根据这个字符串进行模糊查询
	 * @param currentNum 当前页
	 * @param max 最大显示记录数
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsByFuzzyQueryWhitCurrentPage(String str,Integer start,Integer max) throws Exception;
	
	
	/**
	 * 根据用户和开始索引与要查询记录条数  查询商品
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsByCurrentPageNumWithUser(User user,Integer start,Integer max) throws Exception;
	
	/**
	 * 根据类别和开始索引与要查询记录条数   查询所有商品
	 * @param type 类别
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsByCurrentPageNumWithType(Type type,Integer start,Integer max) throws Exception;
	
	/**
	 * 根据开始索引与要查询记录条数   查询所有商品
	 * @param start
	 * @param max
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsByCurrentPageNum(Integer start,Integer max) throws Exception;
	
	/**
	 * 查询一批商品 并过滤掉状态不是 state 的商品
	 * @param product_ids
	 * @return
	 * @throws Exception
	 */
	List<?>getProductsById(Integer product_ids[],Integer state) throws Exception;
	
	/**
	 * 根据指定字段排序并取出指定最大数量商品
	 * @param max 指定数量
	 * @param type 排序字段
	 * @return
	 * @throws Exception
	 */
	List<?>getProductsBySort(Integer max,String colum) throws Exception;
}
