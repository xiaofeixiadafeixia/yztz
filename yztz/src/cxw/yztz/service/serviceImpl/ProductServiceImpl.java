package cxw.yztz.service.serviceImpl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import cxw.yztz.dao.IBrowseHistoryDao;
import cxw.yztz.dao.ICollectDao;
import cxw.yztz.dao.ILeaveDao;
import cxw.yztz.dao.IPictureDao;
import cxw.yztz.dao.IProductDao;
import cxw.yztz.dao.daoImpl.BrowseHistoryDaoImpl;
import cxw.yztz.dao.daoImpl.CollectDaoImpl;
import cxw.yztz.dao.daoImpl.LeaveDaoImpl;
import cxw.yztz.dao.daoImpl.PictureDaoImpl;
import cxw.yztz.dao.daoImpl.ProductDaoImpl;
import cxw.yztz.entity.Picture;
import cxw.yztz.entity.Product;
import cxw.yztz.entity.Type;
import cxw.yztz.entity.User;
import cxw.yztz.service.IProductService;
import cxw.yztz.utils.HibernateUtils;
import cxw.yztz.utils.PageModel;

public class ProductServiceImpl implements IProductService {
	
	private IProductDao productDao = new ProductDaoImpl();
	private IPictureDao pictureDao = new PictureDaoImpl();
	private ICollectDao collectDao = new CollectDaoImpl();
	private IBrowseHistoryDao browseHistoryDao = new BrowseHistoryDaoImpl();
	private ILeaveDao leaveDao = new LeaveDaoImpl();
	
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean saveProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			for(Picture p :product.getPictures()) {
				p.setGoods(product);
			}
			return productDao.saveProduct(product);
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE) 
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public Product getProductById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return productDao.getProductById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public List<?> getProductsByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return productDao.getProuctsByUser(user);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	
	@Override
	public boolean deleteProductByIdWithUser(Integer product_id,User user) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			Product p = productDao.getProductById(product_id);
			if(p.getUser().getId()==user.getId()) {
				//删除这个商品的相关收藏纪录
				collectDao.deleteCollectByProduct(p);
				//删除这个商品的相关浏览记录
				browseHistoryDao.deleteBrowseHistoryByProdcut(p);
				//删除这个商品的相关留言记录
				leaveDao.deleteLeaves(p);
				//删除这个商品的订单记录
				
				for(Picture pic : p.getPictures()) {
					pic.setGoods(null);
				}
				//把图片的外键设为null成功后 ，真正删除商品信息
				if(pictureDao.updatePicture(p.getPictures())) {
					return productDao.deleteProductById(p);
				}
				
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE) 
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public boolean updatePoductState(Product product,User user) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			Product p = productDao.getProductById(product.getGoods_id());
			if(user.getId() == p.getUser().getId()) {
				p.setState(product.getState());
				return productDao.updateProduct(p);
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return false;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE) 
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public PageModel getProductsByCurrentPageNumWithUser(User u, Integer currentPageNum) throws Exception {
		// TODO Auto-generated method stub
		try {
			HibernateUtils.openSession();
			Integer count = productDao.getProductsCountByUser(u);
			PageModel pm = new PageModel(currentPageNum, count, 5);
			pm.setRecords(productDao.getProductsByCurrentPageNumWithUser(u, pm.getStartIndex(), pm.getPageSize()));
			return pm;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
		
	}

	@Override
	public PageModel getProductsByCurrentPageNumWithType(Integer currentPageNum, Type type) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			PageModel pm = new PageModel(currentPageNum, productDao.getProductsCountByType(type), 6);
			pm.setRecords(productDao.getProductsByCurrentPageNumWithType(type, pm.getStartIndex(), pm.getPageSize()));
			return pm;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public PageModel getAllProductsByCurrentPageNum(Integer currentPageNum) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			PageModel pm = new PageModel(currentPageNum, productDao.getAllProductsCount(), 18);
			pm.setRecords(productDao.getProductsByCurrentPageNum(pm.getStartIndex(), pm.getPageSize()));
			return pm;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public List<?> getProductsById(Integer[] product_ids,Integer state) throws Exception {
		// TODO Auto-generated method stub
		Transaction transactionAndOpenSession = HibernateUtils.getTransactionAndOpenSession();
		try {
			return productDao.getProductsById( product_ids,state);
		}catch(Exception e) {
			e.printStackTrace();
			transactionAndOpenSession.rollback();
			return null;
		}finally {
			if(transactionAndOpenSession.getStatus()==TransactionStatus.ACTIVE) 
				transactionAndOpenSession.commit();
			HibernateUtils.closeSession();
		}
	}

	@Override
	public List<?> getProductsBySort(Integer max, String colum) throws Exception {
		// TODO Auto-generated method stub
		HibernateUtils.openSession();
		try {
			return productDao.getProductsBySort(max, colum);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

	@Override
	public PageModel getProductsByFuzzyQueryWhitCurrentPage(String str, Integer currentNum, Integer max) throws Exception {
		HibernateUtils.openSession();
		try {
			PageModel pm = new PageModel(currentNum,productDao.getProductsCountByFuzzyQueryWhitCurrentPage(str), 18);
			pm.setRecords(productDao.getProductsByFuzzyQueryWhitCurrentPage(str, pm.getStartIndex(), pm.getPageSize()));
			return pm;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateUtils.closeSession();
		}
	}

}
