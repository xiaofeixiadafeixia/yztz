package cxw.yztz.dao.daoImpl;

import java.util.List;

import cxw.yztz.dao.ILeaveDao;
import cxw.yztz.entity.Leave;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class LeaveDaoImpl implements ILeaveDao{

	@Override
	public boolean saveLeave(Leave leave) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().save(leave);
		return true;
	}

	@Override
	public boolean deleteLeave(Leave leave) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().delete(leave);
		return true;
	}

	@Override
	public List<?> getLeaves(User user) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Leave where user_id = ?").
				setInteger(0, user.getId()).list();
	}

	@Override
	public List<?> getLeaves(Product product) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Leave where goods_id = ?").
				setInteger(0, product.getGoods_id()).list();
	}

	@Override
	public List<?> getLeaves(Product product, Integer start, Integer count) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Leave where goods_id = ? and type=1 order by time desc").
				setInteger(0, product.getGoods_id()).setFirstResult(start).setMaxResults(count).list();
	}

	@Override
	public boolean deleteLeaves(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().createQuery("delete Leave where goods_id=?")
			.setInteger(0, product.getGoods_id()).executeUpdate();
		return true;
	}

	@Override
	public Integer getLeaveCount(Product product) throws Exception {
		// TODO Auto-generated method stub
		return ((Long)HibernateUtils.getSession().createQuery("select count(*) from Leave where goods_id = ? and type=1")
				.setInteger(0, product.getGoods_id()).uniqueResult()).intValue();
	}

}
