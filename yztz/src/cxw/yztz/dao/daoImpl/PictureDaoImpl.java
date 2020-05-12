package cxw.yztz.dao.daoImpl;

import java.util.Set;

import org.hibernate.Session;

import cxw.yztz.dao.IPictureDao;
import cxw.yztz.entity.Picture;
import cxw.yztz.utils.HibernateUtils;

public class PictureDaoImpl implements IPictureDao {

	@Override
	public boolean savePicture(Set<Picture> pics) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		for(Picture p : pics) {
			session.save(p);
		}
		return true;
	}

	@Override
	public boolean deletePicture(Set<Picture> pics) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		for(Picture p : pics) {
			session.delete(p);
		}
		return true;
	}

	@Override
	public boolean updatePicture(Set<Picture> pics) throws Exception {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getSession();
		for(Picture p : pics) {
			session.update(p);
		}
		return true;
	}
	
}
