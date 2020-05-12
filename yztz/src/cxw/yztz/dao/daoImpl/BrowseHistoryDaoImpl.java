package cxw.yztz.dao.daoImpl;

import java.util.List;

import cxw.yztz.dao.IBrowseHistoryDao;
import cxw.yztz.entity.BrowseHistory;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class BrowseHistoryDaoImpl implements IBrowseHistoryDao{

	@Override
	public boolean saveBrowseHistory(BrowseHistory bh) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().save(bh);
		return true;
	}

	@Override
	public boolean deleteBrowseHistory(BrowseHistory bh) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().delete(bh);
		return true;
	}

	@Override
	public boolean updateBrowseHistory(BrowseHistory bh) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().update(bh);
		return true;
	}

	@Override
	public List<?> getBrowseHistorys(User user) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from BrowseHistory where user_id = ?";
		return HibernateUtils.getSession().createQuery(hql).setInteger(0, user.getId()).list();
	}

	@Override
	public List<?> getBrowseHistorysByCount(User user,Integer start, Integer count) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from BrowseHistory where user_id = ?  order by time desc";
		return HibernateUtils.getSession().createQuery(hql).setInteger(0, user.getId()).setFirstResult(start).setMaxResults(count).list();
	}

	@Override
	public BrowseHistory getBrowseHistory(BrowseHistory bh) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().get(BrowseHistory.class,bh.getBrowseHistoryUionPKID());
	}

	@Override
	public boolean deleteBrowseHistoryByProdcut(Product product) throws Exception {
		// TODO Auto-generated method stub
		String hql = "delete BrowseHistory where goods_id = ?";
		HibernateUtils.getSession().createQuery(hql).setInteger(0, product.getGoods_id()).executeUpdate();
		return true;
	}

}
