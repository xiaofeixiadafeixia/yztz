package cxw.yztz.dao.daoImpl;

import java.util.List;

import org.hibernate.Criteria;

import cxw.yztz.dao.ITyepDao;
import cxw.yztz.entity.Type;
import cxw.yztz.utils.HibernateUtils;

public class TypeDaoImpl implements ITyepDao{

	public TypeDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<?> getType() throws Exception {
		Criteria criteria = HibernateUtils.getSession().createCriteria(Type.class);
		return criteria.list();
	}

	@Override
	public boolean saveType(Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().save(type);
		return true;
	}

	@Override
	public boolean deleteType(Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().delete(type);
		return true;
	}

	@Override
	public boolean updateTye(Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.getSession().update(type);
		return true;
	}
	@Override
	public Type getType(Type type) throws Exception {
		// TODO Auto-generated method stub
		return (Type)HibernateUtils.getSession().createQuery("from Type where type_id = ?").setInteger(0, type.getType_id()).uniqueResult();
	}
}
