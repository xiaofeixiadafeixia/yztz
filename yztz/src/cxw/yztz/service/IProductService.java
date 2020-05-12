package cxw.yztz.service;

import java.util.List;

import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;
import cxw.yztz.utils.PageModel;

public interface IProductService {
	/**
	 * 根据用户id信息和商品类别id,与学院id添加商品。
	 * @param product商品（商品id数据库没设自增，需自行增加）
	 * @return
	 * @throws Exception
	 */
	boolean saveProduct(Product product) throws Exception;
	
	/**
	 * 根据商品id 查询商品
	 * @param id 商品id
	 * @return
	 * @throws Exception
	 */
	Product getProductById(Integer id) throws Exception;
	
	/**
	 * 根据用户id  查询所有商品
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsByUser(User user) throws Exception;
	
	/**
	 * 查询一批商品会过滤掉状态不是 state  的商品
	 * @param product_ids
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsById(Integer product_ids[],Integer state) throws Exception;
	
	/**
	 * 根据指定字段排序并取出指定最大数量商品
	 * @param max 指定数量
	 * @param colum 排序字段
	 * @return
	 * @throws Exception
	 */
	List<?> getProductsBySort(Integer max,String colum) throws Exception;
	
	/**
	 * 模糊查询并进行分页
	 * @param str 根据这个字符串进行模糊查询
	 * @param currentNum 当前页
	 * @param max 最大显示记录数
	 * @return
	 * @throws Exception
	 */
	PageModel getProductsByFuzzyQueryWhitCurrentPage(String str,Integer currentNum,Integer max) throws Exception;
	
	/**
	 * 根据商品id 删除商品 
	 * @param product 包含了id
	 * @return
	 * @throws Exception
	 */
	boolean deleteProductByIdWithUser(Integer product_id,User user)throws Exception;
	
	/**
	 * 更改商品的状态
	 * @param product 包含要更改商品的id与状态
	 * @return
	 * @throws Exception
	 */
	boolean updatePoductState(Product product,User user) throws Exception;
	
	/**
	 * 根据用户id和当前页数，进行分页查询
	 * @param u 用户对象
	 * @param currentPageNum 当前页号
	 * @return
	 * @throws Exception
	 */
	PageModel getProductsByCurrentPageNumWithUser(User u,Integer currentPageNum) throws Exception;
	
	/**
	 * 根据商品类别id与当前页数，进行分页查询
	 * @param currentPageNum  当前页
	 * @param type 商品类别
	 * @return
	 * @throws Exception
	 */
	PageModel getProductsByCurrentPageNumWithType(Integer currentPageNum,Type type) throws Exception;
	
	/**
	 * 查询全部商品，并进行分页
	 * @param currentPageNum 当前页
	 * @return
	 * @throws Exception
	 */
	PageModel getAllProductsByCurrentPageNum(Integer currentPageNum) throws Exception;
	
	
}
