package cxw.yztz.service;

import java.util.List;

import cxw.yztz.entity.Type;

/**
 * 商品的类别
 * @author 24780
 *
 */
public interface ITypeService {
	
	/**
	 * 获取所有的类别
	 * @return list
	 * @throws Exception
	 */
	List<?> getTypes() throws Exception;
	
	/**
	 * 根据类别id 获得类别信息
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Type getType(Type type) throws Exception;
	
	/**
	 * 添加类别
	 * @param type
	 * @return
	 * @throws Exception
	 */
	boolean saveType(Type type) throws Exception;
	
	/**
	 * 删除类别
	 * @param type
	 * @return
	 * @throws Exception
	 */
	boolean deleteType(Type type) throws Exception;
	
	/**
	 * 更改类别
	 * @param type
	 * @return
	 * @throws Exception
	 */
	boolean updateTye(Type type) throws Exception;
}
