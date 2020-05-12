package cxw.yztz.service;

import java.util.List;

import cxw.yztz.entity.Address;
import cxw.yztz.entity.College;
import cxw.yztz.entity.User;

public interface IAddressService {
	/**
	 * 获得所有学院信息
	 * @throws Exception
	 */
	List<?> getColleges() throws Exception;
	
	/**
	 * 查询用户的所有地址
	 * @param u
	 * @return
	 * @throws Exception
	 */
	List<?> getAddressByUserWithCollege(User u,College college) throws Exception;
	
	/**
	 * 更新地址信息
	 * @param address
	 * @return
	 * @throws Exception
	 */
	boolean updateAddressByAddress_id(Address address) throws Exception;
	
	/**
	 * 保存地址信息
	 * @param address
	 * @return
	 * @throws Exception
	 */
	boolean saveAddress(Address address) throws Exception;
	
	/**
	 * 解除 地址与用户的关联
	 * @param address
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean relieveAddressWithUser(Address address,User user) throws Exception;
}
