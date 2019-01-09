import java.util.Random;

import com.lakala.amber.security.TrippleDes;
import com.lakala.amber.util.Base64;
//3DES加密方法
public class secTest {

//	public static void main(String[] args) {
//		String mobilePhone = "18515059595";
//		String params = "{\"loanId\":\"201601070000000002\",\"repaymentStatus\":\"2\",\"repaymentStatus\":\"2cb24c1aafea3674b704849253ff82c04c742d1ca67afd8d7748a9cbcc6a78a8\"}";
//    	String key = getRandomCharAndNum(4) + "rsakey3desrsakey" + getRandomCharAndNum(4);
//    	System.out.println("加密前Data=" + params);
//    	String retRes = encryptBy3Des(key, params);
//		System.out.println("加密后Data=" + encryptBy3Des(key, params));
//		System.out.println("解密后Data=" + decryptBy3Des(key, retRes));
//	}
	static String params = "{\"loanId\":\"201601070000000002\",\"repaymentStatus\":\"2\",\"repaymentStatus\":\"2cb24c1aafea3674b704849253ff82c04c742d1ca67afd8d7748a9cbcc6a78a8\"}";
	public static void main(String[] args){
		
		System.out.println("加密后Data"+encryptString(params));
		System.out.println("解密后Data"+decodeString(encryptString(params)));
		
	}
	
	
//	加密方法
	static String key=getRandomCharAndNum(4) + "rsakey3desrsakey" + getRandomCharAndNum(4);
	public static String encryptString(String prams){
//		String key=getRandomCharAndNum(4) + "rsakey3desrsakey" + getRandomCharAndNum(4);
		String keyValue = encryptBy3Des(key, prams);
		return keyValue;
			
	}
//	解密方法
	public static String decodeString(String decString){
		String decValue = decryptBy3Des(key, decString);
		return decValue;
	}

	
	/** 
	 * 获取随机数字 
	 *  
	 * @param length 
	 *            字符串长度 
	 * @return 
	 */  
	public static String getRandomCharAndNum(Integer length) {  
	    String str = "";  
	    Random random = new Random();  
	    for (int i = 0; i < length; i++) {  
	    	str += String.valueOf(random.nextInt(10));  
	    }  
	    return str;  
	}

	/**
	 * 3DES加密
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptBy3Des(String key, String content) {
		TrippleDes td = null;
		try {
			td = new TrippleDes("PKCS5Padding");
		} catch (Exception e) {
			e.printStackTrace();
		}
		td.initKey(key.getBytes());
		byte[] en = td.encrypt(content.getBytes());
		String retRes = new String(Base64.encode(en));
		return retRes;
	}
	
	/**
	 * 3DES解密
	 * @param key
	 * @param content
	 * @return
	 */
	public static String decryptBy3Des(String key, String content){
		try {
			byte[] data = Base64.decode(content);
			TrippleDes td = new TrippleDes("PKCS5Padding");
			td.initKey(key.getBytes());
			byte[] de = td.decrypt(data);
			return new String(de);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
