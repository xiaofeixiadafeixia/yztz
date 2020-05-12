package cxw.yztz.dao.daoImpl;

import java.util.List;

import org.hibernate.Query;

import cxw.yztz.dao.ICollectDao;
import cxw.yztz.entity.Collect;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class CollectDaoImpl implements ICollectDao{

	@Override
	public boolean saveCollect(Collect collect) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().save(collect);
		return true;
	}

	@Override
	public List<?> getCollectsByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		Query createQuery = HibernateUtils.getSession().createQuery("from Collect where user_id = ?");
		createQuery.setInteger(0, user.getId());
		return createQuery.list();
	}

	@Override
	public Integer getCollectCountByProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Query query = HibernateUtils.getSession().createQuery("select count(*) from Collect where goods_id = ?");
		query.setInteger(0, product.getGoods_id());
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public boolean deleteCollectByProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Query query = HibernateUtils.getSession().createQuery("delete from Collect where goods_id = ?");
		query.setInteger(0, product.getGoods_id());
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean deleteCollectByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		Query query = HibernateUtils.getSession().createQuery("delete from Collect where user_id = ?");
		query.setInteger(0, user.getId());
		query.executeUpdate();
		return true;
	}

	@Override
	public Collect getCollectBycollectUnionPKID(Collect collect) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().get(Collect.class, collect.getUionPKID());
	}

	@Override
	public boolean deleteCollectByProducts(Integer[] product_ids) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().createQuery("delete from Collect where goods_id in :ids").setParameterList("ids", product_ids).executeUpdate();
		return true;
	}
	
	@Override
	public boolean deleteCollectByProducts(User user,Integer[] product_ids) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().
			createQuery("delete from Collect where user_id = :uid and goods_id in :ids").setParameter("uid", user.getId()).
				setParameterList("ids", product_ids).executeUpdate();
		return true;
	}
	@Override
	public List<?> getProductIdsBySort(Integer max) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("select uionPKID.product.goods_id from Collect"
				+ " group by uionPKID.product.goods_id  order by uionPKID.product.goods_id desc")
				.setFirstResult(0).setMaxResults(max).list();
	}
}
