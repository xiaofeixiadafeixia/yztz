package cxw.yztz.utils;
import java.util.Random;
import java.util.UUID;
public class UUIDUtils {

	private UUIDUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static String getUUID64(){
		return getId()+getId();
	}
	
	public static int getIntId() {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for(int x=0;x<9;x++) {
			sb.append(r.nextInt(10));
		}
		return Integer.parseInt(sb.toString());
	}
}
