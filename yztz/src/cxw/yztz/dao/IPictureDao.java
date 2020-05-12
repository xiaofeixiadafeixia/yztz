package cxw.yztz.dao;

import java.util.Set;

import cxw.yztz.entity.Picture;

public interface IPictureDao {
	
	/**
	 * 保存集合里所有的图片数据
	 * @param pics
	 * @return
	 * @throws Exception
	 */
	boolean savePicture(Set<Picture> pics) throws Exception ;
	
	/**
	 * 删除集合里所有的图片数据
	 * @param pics
	 * @return
	 * @throws Exception
	 */
	boolean deletePicture(Set<Picture> pics) throws Exception;
	
	/**
	 * 更改集合里的所有图片数据
	 * @param pics
	 * @return
	 * @throws Exception
	 */
	boolean updatePicture(Set<Picture> pics) throws Exception;
	
}
