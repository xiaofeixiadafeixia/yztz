package cxw.yztz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断用户设置了邮件接收的哪些权限
 * @author 24780
 *
 */
public class JudgeAuthorityEmail {
	public static final Integer SYSTEM_MESSAGE = 1;//系统消息
	public static final Integer INTERACTIVE_MESSAGE = 2;//互动消息
	public static final Integer FRIEND_MESSAGE = 4;//好友消息
	public static final Integer STRANGENESS_MESSAGE = 8;//陌生消息
	public static final Integer VALIDATION_MESSAGE = 16;//验证消息
	public static final Integer ORDER_MESSAGE = 32;//订单消息
	public static final Integer CLOSE_MESSAGE = 64;//关闭消息
	/**
	 * 
	 * @param type 用户设置的权限
	 * @param isExist 要判断是否拥有的权限的值
	 * @return 拥有返回true 否则返回false
	 */
	public static boolean getAuthority(Integer type,Integer isExist) {
		//1：系统消息	2：互动消息   4：好友消息  8：陌生消息    16：验证消息	32：订单消息  64：关闭消息提醒  128：便于计算
		Integer arr[] = new Integer[] {1,2,4,8,16,32,64,128};
		
		//存储传进来的值包含了哪些权限
		List<Integer> include = new ArrayList<Integer>();
		for(int i=arr.length-1;i>=1;i--) {
			if(type>=arr[i-1] && type<arr[i] && type!=0) {
				include.add(arr[i-1]);
				type -= arr[i-1];
			}
		}
		
		for(Integer au :include) {
			if(64==au)
				return false;
			if(isExist == au)
				return true;
		}
		return false;
	}
	
}
