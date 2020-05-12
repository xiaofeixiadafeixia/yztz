package cxw.yztz.dao.daoImpl;

import java.util.List;

import org.hibernate.Query;

import cxw.yztz.dao.IProductDao;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;
import cxw.yztz.utils.HibernateUtils;

public class ProductDaoImpl implements IProductDao{

	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean saveProduct(Product product) throws Exception {
		HibernateUtils.getSession().save(product);
		return true;
		
	}

	@Override
	public Product getProductById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().get(Product.class, id);
	}

	@Override
	public List<?> getProuctsByUser(User user) throws Exception {
		String hql = "from Product where user_id = ? and state != 4";
		Query query = HibernateUtils.getSession().createQuery(hql);
		query.setInteger(0, user.getId());
		return query.list();
	}

	@Override
	public boolean deleteProductById(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().delete(product);
		return true;
	}

	@Override
	public boolean updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().update(product);
		return true;
	}

	@Override
	public Integer getProductsCountByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product where user_id = ? and state != 4";
		Query query = HibernateUtils.getSession().createQuery(hql);
		query.setInteger(0, user.getId());
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public Integer getProductsCountByType(Type type) throws Exception {
		// TODO Auto-generated method stub
		return ((Long)HibernateUtils.getSession().createQuery("select count(*) from Product where type_product=? and  state = 1")
				.setInteger(0, type.getType_id()).uniqueResult()).intValue();
	}

	@Override
	public Integer getAllProductsCount() throws Exception {
		// TODO Auto-generated method stub
		return ((Long)HibernateUtils.getSession().createQuery("select count(*) from Product where state = 1").uniqueResult()).intValue();
	}

	@Override
	public List<?> getProductsByCurrentPageNumWithUser(User user, Integer start, Integer max) throws Exception {
		// TODO Auto-generated method stub
		Query query = HibernateUtils.getSession().createQuery("from Product where user_id = ? and state != 4");
		query.setInteger(0, user.getId());
		query.setFirstResult(start);
		query.setMaxResults(max);
		return query.list();
	}

	@Override
	public List<?> getProductsByCurrentPageNumWithType(Type type, Integer start, Integer max) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Product where type_product = ? and state=1")
				.setInteger(0, type.getType_id()).setFetchSize(start).setMaxResults(max).list();
	}

	@Override
	public List<?> getProductsByCurrentPageNum(Integer start, Integer max) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Product where state = 1").setFirstResult(start).setMaxResults(max).list();
	}

	@Override
	public List<?> getProductsById(Integer[] product_ids,Integer state) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Product where  goods_id in :ids and type = :state")
			.setParameterList("ids", product_ids).setParameter("state", state).list();
	}

	@Override
	public List<?> getProductsBySort(Integer max, String colum) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Product where state =1 order by "+colum+" desc")
				.setFirstResult(0).setMaxResults(max).list();
	}

	@Override
	public List<?> getProductsByFuzzyQueryWhitCurrentPage(String str, Integer start, Integer max) throws Exception {
		// TODO Auto-generated method stub
		return HibernateUtils.getSession().createQuery("from Product where state = 1 and name like '%"+str+"%'")
				.setFetchSize(start).setMaxResults(max).list();
	}

	@Override
	public Integer getProductsCountByFuzzyQueryWhitCurrentPage(String str) throws Exception {
		// TODO Auto-generated method stub
		return ((Long)HibernateUtils.getSession().createQuery("select count(*) from Product where state = 1 and name like '%"+str+"%'")
				.uniqueResult()).intValue();
	}
	
}
