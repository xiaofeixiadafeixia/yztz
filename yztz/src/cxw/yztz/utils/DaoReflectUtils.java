package cxw.yztz.utils;

import java.lang.reflect.Method;

public class DaoReflectUtils {

	private DaoReflectUtils() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param obj 要调用的类
	 * @param daoMethodName 要调用的方法的名字
	 * @param c 调用的方法的参数
	 * @return 调用的方法的返回值（Object类型）
	 * @throws Exception
	 */
	public static Object invokeDaoMethod(Object obj,String daoMethodName,Object...c ) throws Exception{
		if (null == daoMethodName || "".equals(daoMethodName) || daoMethodName.trim().equals("")) {
			throw new Exception("方法不存在");
		}
		if(obj!=null ) {
			Class<?> clazzes[] = new Class<?>[c.length];//存储传进来的对象参数的类
			Class<?> clazz = obj.getClass();//要反射的类
			
			//把传进来的参数对象的类放到clazzes数组中
			for(int x=0;x<c.length;x++) {
				clazzes[x] = c.getClass();
			}
			
			//根据传进来的参数找到要的方法。
			Method method = clazz.getMethod(daoMethodName, clazzes);
			
			HibernateUtils.openSession();
			try {
				Object returnParam = method.invoke(obj,c);
				return returnParam;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				HibernateUtils.closeSession();
			}
		}
		return null;
	}
	public static Object invokeDaoMethodWithTransaction(Object obj,String daoMethodName,Object...c ) throws Exception {
		if (null == daoMethodName || "".equals(daoMethodName) || daoMethodName.trim().equals("")) {
			throw new Exception("方法不存在");
		}
		if(obj!=null ) {
			Class<?> clazzes[] = new Class<?>[c.length];//存储传进来的对象参数的类
			Class<?> clazz = obj.getClass();//要反射的类
			
			//把传进来的参数对象的类放到clazzes数组中
			for(int x=0;x<c.length;x++) 
				clazzes[x] = c.getClass();
			
			//根据传进来的参数找到要的方法。
			Method method = clazz.getMethod(daoMethodName, clazzes);
			
			HibernateUtils.openSession();
			try {
				Object returnParam = method.invoke(obj,c);
				return returnParam;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				HibernateUtils.closeSession();
			}
		}
		return null;
	}
}
